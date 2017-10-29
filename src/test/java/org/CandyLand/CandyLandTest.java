package org.CandyLand;



import org.CandyLand.view.*;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.*;

public class CandyLandTest {

    /*
    * US: Deck of cards
    * */
    @Test
    public void cardTypeTest(){
        GraphicalCard gc = new GraphicalCard(CardType.BLUE, false);
        assertEquals(gc.getBackground(), Color.BLUE);

        GraphicalCard gc2 = new GraphicalCard(CardType.RED, true);
        assertEquals(gc2.getBackground(), Color.RED);
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
    * US Board Exists Unit Tests
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


    /*
    * US Turn Indicator test
    */
    @Test
    public void setNumberOfPlayers(){
        StatusBarPanel sb = new StatusBarPanel(4);
        assertEquals(sb.getNumberOfPlayers(), 4);
    }

    @Test
    public void cycleTurn(){
        StatusBarPanel sb = new StatusBarPanel(4);

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
    /*
    *US Game Window Test
    */
    @Test
    public void windowExists(){

        try {
            MainFrame mainFrame = new MainFrame();
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
            MainFrame mainFrame = new MainFrame();
//            r.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
            assertEquals(mainFrame.getPanel().isVisible(), true);
        } catch (IOException e) {
            e.printStackTrace();
//        } catch (AWTException e) {
//            e.printStackTrace();
       }

    }

    /*
    *US Board Colors
    */

    @Test
    public void boardBackgroundColor(){
        GraphicalBoard b = new GraphicalBoard();
        assertEquals(b.getBackgroundColor(), new Color(0,0,0,0));
    }

    @Test
    public void grandmaColor(){
        GraphicalBoard b = new GraphicalBoard();
        assertEquals(b.getPath()[0].getSpaceColor(), Color.WHITE);

    }

    /*
    *US Past Card
    */

    @Test
    public void discardCardAreaExists(){
        CardPanel c = new CardPanel();
        assertNotNull(c.getDiscardPile());
    }

    @Test
    public void discardCard(){
        CardPanel c = new CardPanel();
        c.drawCard();
        assertNotEquals(c.getDiscardPile().size(), 0);
    }




}