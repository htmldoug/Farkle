package org.thedoug.farkle.player;

import org.thedoug.farkle.model.GameState;

public class RandomPlayer extends AbstractPlayer {
    @Override
    public boolean shouldRollAgain(GameState gameState) {
        return Math.round(Math.random()) == 0;
    }
}
