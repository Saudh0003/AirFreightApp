
package cpcs;

/**
 * Represents a route (edge) between two locations in the graph.
 * Inherits from the abstract Edge class.
 */
public class Route extends Edge {

    /**
     * Constructor that creates a new route with given source, destination, and weight.
     * @param source The starting location
     * @param destination The ending location
     * @param weight The distance or cost of the route
     */
    public Route(Location source, Location destination, int weight) {
        super(source, destination, weight);  // Calls the constructor of the parent class (Edge)
    }

    /**
     * Displays the weight of the route.
     */
    @Override
    public void displayInfo() {
        System.out.println("--- route length: " + weight);
    }
}
