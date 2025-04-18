package fr.emse.ai.search.comparison;

import fr.emse.ai.search.bestfirst.BestFirstSearch;
import fr.emse.ai.search.core.Node;
import fr.emse.ai.search.solver.DepthFirstTreeSearch;
import fr.emse.ai.search.uniform.UniformCostSearch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A fair comparison test for the three search algorithms:
 * - Depth-First Search
 * - Uniform Cost Search
 * - Best First Search
 *
 * All algorithms use the same problem formulation (CommonProblem) to ensure a fair comparison.
 */
public class FairComparisonTest {

    public static void main(String[] args) {
        System.out.println("=== FAIR COMPARISON OF SEARCH ALGORITHMS ===");
        System.out.println("All algorithms use the same problem formulation with:");
        System.out.println("- Same graph structure");
        System.out.println("- Same edge costs");
        System.out.println("- Same heuristic values (for Best First Search)");
        System.out.println();

        // Create a common problem instance
        CommonProblem problem = new CommonProblem();

        // List to store the number of nodes expanded by each algorithm
        List<Integer> nodesExpanded = new ArrayList<>();

        // Test Depth-First Search
        System.out.println("=== DEPTH-FIRST SEARCH ===");
        DepthFirstTreeSearch dfs = new DepthFirstTreeSearch();
        NodeCountingProblem dfsCountingProblem = new NodeCountingProblem(problem);
        Node dfsSolution = dfs.solve(dfsCountingProblem);

        if (dfsSolution != null) {
            System.out.println("\nDepth-First Search solution path:");
            dfsSolution.pathToString();
            System.out.println("Total path cost: " + dfsSolution.getPathCost());
            System.out.println("Nodes expanded: " + dfsCountingProblem.getNodesExpanded());
            nodesExpanded.add(dfsCountingProblem.getNodesExpanded());
        } else {
            System.out.println("No solution found with Depth-First Search.");
            nodesExpanded.add(0);
        }

        // Test Uniform Cost Search
        System.out.println("\n=== UNIFORM COST SEARCH ===");
        UniformCostSearch ucs = new UniformCostSearch();
        NodeCountingProblem ucsCountingProblem = new NodeCountingProblem(problem);
        Node ucsSolution = ucs.solve(ucsCountingProblem);

        if (ucsSolution != null) {
            System.out.println("\nUniform Cost Search solution path:");
            ucsSolution.pathToString();
            System.out.println("Total path cost: " + ucsSolution.getPathCost());
            System.out.println("Nodes expanded: " + ucsCountingProblem.getNodesExpanded());
            nodesExpanded.add(ucsCountingProblem.getNodesExpanded());
        } else {
            System.out.println("No solution found with Uniform Cost Search.");
            nodesExpanded.add(0);
        }

        // Test Best First Search
        System.out.println("\n=== BEST FIRST SEARCH ===");
        BestFirstSearch bfs = new BestFirstSearch();
        NodeCountingProblem bfsCountingProblem = new NodeCountingProblem(problem);
        Node bfsSolution = bfs.solve(bfsCountingProblem);

        if (bfsSolution != null) {
            System.out.println("\nBest First Search solution path:");
            bfsSolution.pathToString();
            System.out.println("Total path cost: " + bfsSolution.getPathCost());
            System.out.println("Nodes expanded: " + bfsCountingProblem.getNodesExpanded());
            nodesExpanded.add(bfsCountingProblem.getNodesExpanded());
        } else {
            System.out.println("No solution found with Best First Search.");
            nodesExpanded.add(0);
        }

        // Compare the algorithms
        System.out.println("\n=== COMPARISON RESULTS ===");

        // Compare path costs
        if (dfsSolution != null && ucsSolution != null && bfsSolution != null) {
            System.out.println("Path costs:");
            System.out.println("- Depth-First Search: " + dfsSolution.getPathCost());
            System.out.println("- Uniform Cost Search: " + ucsSolution.getPathCost());
            System.out.println("- Best First Search: " + bfsSolution.getPathCost());

            // Determine which algorithm found the optimal (lowest cost) path
            double minCost = Math.min(dfsSolution.getPathCost(),
                           Math.min(ucsSolution.getPathCost(), bfsSolution.getPathCost()));

            System.out.println("\nOptimal path cost: " + minCost);
            System.out.println("Algorithms that found the optimal path:");
            if (dfsSolution.getPathCost() == minCost) {
                System.out.println("- Depth-First Search");
            }
            if (ucsSolution.getPathCost() == minCost) {
                System.out.println("- Uniform Cost Search");
            }
            if (bfsSolution.getPathCost() == minCost) {
                System.out.println("- Best First Search");
            }
        }

        // Compare efficiency (nodes expanded)
        if (nodesExpanded.size() == 3) {
            System.out.println("\nNodes expanded (efficiency):");
            System.out.println("- Depth-First Search: " + nodesExpanded.get(0));
            System.out.println("- Uniform Cost Search: " + nodesExpanded.get(1));
            System.out.println("- Best First Search: " + nodesExpanded.get(2));

            // Determine which algorithm was most efficient (expanded fewest nodes)
            int minNodes = Math.min(nodesExpanded.get(0),
                         Math.min(nodesExpanded.get(1), nodesExpanded.get(2)));

            System.out.println("\nMost efficient algorithm(s) (expanded fewest nodes):");
            if (nodesExpanded.get(0) == minNodes) {
                System.out.println("- Depth-First Search");
            }
            if (nodesExpanded.get(1) == minNodes) {
                System.out.println("- Uniform Cost Search");
            }
            if (nodesExpanded.get(2) == minNodes) {
                System.out.println("- Best First Search");
            }
        }

        // Provide a summary of the algorithms' characteristics
        System.out.println("\n=== ALGORITHM CHARACTERISTICS ===");
        System.out.println("Depth-First Search:");
        System.out.println("- Uses a stack (LIFO) for the frontier");
        System.out.println("- Explores deeply before backtracking");
        System.out.println("- Not guaranteed to find the optimal solution");
        System.out.println("- Memory efficient for deep trees");

        System.out.println("\nUniform Cost Search:");
        System.out.println("- Explores nodes in order of increasing path cost");
        System.out.println("- Guaranteed to find the optimal solution");
        System.out.println("- May explore more nodes than necessary");
        System.out.println("- Memory requirements can be high");

        System.out.println("\nBest First Search:");
        System.out.println("- Uses heuristic values to guide the search");
        System.out.println("- Explores nodes that appear to be closer to the goal first");
        System.out.println("- Not guaranteed to find the optimal solution");
        System.out.println("- Often more efficient than uninformed search algorithms");
    }

    /**
     * A wrapper around a Problem that counts the number of nodes expanded.
     */
    private static class NodeCountingProblem implements fr.emse.ai.search.core.Problem {
        private fr.emse.ai.search.core.Problem wrappedProblem;
        private int nodesExpanded;

        public NodeCountingProblem(fr.emse.ai.search.core.Problem problem) {
            this.wrappedProblem = problem;
            this.nodesExpanded = 0;
        }

        @Override
        public Object getInitialState() {
            return wrappedProblem.getInitialState();
        }

        @Override
        public boolean isGoal(Object state) {
            return wrappedProblem.isGoal(state);
        }

        @Override
        public Collection<Object> getActions(Object state) {
            // Increment the counter when a node is expanded
            nodesExpanded++;
            return wrappedProblem.getActions(state);
        }

        @Override
        public Object getNextState(Object state, Object action) {
            return wrappedProblem.getNextState(state, action);
        }

        @Override
        public double getStepCost(Object start, Object action, Object dest) {
            return wrappedProblem.getStepCost(start, action, dest);
        }

        public int getNodesExpanded() {
            return nodesExpanded;
        }
    }
}
