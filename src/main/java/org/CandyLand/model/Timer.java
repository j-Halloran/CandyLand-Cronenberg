package org.CandyLand.model;

public class Timer implements java.io.Serializable {

    private long time = 0;
    private boolean running = false;
    private long lastMillisIncremented;

    public void start() {
        lastMillisIncremented = System.currentTimeMillis();
        running = true;
    }

    public void stop() {
        running = false;
        time += System.currentTimeMillis() - lastMillisIncremented;
    }

    public void reset() {
        stop();
        time = 0;
    }

    public long getSeconds() {
        if (running) {
            long tempTime = System.currentTimeMillis();
            time += tempTime - lastMillisIncremented;
            lastMillisIncremented = tempTime;
        }
        return time / 1000;
    }

    public boolean isRunning() {
        return running;
    }
}
