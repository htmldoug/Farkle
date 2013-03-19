package org.thedoug.farkle;

public class PeriodicProgressReporter {
    public static final int DEFAULT_REPORT_DURATION_MILLIS = 5000;
    private final int reportDurationMillis;

    private long lastReportTimestamp = System.currentTimeMillis();
    private int lastIteration = 0;

    public PeriodicProgressReporter() {
        this(DEFAULT_REPORT_DURATION_MILLIS);
    }

    public PeriodicProgressReporter(int reportDurationMillis) {
        this.reportDurationMillis = reportDurationMillis;
    }

    public void logIteration(int i) {
        long now = System.currentTimeMillis();
        long elapsed = now - lastReportTimestamp;
        if (elapsed > reportDurationMillis) {
            lastReportTimestamp = now;
            long iterationsPerSecond = (i - lastIteration) * 1000 / elapsed;
            lastIteration = i;
            System.out.println(i + " matches (" + iterationsPerSecond + "/sec)");
        }
    }
}