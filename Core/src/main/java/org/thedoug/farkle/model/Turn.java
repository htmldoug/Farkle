package org.thedoug.farkle.model;

public class Turn {
    private int scoredPoints = 0;
    private int remainingDice = 5;
    private int rollIteration = 0;

        public Turn() {
        }

        public Turn(int scoredPoints, int remainingDice, int rollIteration) {
            this.scoredPoints = scoredPoints;
            this.remainingDice = remainingDice;
            this.rollIteration = rollIteration;
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
}
