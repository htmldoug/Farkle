package org.thedoug.farkle.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LukeRulesScorer implements Scorer {
    private static final int MAX_ROLLABLE_DICE = 5;

    private RollStrategy rollStrategy;

    public LukeRulesScorer(RollStrategy rollStrategy) {
        this.rollStrategy = rollStrategy;
    }

    @Override
    public ScoringResult score(List<Integer> rolls) {
        Map<Integer, Integer> counts = countDice(rolls);

        int score = 0;

        // Consider and remove all sets of 3.
        score += scoreAndRemoveTriples(counts);

        // Single 1's
        score += scoreAndRemoveIndividuals(1, 100, counts);

        // Single 5's
        score += scoreAndRemoveIndividuals(5, 50, counts);

        int remainingDice;
        if (score == 0) {
            remainingDice = 0;
        } else {
            remainingDice = countRemainingDice(counts);
            if (remainingDice == 0) {
                remainingDice = MAX_ROLLABLE_DICE;
            }
        }

        return new ScoringResult(score, remainingDice);
    }

    private int scoreAndRemoveIndividuals(int dieOption, int valueForEach, Map<Integer, Integer> counts) {
        int individualScore = 0;
        individualScore += counts.get(dieOption) * valueForEach;
        counts.put(dieOption, 0);
        return individualScore;
    }

    private int scoreAndRemoveTriples(Map<Integer, Integer> counts) {
        int triplesScore = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            Integer dieOption = entry.getKey();

            while (entry.getValue() >= 3) {
                if (dieOption == 1) {
                    triplesScore += 1000;
                } else {
                    triplesScore += 100 * dieOption;
                }

                entry.setValue(entry.getValue() - 3);
            }
        }
        return triplesScore;
    }

    private int countRemainingDice(Map<Integer, Integer> counts) {
        int remainingDice = 0;
        for (Integer remainingCount : counts.values()) {
            remainingDice += remainingCount;
        }
        return remainingDice;
    }

    private Map<Integer, Integer> countDice(List<Integer> rolls) {
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int i = rollStrategy.getMinValue(); i <= rollStrategy.getMaxValue(); i++) {
            counts.put(i, 0);
        }
        for (Integer roll: rolls) {
            Integer old = counts.get(roll);
            if (old == null) {
                old = 0;
            }
            counts.put(roll, old + 1);
        }
        return counts;
    }
}
