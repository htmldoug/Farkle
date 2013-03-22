package org.thedoug.farkle.player;

import org.thedoug.farkle.model.GameContext;
import org.thedoug.farkle.model.Turn;

public class RollIfAtLeastNRemainingDicePlayer extends AbstractPlayer {
    int minDiceToRoll;

    public RollIfAtLeastNRemainingDicePlayer(int minDiceToRoll) {
        this.minDiceToRoll = minDiceToRoll;
    }

    @Override
    public boolean shouldRollAgain(GameContext gameContext, Turn turn) {
        return turn.getRemainingDice() >= minDiceToRoll;
    }

    @Override
    public String toString() {
        return super.toString() + "(" + minDiceToRoll + ")";
    }
}
