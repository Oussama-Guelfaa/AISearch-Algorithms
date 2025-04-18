package fr.emse.ai.search.bestfirst;

import fr.emse.ai.search.core.Node;

// Simple test for the Best First Search algorithm
public class SimpleBestFirstTest {

    public static void main(String[] args) {
        System.out.println("Testing Best First Search algorithm");

        BestFirstProblem problem = new BestFirstProblem();
        BestFirstSearch search = new BestFirstSearch();
        Node solution = search.solve(problem);
        if (solution != null) {
            System.out.println("\nSolution found!");
            System.out.println("Path from start to goal:");
            solution.pathToString();
            System.out.println("Total path cost: " + solution.getPathCost());
        } else {
            System.out.println("No solution found.");
        }
    }
}
