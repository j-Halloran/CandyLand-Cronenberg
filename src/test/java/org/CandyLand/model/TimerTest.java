package org.CandyLand.model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TimerTest {

    // make sure the timer is fresh for each test
    @Before
    public void resetTimer() {
        Timer.reset();
    }

    @Test
    public void timerStartTest() {
        Timer.start();
        try {
            Thread.sleep(2000); // 2 seconds oughta do it.
        }
        catch (InterruptedException e) {
            fail("thread was interrupted");
        }
        assertTrue(Timer.getSeconds() > 0);
    }

    @Test
    public void timerStopTest() {
        Timer.start();
        try {
            Thread.sleep(2000); // 2 seconds oughta do it.
        }
        catch (InterruptedException e) {
            fail("thread was interrupted");
        }
        Timer.stop();
        long seconds = Timer.getSeconds();
        try {
            Thread.sleep(2000); // 2 seconds oughta do it.
        }
        catch (InterruptedException e) {
            fail("thread was interrupted");
        }
        assertEquals(seconds, Timer.getSeconds());
    }

    @Test
    public void resetTest() {
        Timer.start();
        try {
            Thread.sleep(2000); // 2 seconds oughta do it.
        }
        catch (InterruptedException e) {
            fail("thread was interrupted");
        }
        Timer.reset();
        assertEquals(0, Timer.getSeconds());
        try {
            Thread.sleep(2000); // 2 seconds oughta do it.
        }
        catch (InterruptedException e) {
            fail("thread was interrupted");
        }
        assertEquals(0, Timer.getSeconds());
    }
}