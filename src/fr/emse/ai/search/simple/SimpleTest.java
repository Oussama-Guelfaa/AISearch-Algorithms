package fr.emse.ai.search.simple;

import fr.emse.ai.search.core.Node;
import fr.emse.ai.search.solver.DepthFirstTreeSearch;

// Test class for the Depth-First Search algorithm
public class SimpleTest {

    public static void main(String[] args) {
        SimpleOrientedGraphProblem p1 = new SimpleOrientedGraphProblem();
        System.out.println("Solution to problem using depth first : ");

        DepthFirstTreeSearch dfts = new DepthFirstTreeSearch();
        Node solution = dfts.solve(p1);
        if (solution != null) {
            System.out.println("\nSolution path:");
            solution.pathToString();
        } else {
            System.out.println("No solution found.");
        }
    }
}
