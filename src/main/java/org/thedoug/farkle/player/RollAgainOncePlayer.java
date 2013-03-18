package org.thedoug.farkle.player;

import org.thedoug.farkle.model.GameState;

/**
 * Rolls one additional time, then accepts his score.
 */
public class RollAgainOncePlayer extends AbstractPlayer {
    @Override
    public boolean shouldRollAgain(GameState gameState) {
        return gameState.turnInfo().getRollIteration() <= 1;
    }
}
