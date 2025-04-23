package cpcs;

import java.util.Scanner;

public class AirFreightApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display the main menu for user selection
        System.out.println("Choose one of the options:");
        System.out.println("1. Run the algorithm on a graph file (Graph.txt...)");
        System.out.println("2. Run performance test on large random graphs");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            // If user selects to load graph from file
            System.out.println("\nChoose a file:");
            System.out.println("1. Graph.txt");
            System.out.println("2. Graph1.txt");
            System.out.println("3. Graph3.txt");
            System.out.print("Your choice: ");
            int fileChoice = scanner.nextInt();
            String filename = "";

            // Select filename based on user input
            switch (fileChoice) {
                case 1: filename = "Graph.txt"; break;
                case 2: filename = "Graph1.txt"; break;
                case 3: filename = "Graph3.txt"; break;
                default:
                    System.out.println("Invalid option.");
                    return;
            }

            // Create graph object and read data from the selected file
            Graph graph = new Graph();
            graph.readGraphFromFile(filename);

            System.out.println("Graph successfully loaded from file: " + filename);
            System.out.println("------------------------------------------------");
            System.out.println("Printing shortest paths from each source location:");
            System.out.println("------------------------------------------------");

            // Instantiate the algorithm and enable printing of full paths
            DBAllSourceSPAlg alg = new DBAllSourceSPAlg(graph);
            alg.setEnablePrinting(true);  // Enable detailed output
            alg.computeDijkstraBasedSPAlg();  // Run the algorithm

        } else if (choice == 2) {
            // Run the performance testing mode
            runPerformanceTest();
        } else {
            // Handle invalid user input
            System.out.println("Invalid choice.");
        }

        scanner.close();  // Close the input scanner
    }

    // Method to run the performance test on randomly generated graphs
    public static void runPerformanceTest() {
        // Array of graph sizes (number of vertices and edges)
        int[] nValues = {2000, 3000, 4000, 5000, 6000};
        int[] mValues = {10000, 15000, 20000, 25000, 30000};

        System.out.println("-------------------------------------------------------------");
        System.out.println("   n\t|\t   m\t|\tExecution Time (ms)");
        System.out.println("-------------------------------------------------------------");

        for (int i = 0; i < nValues.length; i++) {
            int n = nValues[i];
            int m = mValues[i];

            // Generate a random graph with n vertices and m edges
            Graph graph = new Graph();
            graph.make_graph(n, m);

            // Create algorithm instance and disable printing (for performance test)
            DBAllSourceSPAlg alg = new DBAllSourceSPAlg(graph);
            alg.setEnablePrinting(false);  // Disable detailed output for performance measurement

            // Measure execution time of the algorithm
            long start = System.currentTimeMillis();
            alg.computeDijkstraBasedSPAlg();
            long end = System.currentTimeMillis();

            long duration = end - start;  // Calculate duration
            System.out.println("  " + n + "\t|\t" + m + "\t|\t" + duration + " ms");
        }

        System.out.println("-------------------------------------------------------------");
    }
}

