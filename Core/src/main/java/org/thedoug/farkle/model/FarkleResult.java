package org.thedoug.farkle.model;

import org.thedoug.farkle.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FarkleResult {
    private final Map<Player, Integer> scores;
    private final Rules rules;

    public FarkleResult(Rules rules, Map<Player, Integer> scores) {
        this.rules = rules;
        this.scores = scores;
    }

    public List<Player> getWinners() {
        List<Player> winners = new ArrayList<Player>(2);
        for (Player player : scores.keySet()) {
            Integer playerScore = scores.get(player);
            if (playerScore >= rules.getWinningScore()) {
                winners.add(player);
            }
        }

        assert !winners.isEmpty() : "Nobody won";
        return winners;
    }

    @Override
    public String toString() {
        return "FarkleResult{" +
                "scores=" + scores +
                '}';
    }
}
