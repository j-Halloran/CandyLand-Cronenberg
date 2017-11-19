package org.CandyLand.model;

import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import org.CandyLand.CardType;

public class CardDeckTest {

    static final int NUMBER_OF_CARDS = 70;
    static final int NUMBER_OF_SINGLES = 10;
    static final int NUMBER_OF_DOUBLES = 2;
    static final int NUMBER_OF_SKIP_TURN_CARDS = 5;
    static final int MAX_CARDS_EVER_EXPECTED_TO_BE_DRAWN = 1000;
    CardDeck deck;

    @Before
    public void createDeck() {
        deck = new CardDeck();
    }

    @Test
    public void cardCountTest() {
        int singleRedCount = 0;
        int singleYellowCount = 0;
        int singleBlueCount = 0;
        int singleGreenCount = 0;
        int singleOrangeCount = 0;
        int doubleRedCount = 0;
        int doubleYellowCount = 0;
        int doubleBlueCount = 0;
        int doubleGreenCount = 0;
        int doubleOrangeCount = 0;
        int skipTurnCount = 0;
        for (int i = 0; i < NUMBER_OF_CARDS; i++) {
            switch (deck.drawCard()) {
                case SINGLE_RED:
                    singleRedCount++;
                    break;
                case SINGLE_YELLOW:
                    singleYellowCount++;
                    break;
                case SINGLE_BLUE:
                    singleBlueCount++;
                    break;
                case SINGLE_GREEN:
                    singleGreenCount++;
                    break;
                case SINGLE_ORANGE:
                    singleOrangeCount++;
                    break;
                case DOUBLE_RED:
                    doubleRedCount++;
                    break;
                case DOUBLE_YELLOW:
                    doubleYellowCount++;
                    break;
                case DOUBLE_BLUE:
                    doubleBlueCount++;
                    break;
                case DOUBLE_GREEN:
                    doubleGreenCount++;
                    break;
                case DOUBLE_ORANGE:
                    doubleOrangeCount++;
                    break;
                case SKIP_TURN:
                    skipTurnCount++;
                    break;
            }
        }
        assertEquals(NUMBER_OF_SINGLES, singleRedCount);
        assertEquals(NUMBER_OF_SINGLES, singleYellowCount);
        assertEquals(NUMBER_OF_SINGLES, singleBlueCount);
        assertEquals(NUMBER_OF_SINGLES, singleGreenCount);
        assertEquals(NUMBER_OF_SINGLES, singleOrangeCount);
        assertEquals(NUMBER_OF_DOUBLES, doubleRedCount);
        assertEquals(NUMBER_OF_DOUBLES, doubleYellowCount);
        assertEquals(NUMBER_OF_DOUBLES, doubleBlueCount);
        assertEquals(NUMBER_OF_DOUBLES, doubleGreenCount);
        assertEquals(NUMBER_OF_DOUBLES, doubleOrangeCount);
        assertEquals(NUMBER_OF_SKIP_TURN_CARDS, skipTurnCount);
    }

    @Test
    public void deckNeverEmptyTest() {
        for (int i = 0; i < MAX_CARDS_EVER_EXPECTED_TO_BE_DRAWN; i++) {
            assertThat(deck.drawCard(), instanceOf(CardType.class));
        }
    }

    @Test
    public void shuffleTest() {
        for (int i = 0; i < NUMBER_OF_CARDS/2; i++) {
            deck.drawCard();
        }
        assertEquals(NUMBER_OF_CARDS - NUMBER_OF_CARDS/2, deck.getDeckSize());
        deck.shuffleDeck();
        assertEquals(NUMBER_OF_CARDS, deck.getDeckSize());
    }

    @Test
    public void isDeckEmptyTest() {
        for (int i = 0; i < NUMBER_OF_CARDS; i++) {
            deck.drawCard();
        }
        assertTrue(deck.isDeckEmpty());
        deck.drawCard();
        assertFalse(deck.isDeckEmpty());
    }
}
