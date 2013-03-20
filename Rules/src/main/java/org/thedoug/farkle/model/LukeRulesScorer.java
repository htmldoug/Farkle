package org.thedoug.farkle.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LukeRulesScorer implements Scorer {

    public static final int MAX_ROLLABLE_DICE = 5;
    public static final int MIN_DIE_VALUE = 1;
    public static final int MAX_DIE_VALUE = 6;

    @Override
    public ScoringResult score(List<Integer> rolls) {
        Map<Integer, Integer> faceCounts = countDice(rolls);

        int score = 0;

        // Consider and remove all sets of 3.
        score += scoreAndRemoveTriples(faceCounts);

        // Single 1's
        score += scoreAndRemoveIndividuals(1, 100, faceCounts);

        // Single 5's
        score += scoreAndRemoveIndividuals(5, 50, faceCounts);

        int remainingDice;
        if (score == 0) {
            remainingDice = 0;
        } else {
            remainingDice = countRemainingDice(faceCounts);
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

    /**
     * Would convert [1,1,3,4,5] to Map(1->2, 2->0, 3->1, 4->1, 5->1, 6->0).
     */
    private Map<Integer, Integer> countDice(List<Integer> rolls) {
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int i = MIN_DIE_VALUE; i <= MAX_DIE_VALUE; i++) {
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
