
package cpcs;

import java.util.*;

public class Graph {
    // Stores all locations by their label
    private Map<String, Location> vertices = new HashMap<>();

    // List of all edges (routes)
    private List<Route> edges = new ArrayList<>();

    // Adjacency list for efficient edge lookups
    private Map<Location, List<Route>> adjacencyList = new HashMap<>();

    // Determines whether the graph is directed
    private boolean isDirected;

    // Counter to assign unique city numbers
    int cityCounter = 1;

    // Setter and getter for directed mode
    public void setDirected(boolean directed) {
        this.isDirected = directed;
    }

    public boolean isDirected() {
        return isDirected;
    }

    // Creates a new location (vertex) if it doesn't already exist
    public Location createVertex(String label) {
        if (!vertices.containsKey(label)) {
            Location loc = new Location(label, cityCounter++);
            vertices.put(label, loc);
        }
        return vertices.get(label);
    }

    // Creates an edge between two labels with a given weight
    public Route createEdge(String srcLabel, String destLabel, int weight) {
        Location source = createVertex(srcLabel);
        Location destination = createVertex(destLabel);

        Route route = new Route(source, destination, weight);
        edges.add(route);
        adjacencyList.computeIfAbsent(source, k -> new ArrayList<>()).add(route);

        // Add reverse edge if the graph is undirected
        if (!isDirected) {
            Route reverseRoute = new Route(destination, source, weight);
            edges.add(reverseRoute);
            adjacencyList.computeIfAbsent(destination, k -> new ArrayList<>()).add(reverseRoute);
        }

        return route;
    }

    // Reads a graph from a .txt file (directed or undirected)
    public void readGraphFromFile(String filename) {
        try {
            Scanner scanner = new Scanner(new java.io.File(filename));
            String graphType = scanner.nextLine();  // "digraph 0" or "digraph 1"
            isDirected = graphType.endsWith("1");

            int n = scanner.nextInt();
            int m = scanner.nextInt();

            // Read and create each edge from the file
            while (scanner.hasNext()) {
                String src = scanner.next();
                String dest = scanner.next();
                int weight = scanner.nextInt();
                createEdge(src, dest, weight);
            }

            scanner.close();
            System.out.println("Graph loaded from: " + filename);
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Generates a random directed connected graph
    public void make_graph(int n, int m) {
        vertices.clear();
        edges.clear();
        adjacencyList.clear();
        cityCounter = 1;
        isDirected = true;

        Random rand = new Random();

        // Create all vertices
        for (int i = 0; i < n; i++) {
            createVertex("L" + i);
        }

        List<String> labels = new ArrayList<>(vertices.keySet());
        Set<String> edgeSet = new HashSet<>();

        // Ensure the graph is connected by linking each new node to a previous one
        for (int i = 1; i < n; i++) {
            String from = labels.get(i);
            String to = labels.get(rand.nextInt(i));
            int weight = rand.nextInt(20) + 1;
            createEdge(from, to, weight);
            edgeSet.add(from + "->" + to);
        }

        // Add remaining edges randomly
        while (edges.size() < m) {
            String src = labels.get(rand.nextInt(n));
            String dest = labels.get(rand.nextInt(n));
            if (!src.equals(dest)) {
                String key = src + "->" + dest;
                if (!edgeSet.contains(key)) {
                    int weight = rand.nextInt(20) + 1;
                    createEdge(src, dest, weight);
                    edgeSet.add(key);
                }
            }
        }

        System.out.println("Graph created with " + n + " vertices and " + m + " edges (connected).");
    }

    // Returns all vertices
    public Collection<Location> getVertices() {
        return vertices.values();
    }

    // Returns all edges
    public List<Route> getEdges() {
        return edges;
    }

    // Returns adjacent routes for a given location
    public List<Route> getAdjRoutes(Location loc) {
        return adjacencyList.getOrDefault(loc, new ArrayList<>());
    }
}
