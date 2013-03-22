package org.thedoug.farkle.player;

import org.thedoug.farkle.model.GameContext;
import org.thedoug.farkle.model.Turn;

/**
 * Rolls one additional time, then accepts his score.
 */
public class RollAgainOncePlayer extends AbstractPlayer {
    @Override
    public boolean shouldRollAgain(GameContext gameContext, Turn turn) {
        return turn.getRollIteration() <= 1;
    }
}
