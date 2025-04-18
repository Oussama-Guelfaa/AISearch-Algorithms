package fr.emse.ai.search.bottle;

// Enum for the three allowed actions in the bottle problem: Fill, Empty, and Pour
public enum Actions {
    FILL("Fill"),
    EMPTY("Empty"),
    POUR("Pour");

    private final String name;

    Actions(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


    public static String fill(int bottleIndex) {
        return FILL + " " + bottleIndex;
    }


    public static String empty(int bottleIndex) {
        return EMPTY + " " + bottleIndex;
    }


    public static String pour(int fromIndex, int toIndex) {
        return POUR + " " + fromIndex + " to " + toIndex;
    }


    public static Actions getActionType(String actionStr) {
        if (actionStr.startsWith(FILL.toString())) {
            return FILL;
        } else if (actionStr.startsWith(EMPTY.toString())) {
            return EMPTY;
        } else if (actionStr.startsWith(POUR.toString())) {
            return POUR;
        }
        throw new IllegalArgumentException("Unknown action: " + actionStr);
    }
}
