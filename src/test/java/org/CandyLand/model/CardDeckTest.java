package org.CandyLand.model;

import org.junit.Test;
import static org.junit.Assert.*;
import org.CandyLand.CardType;

public class CardDeckTest {

    static final int NUMBER_OF_CARDS = 60;
    static final int NUMBER_OF_SINGLES = 10;
    static final int NUMBER_OF_DOUBLES = 2;

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
        for (int i = 0; i < NUMBER_OF_CARDS; i++) {
            switch (CardDeck.drawCard()) {
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
    }
}