package org.thedoug.farkle.model;

public class RandomRollStrategy implements RollStrategy {

    private final int MIN = 1;
    private final int MAX = 6;

    @Override
    public int rollSingleDie() {
        return generateNumberInRange(MIN, MAX);
    }

    @Override
    public int getMinValue() {
        return MIN;
    }

    @Override
    public int getMaxValue() {
        return MAX;
    }

    private int generateNumberInRange(int min, int max) {
        return (int) (Math.random() * (max)) + min;
    }
}
