package fr.emse.ai.search.bottle;

import fr.emse.ai.search.core.Problem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Implementation of the bottle problem with actions: fill, empty, pour
public class BottleProblem implements Problem {

    private Bottles initialState;


    public BottleProblem(int[] bottleCapacities, int targetVolume) {
        int[] initialVolumes = new int[bottleCapacities.length];
        this.initialState = new Bottles(initialVolumes, bottleCapacities, targetVolume);
    }

    @Override
    public Object getInitialState() {
        return initialState;
    }

    @Override
    public boolean isGoal(Object state) {
        if (state instanceof Bottles) {
            return ((Bottles) state).hasTargetVolume();
        }
        return false;
    }

    @Override
    public Collection<Object> getActions(Object state) {
        if (!(state instanceof Bottles)) {
            return new ArrayList<>();
        }

        Bottles bottles = (Bottles) state;
        ArrayList<Object> actions = new ArrayList<>();
        int[] volumes = bottles.getVolumes();
        int[] capacities = bottles.getCapacities();

        for (int i = 0; i < volumes.length; i++) {
            if (volumes[i] < capacities[i]) {
                actions.add(Actions.fill(i));
            }

            if (volumes[i] > 0) {
                actions.add(Actions.empty(i));
            }

            for (int j = 0; j < volumes.length; j++) {
                if (i != j && volumes[i] > 0 && volumes[j] < capacities[j]) {
                    actions.add(Actions.pour(i, j));
                }
            }
        }

        return actions;
    }

    @Override
    public Object getNextState(Object state, Object action) {
        if (!(state instanceof Bottles) || !(action instanceof String)) {
            return null;
        }

        Bottles bottles = (Bottles) state;
        String actionStr = (String) action;
        Bottles newBottles = new Bottles(bottles);
        int[] volumes = newBottles.getVolumes();
        int[] capacities = newBottles.getCapacities();

        Actions actionType = Actions.getActionType(actionStr);

        switch (actionType) {
            case FILL:
                Pattern fillPattern = Pattern.compile("Fill (\\d+)");
                Matcher fillMatcher = fillPattern.matcher(actionStr);
                if (fillMatcher.find()) {
                    int bottleIndex = Integer.parseInt(fillMatcher.group(1));
                    if (bottleIndex >= 0 && bottleIndex < volumes.length) {
                        volumes[bottleIndex] = capacities[bottleIndex];
                    }
                }
                break;

            case EMPTY:
                Pattern emptyPattern = Pattern.compile("Empty (\\d+)");
                Matcher emptyMatcher = emptyPattern.matcher(actionStr);
                if (emptyMatcher.find()) {
                    int bottleIndex = Integer.parseInt(emptyMatcher.group(1));
                    if (bottleIndex >= 0 && bottleIndex < volumes.length) {
                        volumes[bottleIndex] = 0;
                    }
                }
                break;

            case POUR:
                Pattern pourPattern = Pattern.compile("Pour (\\d+) to (\\d+)");
                Matcher pourMatcher = pourPattern.matcher(actionStr);
                if (pourMatcher.find()) {
                    int fromIndex = Integer.parseInt(pourMatcher.group(1));
                    int toIndex = Integer.parseInt(pourMatcher.group(2));

                    if (fromIndex >= 0 && fromIndex < volumes.length &&
                        toIndex >= 0 && toIndex < volumes.length &&
                        fromIndex != toIndex) {

                        int availableSpace = capacities[toIndex] - volumes[toIndex];
                        int amountToPour = Math.min(volumes[fromIndex], availableSpace);

                        volumes[fromIndex] -= amountToPour;
                        volumes[toIndex] += amountToPour;
                    }
                }
                break;
        }

        return new Bottles(volumes, capacities, bottles.getTargetVolume());
    }

    @Override
    public double getStepCost(Object start, Object action, Object dest) {

        return 1;
    }
}
