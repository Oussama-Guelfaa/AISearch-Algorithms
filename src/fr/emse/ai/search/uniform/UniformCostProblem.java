package fr.emse.ai.search.uniform;

import fr.emse.ai.search.core.Problem;
import fr.emse.ai.search.simple.SimpleState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

// Implementation of a graph problem with weighted edges for Uniform Cost Search
public class UniformCostProblem implements Problem {

    private SimpleState initialState = new SimpleState(SimpleState.A);
    private SimpleState finalState = new SimpleState(SimpleState.H);

    private Map<String, Map<String, Double>> edgeCosts;

    public UniformCostProblem() {
        initializeEdgeCosts();
    }

    private void initializeEdgeCosts() {
        edgeCosts = new HashMap<>();

        for (String state : new String[]{SimpleState.A, SimpleState.B, SimpleState.C,
                                         SimpleState.D, SimpleState.E, SimpleState.F,
                                         SimpleState.G, SimpleState.H}) {
            edgeCosts.put(state, new HashMap<>());
        }

        edgeCosts.get(SimpleState.A).put(SimpleState.B, 3.0);
        edgeCosts.get(SimpleState.A).put(SimpleState.C, 1.0);

        edgeCosts.get(SimpleState.B).put(SimpleState.D, 2.0);
        edgeCosts.get(SimpleState.B).put(SimpleState.E, 2.0);

        edgeCosts.get(SimpleState.C).put(SimpleState.D, 1.0);

        edgeCosts.get(SimpleState.D).put(SimpleState.G, 3.0);

        edgeCosts.get(SimpleState.E).put(SimpleState.F, 2.0);

        edgeCosts.get(SimpleState.F).put(SimpleState.H, 1.0);

        edgeCosts.get(SimpleState.G).put(SimpleState.H, 2.0);
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
        String s = ((SimpleState) state).value;

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
                String destination = actionStr.substring(6);
                return new SimpleState(destination);
            }
        }
        return null;
    }

    @Override
    public double getStepCost(Object start, Object action, Object dest) {
        if (start instanceof SimpleState && dest instanceof SimpleState) {
            String startState = ((SimpleState) start).value;
            String destState = ((SimpleState) dest).value;

            Map<String, Double> destinations = edgeCosts.get(startState);
            if (destinations != null && destinations.containsKey(destState)) {
                return destinations.get(destState);
            }
        }
        return Double.POSITIVE_INFINITY;
    }
}
