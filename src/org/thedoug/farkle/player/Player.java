package org.thedoug.farkle.player;

import org.thedoug.farkle.model.GameState;

public interface Player {
    public boolean shouldRollAgain(GameState gameState);
}
