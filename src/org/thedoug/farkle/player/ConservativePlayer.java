package org.thedoug.farkle.player;

import org.thedoug.farkle.model.GameState;

/**
 * Stops rolling when he has scored any points.
 */
public class ConservativePlayer implements Player {
    @Override
    public boolean shouldRollAgain(GameState gameState) {
        return false;
    }
}
