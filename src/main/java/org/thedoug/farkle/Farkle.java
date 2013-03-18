package org.thedoug.farkle;

import org.thedoug.farkle.model.GameResult;
import org.thedoug.farkle.model.LukeRulesScorer;
import org.thedoug.farkle.model.RandomRoller;
import org.thedoug.farkle.player.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class Farkle {
    public static void main(String[] args) {
        RandomRoller rollStrategy = new RandomRoller();
        LukeRulesScorer scorer = new LukeRulesScorer(rollStrategy);

        Player[] players = new Player[]{
                new RollIfAtLeastNRemainingDicePlayer(2),
                new RollIfAtLeastNRemainingDicePlayer(3), // Champion!
                new RollIfAtLeastNRemainingDicePlayer(4),
                new RollIfAtLeastNRemainingDicePlayer(5),
                new RollAgainOncePlayer(),
                new RandomPlayer(),
                new ConservativePlayer(),
        };

        Map<Player, Integer> score = new LinkedHashMap<Player, Integer>();
        for (Player player : players) {
            score.put(player, 0);
        }

        for (int i = 0; i < 10000; i++) {
            GameEngine gameEngine = new GameEngine(rollStrategy, scorer, players);
            GameResult result = gameEngine.run();

            Player winner = result.getWinner();
            score.put(winner, score.get(winner) + 1);
        }

        System.out.println(score);
    }
}
