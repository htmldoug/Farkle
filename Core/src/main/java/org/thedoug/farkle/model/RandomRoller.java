package org.thedoug.farkle.model;

import java.util.Random;

public class RandomRoller implements Roller {

    private final int min;
    private final int max;
    private final Random random;

    public RandomRoller() {
        this(1, 6, new Random());
    }

    public RandomRoller(int min, int max, Random random) {
        this.min = min;
        this.max = max;
        this.random = random;
    }

    @Override
    public int rollSingleDie() {
        return random.nextInt(max) + min;
    }
}
