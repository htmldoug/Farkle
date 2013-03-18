package org.thedoug.farkle.model;

import java.util.Random;

public class RandomRoller implements Roller {

    private final int MIN = 1;
    private final int MAX = 6;

    private Random random;

    public RandomRoller() {
        random = new Random();
    }

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
        return random.nextInt(MAX) + min;
    }
}
