package org.thedoug.farkle.player;

import org.thedoug.farkle.model.GameState;

/**
 * Rolls one additional time, then accepts his score.
 */
public class RollOncePlayer implements Player {
    @Override
    public boolean shouldRollAgain(GameState gameState) {
        return gameState.turnInfo().getRollNumber() < 1;
    }
}
