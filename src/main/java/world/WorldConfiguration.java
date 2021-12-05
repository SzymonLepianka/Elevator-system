package world;

public class WorldConfiguration {
    private int numberOfElevators;
    private Algorithm chosenAlgorithm;

    public int getNumberOfElevators() {
        return numberOfElevators;
    }

    public void setNumberOfElevators(int numberOfElevators) {
        this.numberOfElevators = numberOfElevators;
    }

    public Algorithm getChosenAlgorithm() {
        return chosenAlgorithm;
    }

    public void setChosenAlgorithm(Algorithm chosenAlgorithm) {
        this.chosenAlgorithm = chosenAlgorithm;
    }

    public String[] availableAlgorithms() {
        String[] availableAlgorithms = new String[Algorithm.values().length];
        for (int i = 0; i < Algorithm.values().length; i++) {
            availableAlgorithms[i] = String.valueOf(Algorithm.values()[i]);
        }
        return availableAlgorithms;
    }

    public enum Algorithm {
        FIRST_COME_FIRST_SERVED,
        OWN_PRIMITIVE_COST_CALCULATION
    }
}
