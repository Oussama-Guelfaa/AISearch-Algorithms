package fr.emse.ai.search.comparison;

import fr.emse.ai.search.core.Problem;
import fr.emse.ai.search.bestfirst.HeuristicState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A common problem formulation for comparing different search algorithms.
 * This problem has the same graph structure, edge costs, and heuristic values
 * for all algorithms to ensure a fair comparison.
 */
public class CommonProblem implements Problem {

    private HeuristicState initialState = new HeuristicState(HeuristicState.A);
    private HeuristicState finalState = new HeuristicState(HeuristicState.H);
    
    // Map to store the costs between states
    private Map<String, Map<String, Double>> edgeCosts;
    
    public CommonProblem() {
        // Initialize the edge costs
        initializeEdgeCosts();
    }
    
    private void initializeEdgeCosts() {
        edgeCosts = new HashMap<>();
        
        // Initialize maps for each state
        for (String state : new String[]{HeuristicState.A, HeuristicState.B, HeuristicState.C, 
                                         HeuristicState.D, HeuristicState.E, HeuristicState.F, 
                                         HeuristicState.G, HeuristicState.H}) {
            edgeCosts.put(state, new HashMap<>());
        }
        
        // Define costs for each edge
        // From A
        edgeCosts.get(HeuristicState.A).put(HeuristicState.B, 3.0);
        edgeCosts.get(HeuristicState.A).put(HeuristicState.C, 1.0);
        
        // From B
        edgeCosts.get(HeuristicState.B).put(HeuristicState.D, 2.0);
        edgeCosts.get(HeuristicState.B).put(HeuristicState.E, 2.0);
        
        // From C
        edgeCosts.get(HeuristicState.C).put(HeuristicState.D, 1.0);
        
        // From D
        edgeCosts.get(HeuristicState.D).put(HeuristicState.G, 3.0);
        
        // From E
        edgeCosts.get(HeuristicState.E).put(HeuristicState.F, 2.0);
        
        // From F
        edgeCosts.get(HeuristicState.F).put(HeuristicState.H, 1.0);
        
        // From G
        edgeCosts.get(HeuristicState.G).put(HeuristicState.H, 2.0);
    }

    @Override
    public Object getInitialState() {
        return initialState;
    }

    @Override
    public boolean isGoal(Object state) {
        return state.equals(finalState);
    }

    @Override
    public Collection<Object> getActions(Object state) {
        ArrayList<Object> actions = new ArrayList<>();
        String s = ((HeuristicState) state).value;
        
        // Get all possible destinations from the current state
        Map<String, Double> destinations = edgeCosts.get(s);
        if (destinations != null) {
            for (String destination : destinations.keySet()) {
                actions.add("go to " + destination);
            }
        }
        
        return actions;
    }

    @Override
    public Object getNextState(Object state, Object action) {
        if (action instanceof String) {
            String actionStr = (String) action;
            if (actionStr.startsWith("go to ")) {
                String destination = actionStr.substring(6); // Extract the destination state
                return new HeuristicState(destination);
            }
        }
        return null;
    }

    @Override
    public double getStepCost(Object start, Object action, Object dest) {
        if (start instanceof HeuristicState && dest instanceof HeuristicState) {
            String startState = ((HeuristicState) start).value;
            String destState = ((HeuristicState) dest).value;
            
            // Look up the cost in our edge costs map
            Map<String, Double> destinations = edgeCosts.get(startState);
            if (destinations != null && destinations.containsKey(destState)) {
                return destinations.get(destState);
            }
        }
        // Default cost if not found
        return Double.POSITIVE_INFINITY;
    }
}
