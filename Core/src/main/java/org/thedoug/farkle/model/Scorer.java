package org.thedoug.farkle.model;

import java.util.List;

public interface Scorer {
    ScoringResult score(List<Integer> rolls);
}
