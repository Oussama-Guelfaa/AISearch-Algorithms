package fr.emse.ai.search.bestfirst;

import fr.emse.ai.search.core.AbstractTreeSearch;
import fr.emse.ai.search.core.Node;
import fr.emse.ai.search.core.Problem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

// Best First Search algorithm using heuristic values to guide exploration
public class BestFirstSearch extends AbstractTreeSearch {

    private Set<Object> visitedStates = new HashSet<>();

    @Override
    public Collection<Node> initFrontier() {
        return new ArrayList<Node>();
    }

    @Override
    public Node chooseLeafNode(Collection<Node> frontier, Problem problem) {
        Node bestNode = null;
        int minHValue = Integer.MAX_VALUE;

        for (Node node : frontier) {
            if (node.getState() instanceof HeuristicState) {
                HeuristicState state = (HeuristicState) node.getState();
                int hValue = state.getHValue();

                if (hValue < minHValue) {
                    minHValue = hValue;
                    bestNode = node;
                }
            }
        }

        frontier.remove(bestNode);
        visitedStates.add(bestNode.getState());

        return bestNode;
    }

    @Override
    public Collection<Node> expand(Node node, Problem problem) {
        Collection<Node> nodes = new ArrayList<Node>();
        Collection<Object> actions = problem.getActions(node.getState());

        for (Object action : actions) {
            Object nextState = problem.getNextState(node.getState(), action);

            if (!visitedStates.contains(nextState)) {
                nodes.add(new Node(nextState, node, action, problem.getStepCost(node.getState(), action, nextState)));
            }
        }

        return nodes;
    }

    @Override
    public Node solve(Problem problem) {
        visitedStates.clear();
        visitedStates.add(problem.getInitialState());
        return super.solve(problem);
    }
}
