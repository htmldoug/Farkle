package org.thedoug.farkle;

import org.thedoug.farkle.doug.DougPlayerV1;
import org.thedoug.farkle.doug.DougPlayerV2;
import org.thedoug.farkle.doug.DougPlayerV3;
import org.thedoug.farkle.model.FarkleResult;
import org.thedoug.farkle.model.LukeRules;
import org.thedoug.farkle.model.Rules;
import org.thedoug.farkle.player.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MultithreadedFarkleDriver {
    public static void main(String[] args) throws InterruptedException {
        int numMatches = 1000000;

        Rules rules = new LukeRules();

        Player[] players = InstrumentedPlayer.instrumentAll(new Player[]{
                new DougPlayerV1(rules), // TODO: not thread-safe!
                new DougPlayerV2(rules), // TODO: not thread-safe!
                new DougPlayerV3(rules), // TODO: not thread-safe!
                new RollIfAtLeastNRemainingDicePlayer(2),
                new RollIfAtLeastNRemainingDicePlayer(3),
                new RollIfAtLeastNRemainingDicePlayer(4),
                new ConservativePlayer(),
                new RollAgainOncePlayer(),
                new LukePlayer(),
                new RandomPlayer(),
        });

        new MultithreadedFarkleDriver(rules, players, numMatches).run();
    }

    private final Rules rules;
    private final Player[] players;
    private final int numMatches;

    private final BlockingQueue<FarkleResult> queue = new LinkedBlockingQueue<FarkleResult>();

    private boolean runMoreMatches = true;

    public MultithreadedFarkleDriver(Rules rules, Player[] players, int numMatches) {
        this.rules = rules;
        this.players = players;
        this.numMatches = numMatches;
    }

    public void run() throws InterruptedException {
        startWorkerThreads();

        WinsTally winsTally = tallyWins();

        stopWorkerThreads();

        winsTally.printReport();
    }

    private WinsTally tallyWins() throws InterruptedException {
        PeriodicProgressReporter progressReporter = new PeriodicProgressReporter();
        WinsTally totalScores = new WinsTally(players);
        for (int completed = 0; completed < numMatches; completed++) {
            totalScores.add(queue.take());
            progressReporter.logIteration(completed);
        }
        return totalScores;
    }

    private void startWorkerThreads() {
        int cores = Runtime.getRuntime().availableProcessors();
        ThreadGroup farkleEngines = new ThreadGroup("Farkle engines");
        for (int i = 0; i < cores; i++) {
            new Thread(farkleEngines, new FarkleRunnable()).start();
        }
    }

    private void stopWorkerThreads() {
        runMoreMatches = false;
    }

    private class FarkleRunnable implements Runnable {
        @Override
        public void run() {
            while (runMoreMatches) {
                queue.add(new FarkleEngine(rules, players).run());
            }
        }
    }
}
