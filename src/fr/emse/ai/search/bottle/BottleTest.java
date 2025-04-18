package fr.emse.ai.search.bottle;

import fr.emse.ai.search.core.Node;
import fr.emse.ai.search.solver.AStarSearch;

// Test for the bottle problem (4,3) with target=2 using A* search
public class BottleTest {

    public static void main(String[] args) {
        int[] capacities = {4, 3};
        int targetVolume = 2;

        BottleProblem problem = new BottleProblem(capacities, targetVolume);

        System.out.println("Solving the bottle problem with A* search:");
        System.out.println("Bottle capacities: [4, 3]");
        System.out.println("Target volume: 2");
        System.out.println();

        AStarSearch astar = new AStarSearch();
        Node solution = astar.solve(problem);

        if (solution != null) {
            System.out.println("Solution found!");
            System.out.println("Path from initial state to goal:");

            for (Node node : solution.getPathFromRoot()) {
                System.out.println("State: " + node.getState());
                if (node.getAction() != null) {
                    System.out.println("Action: " + node.getAction());
                }
            }

            System.out.println("\nTotal steps: " + (solution.getPathFromRoot().size() - 1));
        } else {
            System.out.println("No solution found.");
        }
    }
}
