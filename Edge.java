
package cpcs;

/**
 * Abstract class representing an edge in a graph.
 */
public abstract class Edge {
    protected Vertex source;        // Source vertex
    protected Vertex destination;   // Destination vertex
    protected int weight;           // Edge weight
    protected Vertex parent;        // Parent for path tracking (optional)

    public Edge(Vertex source, Vertex destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.parent = null;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    // Abstract method to display edge info (to be implemented in subclass)
    public abstract void displayInfo();
}
