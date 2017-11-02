package org.CandyLand.view;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import org.CandyLand.CardType;

public class CardPanelTest {

    CardPanel cardPanel;
    static final int NUMBER_OF_CARDS_IN_DECK = 68;

    @Before
    public void preactions() {
        cardPanel = new CardPanel();
    }

    @Test
    public void testShuffleEnableDisableCards() {
        for (int i = 0; i < NUMBER_OF_CARDS_IN_DECK; i++) {
            cardPanel.drawCard();
        }
        assertFalse(cardPanel.drawPile.isEnabled());
        assertTrue(cardPanel.discardPile.isEnabled());
        cardPanel.shuffleDeck();
        assertTrue(cardPanel.drawPile.isEnabled());
        assertFalse(cardPanel.discardPile.isEnabled());
    }

    @Test
    public void shuffleCardTypeTest() {
        for (int i = 0; i < NUMBER_OF_CARDS_IN_DECK; i++) {
            cardPanel.drawCard();
        }
        assertEquals(CardType.EMPTY_DRAW, cardPanel.drawPile.getCardType());
        assertNotEquals(CardType.EMPTY_DISCARD, cardPanel.discardPile.getCardType());
        cardPanel.shuffleDeck();
        assertEquals(CardType.EMPTY_DISCARD, cardPanel.discardPile.getCardType());
        assertEquals(CardType.UPSIDEDOWN, cardPanel.drawPile.getCardType());
    }
}
