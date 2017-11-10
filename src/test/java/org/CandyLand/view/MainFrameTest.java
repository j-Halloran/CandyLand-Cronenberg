package org.CandyLand.view;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import org.CandyLand.model.GameBoard;

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
        MainFrame mainFrame = new MainFrame(new GameBoard(2));
        assertEquals(mainFrame.getFrame().isVisible(), true);
    }

    @Test
    public void cardExists(){
        MainFrame mainFrame = new MainFrame(new GameBoard(2));
        assertEquals(mainFrame.getPanel().isVisible(), true);
    }












}
