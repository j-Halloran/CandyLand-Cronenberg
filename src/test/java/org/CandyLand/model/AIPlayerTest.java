package org.CandyLand.model;

import org.CandyLand.view.StatusBarPanel;
import org.junit.Test;
import static org.junit.Assert.*;

public class AIPlayerTest {


    @Test
    public void threadGetNextPlayer() {
          Thread AIPlayerThread;
          boolean[] AIplayers = {false,true,false,true};
          String[] players = {"Mike1","Mike2","Mike3","Mike4"};
          StatusBarPanel stat = new StatusBarPanel(players,false);
        AIPlayerThread = new Thread() {
            public void run() {

                    if(AIplayers[stat.getCurrentPlayer()]){
                        StatusBarPanel.activateNextPlayer();
                        assertTrue(stat.getCurrentPlayer() == 1);
                    }
                    try {
                        Thread.sleep(500); // snooze for HALF a second
                    }
                    catch (InterruptedException e) {
                        // do nothing
                    }

            }
        };
        AIPlayerThread.start();
    }


    @Test
    public void threadCycleCorrectly() {
        Thread AIPlayerThread;
        boolean[] AIplayers = {true,true,true,true};
        String[] players = {"Mike1","Mike2","Mike3","Mike4"};
        StatusBarPanel stat = new StatusBarPanel(players,false);
        AIPlayerThread = new Thread() {
            public void run() {

                for (int i = 0; i < 4; i++) {

                    if (AIplayers[stat.getCurrentPlayer()]) {
                        StatusBarPanel.activateNextPlayer();
                        assertTrue(stat.getCurrentPlayer() == i+1);
                    }
                    try {
                        Thread.sleep(500); // snooze for HALF a second
                    } catch (InterruptedException e) {
                        // do nothing
                    }

                }
            }
        };
        AIPlayerThread.start();
    }


}
