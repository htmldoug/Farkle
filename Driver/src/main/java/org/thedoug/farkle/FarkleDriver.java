package org.thedoug.farkle;

import org.lab304.farlke.player.BrandonPlayer;
import org.thedoug.farkle.doug.DougPlayerV1;
import org.thedoug.farkle.doug.DougPlayerV2;
import org.thedoug.farkle.doug.DougPlayerV3;
import org.thedoug.farkle.model.FarkleResult;
import org.thedoug.farkle.model.LukeRules;
import org.thedoug.farkle.player.*;

public class FarkleDriver {

    public static final int NUM_MATCHES = 100000;

    public static void main(String[] args) {
        LukeRules rules = new LukeRules();

        Player[] players = InstrumentedPlayer.instrumentAll(new Player[]{
                new BrandonPlayer(),
                new DougPlayerV1(rules),
                new DougPlayerV2(rules),
                new DougPlayerV3(rules),
                new RollIfAtLeastNRemainingDicePlayer(2),
                new RollIfAtLeastNRemainingDicePlayer(3),
                new RollIfAtLeastNRemainingDicePlayer(4),
                new ConservativePlayer(),
                new RollAgainOncePlayer(),
                new LukePlayer(),
                new RandomPlayer(),
        });

        WinsTally totalScores = new WinsTally(players);

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
