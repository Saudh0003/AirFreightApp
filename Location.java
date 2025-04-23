
package cpcs;

/**
 * Represents a specific location in the graph.
 * Extends the Vertex class and includes a unique city number.
 */
public class Location extends Vertex {
    
    private int cityNumber; // City identifier (e.g., city 1, city 2, ...)

    /**
     * Constructor that sets the label and the city number.
     * @param label The name or label of the location (e.g., "A", "B", etc.)
     * @param cityNumber The unique number assigned to the city
     */
    public Location(String label, int cityNumber) {
        super(label);  // Calls the constructor of the parent class (Vertex)
        this.cityNumber = cityNumber;
    }

    /**
     * Displays the label and city number of this location.
     */
    @Override
    public void displayInfo() {
        System.out.println(label + ": city " + cityNumber);
    }

    /**
     * Returns the city number.
     * @return cityNumber
     */
    public int getCityNumber() {
        return cityNumber;
    }
}

