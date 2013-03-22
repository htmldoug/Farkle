package org.thedoug.farkle.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Roller {

    public List<Integer> rollDice(int diceToRoll) {
        List<Integer> rolls = new ArrayList<Integer>();
        for (int i = 0; i < diceToRoll; i++) {
            int result = rollSingleDie();
            rolls.add(result);
        }
        return rolls;
    }

    abstract protected int rollSingleDie();
}
