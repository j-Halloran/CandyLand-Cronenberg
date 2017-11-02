package org.CandyLand.view;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;

public class MainFrameTest {


    @Test
    public void rematchTest() {
        try {
            MainFrame mf = new MainFrame();
            MainFrame.reInitBoard(true);
            assertEquals(StatusBarPanel.getNumberOfPlayers(), 3);
        } catch (IOException e) {
            //System.out.print("");
            e.printStackTrace();
        }
    }

    /* Upon board initialization, select 4 players */
    @Test
    public void newGameTest() {
        try {
            MainFrame mf = new MainFrame();
            MainFrame.reInitBoard(false);
            // StatusBarPanel needs to be updated/reconstructed
            assertEquals(mf.getPlayerPosition(0), 0);
            assertEquals(mf.getPlayerPosition(1), 0);
            assertEquals(mf.getPlayerPosition(2), 0);
            assertEquals(mf.getPlayerPosition(3), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
}
=======













}
>>>>>>> f08ed871b61b55f41c6a94ec6ad07f5aaf90e13b
