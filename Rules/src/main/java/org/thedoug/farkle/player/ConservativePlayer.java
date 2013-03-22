package org.thedoug.farkle.player;

import org.thedoug.farkle.model.GameContext;
import org.thedoug.farkle.model.Turn;

/**
 * Stops rolling when he has scored any points.
 */
public class ConservativePlayer extends AbstractPlayer {
    @Override
    public boolean shouldRollAgain(GameContext gameContext, Turn turn) {
        return false;
    }
}
