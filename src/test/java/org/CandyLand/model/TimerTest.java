package org.CandyLand.model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TimerTest {

    Timer timer;

    @Before
    public void createTimer() {
        timer = new Timer();
    }

    @Test
    public void timerStartTest() {
        timer.start();
        try {
            Thread.sleep(2000); // 2 seconds oughta do it.
        }
        catch (InterruptedException e) {
            fail("thread was interrupted");
        }
        assertTrue(timer.getSeconds() > 0);
    }

    @Test
    public void timerStopTest() {
        timer.start();
        try {
            Thread.sleep(2000); // 2 seconds oughta do it.
        }
        catch (InterruptedException e) {
            fail("thread was interrupted");
        }
        timer.stop();
        long seconds = timer.getSeconds();
        try {
            Thread.sleep(2000); // 2 seconds oughta do it.
        }
        catch (InterruptedException e) {
            fail("thread was interrupted");
        }
        assertEquals(seconds, timer.getSeconds());
    }

    @Test
    public void resetTest() {
        timer.start();
        try {
            Thread.sleep(2000); // 2 seconds oughta do it.
        }
        catch (InterruptedException e) {
            fail("thread was interrupted");
        }
        timer.reset();
        assertEquals(0, timer.getSeconds());
        try {
            Thread.sleep(2000); // 2 seconds oughta do it.
        }
        catch (InterruptedException e) {
            fail("thread was interrupted");
        }
        assertEquals(0, timer.getSeconds());
    }
}
