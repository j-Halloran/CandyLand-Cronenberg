package org.CandyLand;



import org.CandyLand.view.*;
import org.CandyLand.model.*;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.*;

public class CandyLandTest {

    /*
    * US: Deck of cards
    *
    * cardTypeTest() - test ability to set card to a certian color/type
    * deckExists() - test generation of card deck of size 60
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
        CardDeck cd = new CardDeck();
        assertEquals(cd.getDeckSize(), 60);
    }


    /*
    * US: Visible Avatar
    *
    * placeAvatar() - test creation of avatar, and palcement onto space
    * removeAvatar() - test ability to remove avatar from a space
    * spaceLimit() - test capacity and overflow of 4 avatars per space
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
    * US: Board Exists Unit Tests
    *
    * numberOfSpaces71() - confirm 71 spaces generated for board
    * colorOfSpaces5() - confirm abilty to set spaces to 5 specified colors
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
    * US: Turn Indicator test
    *
    * setNumberOfPLayers() - test ability to choose # of players, 2-4
    * cycelTurn() - confirm turn cycles from player to player (looping)
    *               upon clicking of card deck
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
    *US: Game Window Test
    *
    * windowExists() - confirm generation of game window
    * cardExists() - confirm visibility of card pile
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
    *US: Board Colors
    *
    * boardBackgroundColor() - verify that board background set to opaque
    * grandmaColor - verify grandmas house space set to a compliment of the
    *                5 game path space colors
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
    *US: Past Card
    *
    * discardCardAreaExists() - verify creation and visibility fo discard area
    * discardCard() - confirm that past cards are placed in the discard pile
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