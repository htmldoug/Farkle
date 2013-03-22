package org.thedoug.farkle.model;

public interface Rules {
    Scorer getScorer();

    int getWinningScore();

    int getNumDice();
}
