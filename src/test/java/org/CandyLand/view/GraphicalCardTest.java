package org.CandyLand.view;

import org.junit.Test;
import static org.junit.Assert.*;
import org.CandyLand.CardType;

public class GraphicalCardTest {

    @Test
    public void skipTurnCardTest() {
        GraphicalCard card = new GraphicalCard(CardType.SKIP_TURN);
        assertEquals(CardType.SKIP_TURN, card.getCardType());
        assertFalse(card.isDouble());
    }
}
