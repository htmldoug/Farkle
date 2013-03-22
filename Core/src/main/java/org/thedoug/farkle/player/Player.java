package org.thedoug.farkle.player;

import org.thedoug.farkle.model.GameContext;
import org.thedoug.farkle.model.Turn;

public interface Player {
    boolean shouldRollAgain(GameContext gameContext, Turn turn);
}
