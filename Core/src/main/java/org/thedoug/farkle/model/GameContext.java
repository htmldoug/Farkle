package org.thedoug.farkle.model;

import org.thedoug.farkle.player.Player;

import java.util.Iterator;
import java.util.Map;

/**
 * Player accessible game information useful for making decisions (scores, etc.).
 */
public class GameContext {
    private final Rules rules;

    /*
     * NOTE: Don't expose this map at all.
     *
     * 1. Exposing Player instances would make copy-cat cheating possible.
     * 2. Players may not even be able to identify themselves in this map since a Player may be a composite of other
     *    players, so the underlying one making the decision will not be the same instance as the one in this map.
     */
    private final Map<Player, Integer> scoresView;

    private final Player player;

    public GameContext(Player player, Rules rules, Map<Player, Integer> scoresView) {
        this.player = player;
        this.rules = rules;
        this.scoresView = scoresView;
    }

    public Rules getRules() {
        return rules;
    }

    public Iterator<Integer> getOtherScores() {
        return new OtherScoreIterator(player);
    }

    public Integer getMyScore() {
        return scoresView.get(player);
    }

    class OtherScoreIterator implements Iterator<Integer> {
        private final Player excludeMe;

        private final Iterator<Map.Entry<Player, Integer>> scoreIterator;
        private Map.Entry<Player, Integer> next = null;

        OtherScoreIterator(Player excludeMe) {
            this.excludeMe = excludeMe;
            this.scoreIterator = scoresView.entrySet().iterator();
            getUnderlyingNext();
        }

        private void getUnderlyingNext() {
            while ((next = scoreIterator.next()) != null && next.getKey() != excludeMe) ;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Integer next() {
            Integer nextValue = next.getValue();
            getUnderlyingNext();
            return nextValue;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Can't remove another player's score. Nice try.");
        }
    }
}

