package fr.emse.ai.search.uniform;

import fr.emse.ai.search.core.AbstractTreeSearch;
import fr.emse.ai.search.core.Node;
import fr.emse.ai.search.core.Problem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

// Implementation of Uniform Cost Search algorithm using minimum path cost
public class UniformCostSearch extends AbstractTreeSearch {

    @Override
    public Collection<Node> initFrontier() {
        return new ArrayList<Node>();
    }

    @Override
    public Node chooseLeafNode(Collection<Node> frontier, Problem problem) {
        Node minNode = null;
        double minCost = Double.POSITIVE_INFINITY;

        for (Node node : frontier) {
            if (node.getPathCost() < minCost) {
                minCost = node.getPathCost();
                minNode = node;
            }
        }

        frontier.remove(minNode);

        return minNode;
    }
}
