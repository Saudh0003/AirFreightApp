
package cpcs;


import java.util.*;

/**
 * Implements Dijkstra's algorithm to find shortest paths from a single source location.
 */
public class SingleSourceSPAlg extends ShortestPathAlgorithm {

    private boolean enablePrinting = true;  // Controls whether to print paths

    public SingleSourceSPAlg(Graph graph) {
        this.graph = graph;
    }

    // Enables or disables output printing
    public void setEnablePrinting(boolean enable) {
        this.enablePrinting = enable;
    }

    /**
     * Computes the shortest paths from the given source using Dijkstra's algorithm.
     * @param source The source location to start the algorithm from
     */
    public void computeDijkstraAlg(Location source) {
        Map<Location, Integer> distances = new HashMap<>();  // Holds shortest distances from source
        Map<Location, Route> previousRoute = new HashMap<>(); // Tracks path
        PriorityQueue<Location> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        // Initialize distances to infinity
        for (Location v : graph.getVertices()) {
            distances.put(v, Integer.MAX_VALUE);
        }

        distances.put(source, 0); // Source has distance 0
        queue.add(source);

        // Main loop of Dijkstra
        while (!queue.isEmpty()) {
            Location current = queue.poll();

            for (Route route : graph.getAdjRoutes(current)) {
                Location neighbor = (Location) route.getDestination();
                int newDist = distances.get(current) + route.getWeight();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previousRoute.put(neighbor, route);
                    queue.remove(neighbor); // Update priority
                    queue.add(neighbor);
                }
            }
        }

        // Print all shortest paths from the source
        if (enablePrinting) {
            System.out.println("The starting point location is " + source.getLabel());
            System.out.println("The routes from location " + source.getLabel() + " to the rest of the locations are:");
            for (Location target : graph.getVertices()) {
                if (target.equals(source)) continue;
                printPath(source, target, previousRoute, distances);
            }
            System.out.println("-------------------------------------------------------------");
        }
    }

    /**
     * Helper method to reconstruct and print the shortest path from source to target.
     */
    private void printPath(Location source, Location target, Map<Location, Route> previousRoute, Map<Location, Integer> distances) {
        Stack<Location> path = new Stack<>();
        Location current = target;

        while (!current.equals(source)) {
            Route r = previousRoute.get(current);
            if (r == null) {
                System.out.println("No route from " + source.getLabel() + " to " + target.getLabel());
                return;
            }
            path.push(current);
            current = (Location) r.getSource();
        }

        path.push(source);

        System.out.print("loc. " + path.peek().getLabel() + ": city " + path.peek().getCityNumber());
        path.pop();

        while (!path.isEmpty()) {
            Location loc = path.pop();
            System.out.print(" – loc. " + loc.getLabel() + ": city " + loc.getCityNumber());
        }

        System.out.print(" --- route length: " + distances.get(target));
        System.out.println();
    }
}

