package org.CandyLand.view;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.CandyLand.CardType;
import org.CandyLand.model.GameBoard;

import java.awt.*;
import java.io.IOException;

public class GraphicalBoardTest {

    GraphicalBoard board;
    private static final int FIRST_SPECIAL_SPACE = 12;

    @Before
    public void preactions() {
        String[] tempNames = {"Test", "Dad"};
        this.board = new GraphicalBoard(new GameBoard(2, tempNames));
    }

// TODO: THE FOLLOWING TESTS NEED TO BE REFACTORED INTO THE GAMEBOARD CLASS OF THE MODEL
//    @Test
//    public void skipTurnNextSpaceTest() {
//        GraphicalCard skipCard = mock(GraphicalCard.class);
//        when(skipCard.getCardType()).thenReturn(CardType.SKIP_TURN);
//        assertEquals(0, board.getNextSpace(skipCard, 0));
//        assertEquals(15, board.getNextSpace(skipCard, 15));
//        assertEquals(90, board.getNextSpace(skipCard, 90));
//        assertEquals(0xffff, board.getNextSpace(skipCard, 0xffff));
//    }
//
//    @Test
//    public void goToMiddleNextSpaceTest() {
//        GraphicalCard middleCard = mock(GraphicalCard.class);
//        when(middleCard.getCardType()).thenReturn(CardType.GO_TO_MIDDLE);
//        assertEquals(MIDDLE_SPACE, board.getNextSpace(middleCard, 0));
//        assertEquals(MIDDLE_SPACE, board.getNextSpace(middleCard, 11));
//        assertEquals(MIDDLE_SPACE, board.getNextSpace(middleCard, 73));
//        assertEquals(MIDDLE_SPACE, board.getNextSpace(middleCard, 0xffff));
//    }
//
//    @Test
//    public void winStateTest(){
//        GraphicalCard blueCard = new GraphicalCard(CardType.SINGLE_BLUE);
//        board.addInitialTokens(1);
//        for(int i = 0; i < 18; i++) {
//            board.moveAvatar(0, blueCard);
//        }
//        assertEquals(board.atGrandmas(0), true);
//    }
//
//    @Test
//    public void avatarMovementSingleTest() {
//        GraphicalCard redCard = mock(GraphicalCard.class);
//        when(redCard.getCardType()).thenReturn(CardType.SINGLE_RED);
//        when(redCard.getBackground()).thenReturn(Color.RED);
//        board.addInitialTokens(1);
//        board.moveAvatar(0,redCard);
//        assertEquals(board.getPlayerLocation(0), 1);
//    }
//
//    @Test
//    public void avatarMovementDoubleTest() {
//        GraphicalCard redDoubleCard = mock(GraphicalCard.class);
//        when(redDoubleCard.getCardType()).thenReturn(CardType.DOUBLE_RED);
//        when(redDoubleCard.getBackground()).thenReturn(Color.RED);
//        board.addInitialTokens(1);
//        board.moveAvatar(0,redDoubleCard);
//        assertEquals(board.getPlayerLocation(0), 6);
//    }

    @Test
    public void grandmaBackgroundTest(){
        GamePathSpace[] path = board.getPath();
        assertEquals(Color.WHITE,path[path.length-1].getSpaceColor());
    }

    @Test
    public void grandmaImageTest(){
        GamePathSpace[] path = board.getPath();
        TokenSpace[] spaces = path[path.length-1].getTokenSpaces();
        for(TokenSpace space:spaces){
            assertNotNull(space.getIcon());
        }
    }

    @Test
    public void specialImageTest(){
        GamePathSpace[] path = board.getPath();
        TokenSpace[] spaces = path[FIRST_SPECIAL_SPACE].getTokenSpaces();
        for(TokenSpace space:spaces){
            assertNotNull(space.getIcon());
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
        assertEquals(board.getBackgroundColor(), new Color(0,0,0,0));
    }

    @Test
    public void startColor(){
        assertEquals(board.getPath()[0].getSpaceColor(), Color.WHITE);

    }


}