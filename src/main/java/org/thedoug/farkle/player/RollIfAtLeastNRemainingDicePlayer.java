package org.thedoug.farkle.player;

import org.thedoug.farkle.model.GameState;

public class RollIfAtLeastNRemainingDicePlayer implements Player {
    int minDiceToRoll;

    public RollIfAtLeastNRemainingDicePlayer(int minDiceToRoll) {
        this.minDiceToRoll = minDiceToRoll;
    }

    @Override
    public boolean shouldRollAgain(GameState gameState) {
        return gameState.turnInfo().getRemainingDice() >= minDiceToRoll;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + minDiceToRoll + ")";
    }
}
