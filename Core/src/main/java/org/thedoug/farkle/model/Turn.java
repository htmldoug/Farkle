package org.thedoug.farkle.model;

public class Turn {
    private int scoredPoints;
    private int remainingDice;
    private int rollIteration;

    public Turn(int scoredPoints, int remainingDice, int rollIteration) {
        this.scoredPoints = scoredPoints;
        this.remainingDice = remainingDice;
        this.rollIteration = rollIteration;
    }

    public boolean canRollAgain() {
        return remainingDice > 0;
    }

    public int getScoredPoints() {
        return scoredPoints;
    }

    public int getRemainingDice() {
        return remainingDice;
    }

    public int getRollIteration() {
        return rollIteration;
    }

    @Override
    public String toString() {
        return "Turn{" +
                "scoredPoints=" + scoredPoints +
                ", remainingDice=" + remainingDice +
                ", rollIteration=" + rollIteration +
                '}';
    }

    public static Turn farkled(int rollIteration) {
        return new Turn(0, 0, rollIteration);
    }
}
