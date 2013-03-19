package org.thedoug.farkle.player;

import org.thedoug.farkle.model.GameState;

public class LukePlayer implements Player {
    @Override
    public boolean shouldRollAgain(GameState gameState) {
        return false;  // TODO implement me!
    }
}
