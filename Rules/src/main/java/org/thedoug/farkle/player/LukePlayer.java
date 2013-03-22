package org.thedoug.farkle.player;

import org.thedoug.farkle.model.GameContext;
import org.thedoug.farkle.model.Turn;

public class LukePlayer implements Player {
    @Override
    public boolean shouldRollAgain(GameContext gameContext, Turn turn) {
        return false;  // TODO implement me!
    }
}
