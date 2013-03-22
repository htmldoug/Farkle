package org.thedoug.farkle.model;

public class LukeRules implements Rules {
    @Override
    public Scorer getScorer() {
        return new LukeRulesScorer();
    }

    @Override
    public int getWinningScore() {
        return 10000;
    }

    @Override
    public int getNumDice() {
        return 5;
    }
}
