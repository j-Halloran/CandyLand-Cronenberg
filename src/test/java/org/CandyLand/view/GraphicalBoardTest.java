package org.CandyLand.view;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.CandyLand.CardType;

public class GraphicalBoardTest {
    GraphicalBoard board;

    @Before
    public void preactions() {
        this.board = new GraphicalBoard();
    }

    @Test
    public void skipTurnNextSpaceTest() {
        GraphicalCard skipCard = mock(GraphicalCard.class);
        when(skipCard.getCardType()).thenReturn(CardType.SKIP_TURN);
        assertEquals(0, board.getNextSpace(skipCard, 0));
        assertEquals(15, board.getNextSpace(skipCard, 15));
        assertEquals(90, board.getNextSpace(skipCard, 90));
        assertEquals(0xffff, board.getNextSpace(skipCard, 0xffff));
    }


}