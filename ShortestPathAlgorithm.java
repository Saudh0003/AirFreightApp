
package cpcs;

/**
 * Abstract base class for shortest path algorithms.
 * Stores a reference to the graph on which the algorithm operates.
 */
public abstract class ShortestPathAlgorithm {
    
    // The graph on which the algorithm will run
    protected Graph graph;

    /**
     * Default constructor. Initializes graph to null.
     */
    public ShortestPathAlgorithm() {
        this.graph = null;
    }

    /**
     * Constructor with graph parameter.
     * @param graph The graph to be used by the algorithm
     */
    public ShortestPathAlgorithm(Graph graph) {
        this.graph = graph;
    }

    /**
     * Sets the graph to be used by the algorithm.
     */
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    /**
     * Returns the current graph.
     * @return The graph object
     */
    public Graph getGraph() {
        return graph;
    }
}
