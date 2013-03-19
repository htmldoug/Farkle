package org.thedoug.farkle.player;

import org.thedoug.farkle.model.GameState;

public interface Player {
    boolean shouldRollAgain(GameState gameState);
}
