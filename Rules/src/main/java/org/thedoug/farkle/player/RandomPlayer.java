package org.thedoug.farkle.player;

import org.thedoug.farkle.model.GameContext;
import org.thedoug.farkle.model.Turn;

public class RandomPlayer extends AbstractPlayer {
    @Override
    public boolean shouldRollAgain(GameContext gameContext, Turn turn) {
        return Math.round(Math.random()) == 0;
    }
}
