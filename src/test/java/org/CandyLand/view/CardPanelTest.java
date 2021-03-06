package org.CandyLand.view;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import org.CandyLand.CardType;

import javax.swing.*;

public class CardPanelTest {

    CardPanel cardPanel;
    static final int NUMBER_OF_CARDS_IN_DECK = 68;

    @Before
    public void preactions() {
        cardPanel = new CardPanel(false);
    }

    @Test
    public void testShuffleEnableDisableCards() {
        cardPanel.setDeckEmpty();
        assertFalse(cardPanel.drawPile.isEnabled());
        assertTrue(cardPanel.discardPile.isEnabled());
        cardPanel.shuffleDeck();
        assertTrue(cardPanel.drawPile.isEnabled());
        assertFalse(cardPanel.discardPile.isEnabled());
    }

    @Test
    public void shuffleCardTypeTest() {
        cardPanel.setDeckEmpty();
        assertEquals(CardType.EMPTY_DRAW, cardPanel.drawPile.getCardType());
        cardPanel.shuffleDeck();
        assertEquals(CardType.EMPTY_DISCARD, cardPanel.discardPile.getCardType());
        assertEquals(CardType.UPSIDEDOWN, cardPanel.drawPile.getCardType());
    }

        /*
    *US: Past Card
    *
    * discardCardAreaExists() - verify creation and visibility fo discard area
    * discardCard() - confirm that past cards are placed in the discard pile
    */

    @Test
    public void discardCardAreaExists(){
        CardPanel c = new CardPanel(false);
        assertNotNull(c.getDiscardPile());
    }

    @Test
    public void discardCard(){
        CardPanel c = new CardPanel(false);
        c.setCurrentCard(new GraphicalCard(CardType.SINGLE_RED));
        assertEquals(c.getDiscardPile().getCardType(), CardType.SINGLE_RED);
    }

    @Test
    public void boomerangButton(){
        CardPanel c = new CardPanel(true);
        assertEquals(3,c.getComponentCount());
        c = new CardPanel(false);
        assertEquals(2,c.getComponentCount());
    }

}
