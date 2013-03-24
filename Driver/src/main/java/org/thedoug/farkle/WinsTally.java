package org.thedoug.farkle;

import org.thedoug.farkle.model.FarkleResult;
import org.thedoug.farkle.player.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Tallied wins from multiple Farkle matches.
 */
public class WinsTally {
    // TODO switch Map to identity in case somebody implements equals() {return true}
    private final Map<Player, Integer> wins;

    public WinsTally(Player[] players) {
        wins = initialScores(players);
    }

    private Map<Player, Integer> initialScores(Player[] players) {
        Map<Player, Integer> scores = new LinkedHashMap<Player, Integer>();
        for (Player player : players) {
            scores.put(player, 0);
        }
        return scores;
    }

    public void add(FarkleResult result) {
        List<Player> winners = result.getWinners();
        for (Player winner : winners) {
            wins.put(winner, wins.get(winner) + 1);
        }
    }

    public void printReport() {
        for (Map.Entry<Player, Integer> score : wins.entrySet()) {
            System.out.println(score.getValue() + " wins : " + score.getKey());
        }
    }
}
