package org.CandyLand.view;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by 15mik_000 on 11/7/2017.
 */
public class StatusBarPanelTest {

    private String[] tempNames = {"Test1","Test2","Test3","Bob"};
    /*
    * US: Turn Indicator test
    *
    * setNumberOfPLayers() - test ability to choose # of players, 2-4
    * cycelTurn() - confirm turn cycles from player to player (looping)
    *               upon clicking of card deck
    */
    @Test
    public void setNumberOfPlayers(){
        StatusBarPanel sb = new StatusBarPanel(tempNames,false);
        assertEquals(sb.getNumberOfPlayers(), 4);
    }

    @Test
    public void cycleTurn(){
        StatusBarPanel sb = new StatusBarPanel(tempNames,false);

        try {
            assertEquals(sb.getCurrentTurn(1), 0);
            sb.activateNextPlayer();
            assertEquals(sb.getCurrentTurn(2), 0);
            sb.activateNextPlayer();
            assertEquals(sb.getCurrentTurn(3), 0);
            sb.activateNextPlayer();
            assertEquals(sb.getCurrentTurn(4), 0);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}