package fr.emse.ai.search.uniform;

import fr.emse.ai.search.core.Node;
import fr.emse.ai.search.simple.SimpleOrientedGraphProblem;
import fr.emse.ai.search.solver.DepthFirstTreeSearch;

// Test class to compare Depth-First Search and Uniform Cost Search algorithms
public class ComparisonTest {

    public static void main(String[] args) {
        System.out.println("=== DEPTH-FIRST SEARCH ===");
        SimpleOrientedGraphProblem simpleProblem = new SimpleOrientedGraphProblem();
        DepthFirstTreeSearch dfs = new DepthFirstTreeSearch();
        Node dfsSolution = dfs.solve(simpleProblem);

        if (dfsSolution != null) {
            System.out.println("\nDepth-First Search solution path:");
            dfsSolution.pathToString();
            System.out.println("Total path cost: " + dfsSolution.getPathCost());
        } else {
            System.out.println("No solution found with Depth-First Search.");
        }

        System.out.println("\n=== UNIFORM COST SEARCH ===");
        UniformCostProblem uniformProblem = new UniformCostProblem();
        UniformCostSearch ucs = new UniformCostSearch();
        Node ucsSolution = ucs.solve(uniformProblem);

        if (ucsSolution != null) {
            System.out.println("\nUniform Cost Search solution path:");
            ucsSolution.pathToString();
            System.out.println("Total path cost: " + ucsSolution.getPathCost());
        } else {
            System.out.println("No solution found with Uniform Cost Search.");
        }

        System.out.println("\n=== COMPARISON ===");
        if (dfsSolution != null && ucsSolution != null) {
            System.out.println("NOTE: The two problems have different edge costs:");
            System.out.println("- SimpleOrientedGraphProblem: All edges have cost 1");
            System.out.println("- UniformCostProblem: Edges have varying costs");
            System.out.println();
            System.out.println("Depth-First Search path cost: " + dfsSolution.getPathCost());
            System.out.println("Uniform Cost Search path cost: " + ucsSolution.getPathCost());

            System.out.println("\nThe comparison is not directly meaningful because the problems have different costs.");
            System.out.println("However, Uniform Cost Search guarantees finding the optimal (lowest cost) path");
            System.out.println("when the edge costs are considered, while Depth-First Search does not.");
        }
    }
}
