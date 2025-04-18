package fr.emse.ai.search.solver;

import fr.emse.ai.search.core.AbstractTreeSearch;
import fr.emse.ai.search.core.Node;
import fr.emse.ai.search.core.Problem;
import fr.emse.ai.search.bottle.Bottles;

import java.util.ArrayList;
import java.util.Collection;

// A* search algorithm that combines path cost (g) and heuristic value (h)
public class AStarSearch extends AbstractTreeSearch {

    private Collection<Node> closedList = new ArrayList<>();

    @Override
    public Collection<Node> initFrontier() {
        return new ArrayList<Node>();
    }

    @Override
    public Node chooseLeafNode(Collection<Node> frontier, Problem problem) {
        Node bestNode = null;
        double minFValue = Double.POSITIVE_INFINITY;

        for (Node node : frontier) {
            double gValue = node.getPathCost();
            double hValue = 0;

            if (node.getState() instanceof Bottles) {
                hValue = ((Bottles) node.getState()).getHValue();
            }

            double fValue = gValue + hValue;

            if (fValue < minFValue) {
                minFValue = fValue;
                bestNode = node;
            }
        }

        frontier.remove(bestNode);
        closedList.add(bestNode);

        return bestNode;
    }

    @Override
    public Collection<Node> expand(Node node, Problem problem) {
        Collection<Node> nodes = new ArrayList<Node>();
        Collection<Object> actions = problem.getActions(node.getState());

        for (Object action : actions) {
            Object nextState = problem.getNextState(node.getState(), action);
            double stepCost = problem.getStepCost(node.getState(), action, nextState);
            Node newNode = new Node(nextState, node, action, stepCost);

            boolean inClosedList = false;
            Node existingClosedNode = null;

            for (Node closedNode : closedList) {
                if (closedNode.getState().equals(nextState)) {
                    inClosedList = true;
                    existingClosedNode = closedNode;
                    break;
                }
            }

            if (inClosedList) {
                if (newNode.getPathCost() < existingClosedNode.getPathCost()) {
                    closedList.remove(existingClosedNode);
                    nodes.add(newNode);
                }
            } else {
                nodes.add(newNode);
            }
        }

        return nodes;
    }

    @Override
    public Node solve(Problem problem) {
        closedList.clear();
        return super.solve(problem);
    }
}
