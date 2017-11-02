package org.CandyLand.view;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.CandyLand.CardType;

import java.awt.*;
import java.io.IOException;

public class GraphicalBoardTest {

    static final int MIDDLE_SPACE = 36;
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

    @Test
    public void goToMiddleNextSpaceTest() {
        GraphicalCard middleCard = mock(GraphicalCard.class);
        when(middleCard.getCardType()).thenReturn(CardType.GO_TO_MIDDLE);
        assertEquals(MIDDLE_SPACE, board.getNextSpace(middleCard, 0));
        assertEquals(MIDDLE_SPACE, board.getNextSpace(middleCard, 11));
        assertEquals(MIDDLE_SPACE, board.getNextSpace(middleCard, 73));
        assertEquals(MIDDLE_SPACE, board.getNextSpace(middleCard, 0xffff));
    }

    @Test
    public void winStateTest(){
        GraphicalCard blueCard = new GraphicalCard(CardType.SINGLE_BLUE);
        board.addInitialTokens(1);
        for(int i = 0; i < 18; i++) {
            board.moveAvatar(0, blueCard);
        }
        assertEquals(board.atGrandmas(0), true);
    }

    @Test
    public void avatarMovementSingleTest() {
        GraphicalCard redCard = mock(GraphicalCard.class);
        when(redCard.getCardType()).thenReturn(CardType.SINGLE_RED);
        when(redCard.getBackground()).thenReturn(Color.RED);
        board.addInitialTokens(1);
        board.moveAvatar(0,redCard);
        assertEquals(board.getPlayerLocation(0), 1);
    }

    @Test
    public void avatarMovementDoubleTest() {
        GraphicalCard redDoubleCard = mock(GraphicalCard.class);
        when(redDoubleCard.getCardType()).thenReturn(CardType.DOUBLE_RED);
        when(redDoubleCard.getBackground()).thenReturn(Color.RED);
        board.addInitialTokens(1);
        board.moveAvatar(0,redDoubleCard);
        assertEquals(board.getPlayerLocation(0), 6);
    }




}