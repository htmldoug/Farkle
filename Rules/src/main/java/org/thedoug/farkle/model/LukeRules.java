package org.thedoug.farkle.model;

public class LukeRules implements Rules {
    @Override
    public Scorer getScorer() {
        return new LukeRulesScorer();
    }
}
