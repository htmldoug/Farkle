package org.thedoug.farkle;

import org.thedoug.farkle.doug.DougPlayer;
import org.thedoug.farkle.model.FarkleResult;
import org.thedoug.farkle.model.LukeRulesScorer;
import org.thedoug.farkle.model.RandomRoller;
import org.thedoug.farkle.player.InstrumentedPlayer;
import org.thedoug.farkle.player.Player;
import org.thedoug.farkle.player.RollIfAtLeastNRemainingDicePlayer;

import java.util.LinkedHashMap;
import java.util.Map;

public class Farkle {
    public static void main(String[] args) {
        RandomRoller rollStrategy = new RandomRoller();
        LukeRulesScorer scorer = new LukeRulesScorer();

        Player[] players = new Player[]{
                InstrumentedPlayer.from(new DougPlayer()),
                InstrumentedPlayer.from(new RollIfAtLeastNRemainingDicePlayer(3)), // Champion!
        };

        Map<Player, Integer> scores = new LinkedHashMap<Player, Integer>();
        for (Player player : players) {
            scores.put(player, 0);
        }

        Reporter reporter = new Reporter();
        for (int i = 0; i < 10; i++) {
            FarkleEngine gameEngine = new FarkleEngine(rollStrategy, scorer, players);
            FarkleResult result = gameEngine.run();

            Player winner = result.getWinner();
            scores.put(winner, scores.get(winner) + 1);

            reporter.report(i);
        }

        for (Map.Entry<Player,Integer> score: scores.entrySet()) {
            System.out.println(score.getValue() + ": " + score.getKey());
        }
    }

    static class Reporter {

        long lastReport = System.currentTimeMillis();
        int lastIteration = 0;

        public void report(int i) {
            long now = System.currentTimeMillis();
            long elapsed = now - lastReport;
            if (elapsed > 5000) {
                lastReport = now;
                long iterationsPerSecond = (i - lastIteration) * 1000 / elapsed;
                lastIteration = i;
                System.out.println(i + " matches (" + iterationsPerSecond + "/sec)");
            }
        }
    }
}
