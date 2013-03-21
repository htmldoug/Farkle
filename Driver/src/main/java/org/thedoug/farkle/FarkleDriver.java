package org.thedoug.farkle;

import org.thedoug.farkle.doug.DougPlayerV1;
import org.thedoug.farkle.model.FarkleResult;
import org.thedoug.farkle.model.LukeRules;
import org.thedoug.farkle.player.*;

public class FarkleDriver {

    public static final int NUM_MATCHES = 10000;

    public static void main(String[] args) {
        LukeRules rules = new LukeRules();

        Player[] players = InstrumentedPlayer.instrumentAll(new Player[]{
                new DougPlayerV1(rules),
                new RollIfAtLeastNRemainingDicePlayer(2),
                new RollIfAtLeastNRemainingDicePlayer(3),
                new RollIfAtLeastNRemainingDicePlayer(4),
                new ConservativePlayer(),
                new RollAgainOncePlayer(),
        });

        AggregatedScores totalScores = new AggregatedScores(players);

        PeriodicProgressReporter progressReporter = new PeriodicProgressReporter();
        for (int i = 0; i < NUM_MATCHES; i++) {
            FarkleEngine gameEngine = new FarkleEngine(rules, players);
            FarkleResult singleMatchResult = gameEngine.run();
            totalScores.add(singleMatchResult);

            progressReporter.logIteration(i);
        }

        totalScores.printReport();
    }
}
