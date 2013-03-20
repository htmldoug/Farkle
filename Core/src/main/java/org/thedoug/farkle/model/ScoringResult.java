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

    /**
     * @return true if the player has "Farkled", i.e. cannot roll again.
     */
    public boolean isFarkled() {
        return remainingDice <= 0;
    }

    @Override
    public String toString() {
        return "ScoringResult(" + score + ", " + remainingDice + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoringResult)) return false;

        ScoringResult that = (ScoringResult) o;

        return remainingDice == that.remainingDice && score == that.score;
    }

    @Override
    public int hashCode() {
        int result = score;
        result = 31 * result + remainingDice;
        return result;
    }
}
