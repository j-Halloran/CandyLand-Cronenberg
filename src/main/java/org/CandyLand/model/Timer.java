package org.CandyLand.model;

import java.time.Duration;

public class Timer {

    private static long time = 0;
    private static boolean running = false;
    private static long lastMillisIncremented;

    public static void start() {
        lastMillisIncremented = System.currentTimeMillis();
        running = true;
    }

    public static void stop() {
        running = false;
        time += System.currentTimeMillis() - lastMillisIncremented;
    }

    public static void reset() {
        stop();
        time = 0;
    }

    public static long getSeconds() {
        if (running) {
            long tempTime = System.currentTimeMillis();
            time += tempTime - lastMillisIncremented;
            lastMillisIncremented = tempTime;
        }
        return time / 1000;
    }
}