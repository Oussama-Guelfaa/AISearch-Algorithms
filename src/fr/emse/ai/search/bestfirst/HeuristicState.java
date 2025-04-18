package fr.emse.ai.search.bestfirst;

import fr.emse.ai.search.simple.SimpleState;

// A state with heuristic values for Best First Search, inherits from SimpleState
public class HeuristicState extends SimpleState {

    public static final int H_VALUE_A = 5;
    public static final int H_VALUE_B = 4;
    public static final int H_VALUE_C = 4;
    public static final int H_VALUE_D = 3;
    public static final int H_VALUE_E = 2;
    public static final int H_VALUE_F = 1;
    public static final int H_VALUE_G = 1;
    public static final int H_VALUE_H = 0;

    public HeuristicState(String value) {
        super(value);
    }
    public int getHValue() {
        if (value.equals(A)) return H_VALUE_A;
        if (value.equals(B)) return H_VALUE_B;
        if (value.equals(C)) return H_VALUE_C;
        if (value.equals(D)) return H_VALUE_D;
        if (value.equals(E)) return H_VALUE_E;
        if (value.equals(F)) return H_VALUE_F;
        if (value.equals(G)) return H_VALUE_G;
        if (value.equals(H)) return H_VALUE_H;

        // Default value for unknown states
        return Integer.MAX_VALUE;
    }

    @Override
    public String toString() {
        return value + "(h=" + getHValue() + ")";
    }
}
