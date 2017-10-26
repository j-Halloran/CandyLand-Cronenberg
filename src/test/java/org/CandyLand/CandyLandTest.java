package org.CandyLand;



import org.CandyLand.view.*;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;

public class CandyLandTest {

    /*
    * US: Deck of cards
    * */
    @Test
    public void cardTypeTest(){
        GraphicalCard gc = new GraphicalCard(CardType.BLUE);
        assertEquals(gc.getBackground(), Color.BLUE);
    }

    @Test
    public void deckExists(){
        //wait for desk generation class implementation
    }


    /*
    * US: Visible Avatar
     */
    @Test
    public void placeAvatar(){

        TokenSpace ts = new TokenSpace(Color.BLUE);
        Token token = new Token();
        ts.setToken(token);
        assertEquals(ts.getToken(), token);
    }

    @Test
    public void removeAvatar(){

        TokenSpace ts = new TokenSpace(Color.BLUE);
        Token token = new Token();
        ts.setToken(token);
        ts.removeToken();
        assertNull(ts.getToken());
    }

    @Test
    public void spaceLimit(){
        GamePathSpace gps = new GamePathSpace(Color.BLUE);

        Token toke1 = new Token();
        Token toke2 = new Token();
        Token toke3 = new Token();
        Token toke4 = new Token();
        Token toke5 = new Token();

        try {
            gps.addToken(toke1);
            gps.addToken(toke2);
            gps.addToken(toke3);
            gps.addToken(toke4);
            gps.addToken(toke5);
        }catch(NoSpaceForTokenException ns){
            assertNotNull(ns);
        }
    }

    /*
    * Board Exists Unit Tests
    */
    @Test
    public void numberOfSpaces71(){
        GraphicalBoard gb = new GraphicalBoard();
        assertEquals(gb.getPathSize(), 71);
    }

    @Test
    public void colorOfSpaces5(){
        GamePathSpace gsR = new GamePathSpace(Color.RED);
        GamePathSpace gsY = new GamePathSpace(Color.YELLOW);
        GamePathSpace gsB = new GamePathSpace(Color.BLUE);
        GamePathSpace gsG = new GamePathSpace(Color.GREEN);
        GamePathSpace gsO = new GamePathSpace(Color.ORANGE);

        assertEquals(gsR.getSpaceColor(), Color.RED);
        assertEquals(gsY.getSpaceColor(), Color.YELLOW);
        assertEquals(gsB.getSpaceColor(), Color.BLUE);
        assertEquals(gsG.getSpaceColor(), Color.GREEN);
        assertEquals(gsO.getSpaceColor(), Color.ORANGE);
    }









}