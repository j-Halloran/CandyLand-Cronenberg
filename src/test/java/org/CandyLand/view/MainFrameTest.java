package org.CandyLand.view;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;

public class MainFrameTest {

//
//    @Test
//    public void rematchTest() {
//        try {
//            MainFrame mf = new MainFrame();
//            MainFrame.reInitBoard(true);
//            // StatusBarPanel needs to be updated/reconstructed
//            assertEquals(StatusBarPanel.getNumberOfPlayers(), 3);
//        } catch (IOException e) {
//            //System.out.print("");
//            e.printStackTrace();
//        }
//    }
//
//    /* Upon board initialization, select 4 players */
//    @Test
//    public void newGameTest() {
//        try {
//            MainFrame mf = new MainFrame();
//            MainFrame.reInitBoard(false);
//            // StatusBarPanel needs to be updated/reconstructed
//            assertEquals(mf.getPlayerPosition(0), 0);
//            assertEquals(mf.getPlayerPosition(1), 0);
//            assertEquals(mf.getPlayerPosition(2), 0);
//            assertEquals(mf.getPlayerPosition(3), 0);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    /*
    *US: Game Window Test
    *
    * windowExists() - confirm generation of game window
    * cardExists() - confirm visibility of card pile
    */
    @Test
    public void windowExists(){

        try {
            MainFrame mainFrame = new MainFrame(2);

            assertEquals(mainFrame.getFrame().isVisible(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void cardExists(){



        try {
//            Robot r = new Robot();
//            r.keyPress(java.awt.event.KeyEvent.VK_ENTER);
            MainFrame mainFrame = new MainFrame(2);
//            r.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
            assertEquals(mainFrame.getPanel().isVisible(), true);
        } catch (IOException e) {
            e.printStackTrace();
//        } catch (AWTException e) {
//            e.printStackTrace();
       }

    }












}
