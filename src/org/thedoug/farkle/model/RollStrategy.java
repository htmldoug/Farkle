package org.thedoug.farkle.model;

public interface RollStrategy {
    int rollSingleDie();
    int getMinValue();
    int getMaxValue();
}
