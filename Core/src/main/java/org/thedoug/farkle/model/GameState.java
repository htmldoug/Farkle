package org.thedoug.farkle.model;

public class GameState {
    private Turn turnInfo;

    public GameState() {
        this(new Turn());
    }

    public GameState(Turn turnInfo) {
        this.turnInfo = turnInfo;
    }

    public Turn turnInfo() {
        return turnInfo;
    }

    public boolean canRollAgain() {
        return turnInfo().getRemainingDice() > 0;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "turnInfo=" + turnInfo +
                '}';
    }
}

