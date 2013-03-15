package org.thedoug.farkle.model;

public interface Turn {
    int getScoredPoints();

    int getRemainingDice();

    int getRollNumber();
}
