package fr.emse.ai.search.bestfirst;

import fr.emse.ai.search.core.Node;

// Test class to demonstrate the Best First Search algorithm
public class AlgorithmComparisonTest {

    public static void main(String[] args) {
        System.out.println("=== BEST FIRST SEARCH ===");
        BestFirstProblem bestFirstProblem = new BestFirstProblem();
        BestFirstSearch bfs = new BestFirstSearch();
        Node solution = bfs.solve(bestFirstProblem);

        if (solution != null) {
            System.out.println("\nBest First Search solution path:");
            solution.pathToString();
            System.out.println("Total path cost: " + solution.getPathCost());
        } else {
            System.out.println("No solution found with Best First Search.");
        }
        System.out.println("\n=== ALGORITHM EXPLANATION ===");
        System.out.println("Best First Search is a search algorithm that uses a heuristic function");
        System.out.println("to determine which nodes to explore first. The heuristic function");
        System.out.println("estimates how close a node is to the goal state.");
        System.out.println();
        System.out.println("In this implementation:");
        System.out.println("- Each state has a heuristic value (h-value) that estimates its distance to the goal");
        System.out.println("- Lower h-values indicate states that are closer to the goal");
        System.out.println("- The goal state (H) has an h-value of 0");
        System.out.println("- The algorithm always chooses to explore the node with the lowest h-value");
        System.out.println("- Only new states are added to the frontier (states are not revisited)");
        System.out.println();
        System.out.println("The path found by Best First Search is not guaranteed to be optimal,");
        System.out.println("but it often finds a solution more efficiently than uninformed search");
        System.out.println("algorithms like Depth-First Search or Breadth-First Search.");
    }
}
