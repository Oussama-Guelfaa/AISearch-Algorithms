package fr.emse.ai.search.bestfirst;

import fr.emse.ai.search.core.Node;

// Test class for the Best First Search algorithm
public class BestFirstTest {

    public static void main(String[] args) {
        BestFirstProblem problem = new BestFirstProblem();
        System.out.println("Solution to problem using best first search:");

        BestFirstSearch bfs = new BestFirstSearch();
        Node solution = bfs.solve(problem);
        if (solution != null) {
            System.out.println("\nSolution path:");
            solution.pathToString();
            System.out.println("Total path cost: " + solution.getPathCost());
        } else {
            System.out.println("No solution found.");
        }
    }
}
