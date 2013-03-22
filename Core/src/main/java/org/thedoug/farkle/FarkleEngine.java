package org.thedoug.farkle;

import org.thedoug.farkle.model.*;
import org.thedoug.farkle.player.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Runs the game against the provided players.
 */
public class FarkleEngine {

    private final GameContexts gameContexts;

    private Player[] players;

    private final Map<Player, Integer> scores;

    private final Roller roller = new RandomRoller();

    private final Rules rules;

    private final Turn INITIAL_TURN;

    public FarkleEngine(Rules rules, Player... players) {
        this.players = players;
        this.rules = rules;
        this.scores = getInitialScores(players);
        this.gameContexts = new GameContexts(players);

        this.INITIAL_TURN = new Turn(0, rules.getNumDice(), 0);
    }

    public FarkleResult run() {
        while (!somePlayerHasWon()) {
            for (Player player : players) {
                Turn turn = roll(INITIAL_TURN);

                while (turn.canRollAgain() && player.shouldRollAgain(gameContexts.forPlayer(player), turn)) {
                    turn = roll(turn);
                }

                updateScoreForTurn(player, turn);
            }
        }

        return new FarkleResult(rules, scores);
    }

    private void updateScoreForTurn(Player player, Turn turnInfo) {
        int newScore = scores.get(player) + turnInfo.getScoredPoints();
        scores.put(player, newScore);
    }

    private Map<Player, Integer> getInitialScores(Player[] players) {
        Map<Player, Integer> initialScores = new HashMap<Player, Integer>();

        for (Player player : players) {
            initialScores.put(player, 0);
        }

        return initialScores;
    }

    private Turn roll(Turn previousState) {
        assert previousState.canRollAgain();

        int nextRollIteration = previousState.getRollIteration() + 1;

        List<Integer> rolls = roller.rollDice(previousState.getRemainingDice());
        ScoringResult result = rules.getScorer().score(rolls);

        Turn nextState;
        if (result.isFarkled()) {
            nextState = Turn.farkled(nextRollIteration);
        } else {
            int previousScore = previousState.getScoredPoints();
            int newScore = previousScore + result.getScore();

            nextState = new Turn(newScore, result.getRemainingDice(), nextRollIteration);
        }

        return nextState;
    }


    private boolean hasWon(Player player) {
        return scores.get(player) >= rules.getWinningScore();
    }

    private boolean somePlayerHasWon() {
        for (Player player : players) {
            if (hasWon(player)) {
                return true;
            }
        }
        return false;
    }

    private class GameContexts {
        private final Map<Player, GameContext> cache;

        public GameContexts(Player[] players) {
            Map<Player, Integer> scoresView = Collections.unmodifiableMap(scores);

            cache = new HashMap<Player, GameContext>(players.length);
            for (Player player : players) {
                cache.put(player, new GameContext(player, rules, scoresView));
            }
        }

        public GameContext forPlayer(Player player) {
            return cache.get(player);
        }
    }
}
