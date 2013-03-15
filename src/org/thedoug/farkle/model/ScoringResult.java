package org.thedoug.farkle.model;

public class ScoringResult {
    private int score;
    private int remainingDice;

    public ScoringResult(int score, int remainingDice) {
        this.score = score;
        this.remainingDice = remainingDice;
    }

    public int getScore() {
        return score;
    }

    public int getRemainingDice() {
        return remainingDice;
    }
}
