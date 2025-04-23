package cpcs;

/**
 * This class handles running Dijkstra's algorithm from all source vertices
 * in the graph, effectively implementing the all-pairs shortest paths approach.
 */
public class DBAllSourceSPAlg extends ShortestPathAlgorithm {
    
    // Flag to control whether to print detailed output or not
    private boolean enablePrinting = true;

    /**
     * Constructor that receives the graph to be processed
     * @param graph the graph on which the algorithm will run
     */
    public DBAllSourceSPAlg(Graph graph) {
        this.graph = graph;
    }

    /**
     * Allows enabling or disabling detailed output printing
     * @param enable true to print full paths, false to suppress output (for performance tests)
     */
    public void setEnablePrinting(boolean enable) {
        this.enablePrinting = enable;
    }

    /**
     * Runs Dijkstra's algorithm from every vertex in the graph.
     * For each vertex, it creates a new instance of the single-source algorithm and computes paths.
     */
    public void computeDijkstraBasedSPAlg() {
        for (Location source : graph.getVertices()) {
            // Create a new single-source shortest path algorithm instance
            SingleSourceSPAlg singleSourceAlg = new SingleSourceSPAlg(graph);
            
            // Set whether or not to print the detailed paths
            singleSourceAlg.setEnablePrinting(enablePrinting);
            
            // Compute the shortest paths from the current source vertex
            singleSourceAlg.computeDijkstraAlg(source);
        }
    }
}

