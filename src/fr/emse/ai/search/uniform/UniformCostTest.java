package fr.emse.ai.search.uniform;

import fr.emse.ai.search.core.Node;

// Test class for the Uniform Cost Search algorithm
public class UniformCostTest {

    public static void main(String[] args) {
        UniformCostProblem problem = new UniformCostProblem();
        System.out.println("Solution to problem using uniform cost search:");

        UniformCostSearch ucs = new UniformCostSearch();
        Node solution = ucs.solve(problem);
        if (solution != null) {
            System.out.println("\nSolution path:");
            solution.pathToString();
            System.out.println("Total path cost: " + solution.getPathCost());
        } else {
            System.out.println("No solution found.");
        }
    }
}
