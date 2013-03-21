package org.thedoug.farkle.model;

import java.util.List;

public class LukeRulesScorer implements Scorer {
// TODO separate out farkleing knowledge into a PostScoringRules for more flexibility

    public static final int MAX_ROLLABLE_DICE = 5;
    public static final int MIN_DIE_VALUE = 1;
    public static final int MAX_DIE_VALUE = 6;

    private FaceCounter faceCounter = new FaceCounter(MIN_DIE_VALUE, MAX_DIE_VALUE);

    @Override
    public ScoringResult score(List<Integer> rolls) {
        FaceCounter.FaceCount faceCounts = faceCounter.count(rolls);
        int score = scoreAndRemoveScoringDice(faceCounts);

        ScoringResult result = determineResult(faceCounts, score);
        return result;
    }

    private int scoreAndRemoveScoringDice(FaceCounter.FaceCount faceCounts) {
        int score = 0;

        // Consider and remove all sets of 3.
        score += scoreAndRemoveTriples(faceCounts);

        // Single 1's
        score += scoreAndRemoveIndividuals(1, 100, faceCounts);

        // Single 5's
        score += scoreAndRemoveIndividuals(5, 50, faceCounts);
        return score;
    }

    private int scoreAndRemoveIndividuals(int dieOption, int valueForEach, FaceCounter.FaceCount counts) {
        int individualScore = counts.get(dieOption) * valueForEach;
        counts.put(dieOption, 0);
        return individualScore;
    }

    private int scoreAndRemoveTriples(FaceCounter.FaceCount counts) {
        int triplesScore = 0;

        for (Integer face : counts) {
            int count;
            while ((count = counts.get(face)) >= 3) {
                triplesScore += scoreForTriple(face);
                counts.put(face, count - 3);
            }
        }
        return triplesScore;
    }

    private int scoreForTriple(int dieValue) {
        if (dieValue == 1) {
            return 1000;
        } else {
            return 100 * dieValue;
        }
    }

    private ScoringResult determineResult(FaceCounter.FaceCount faceCounts, int score) {
        ScoringResult result;
        if (score == 0) {
            result = ScoringResult.FARKLED;
        } else {
            result = new ScoringResult(score, determineRemainingDice(faceCounts));
        }
        return result;
    }

    private int determineRemainingDice(FaceCounter.FaceCount faceCounts) {
        int remainingDice = faceCounts.totalCounts();
        if (remainingDice == 0) {
            // "hot dice", player may roll all dice again.
            remainingDice = MAX_ROLLABLE_DICE;
        }
        return remainingDice;
    }
}
