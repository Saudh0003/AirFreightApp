
package cpcs;

/**
 * Abstract class representing a graph vertex.
 * Each vertex has a label and must implement displayInfo().
 */
public abstract class Vertex {
    
    // Label of the vertex (e.g., "A", "B", etc.)
    protected String label;

    /**
     * Constructor that sets the vertex label.
     * @param label The label of the vertex
     */
    public Vertex(String label) {
        this.label = label;
    }

    /**
     * Returns the label of the vertex.
     * @return The label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Abstract method to display information about the vertex.
     */
    public abstract void displayInfo();  
}
