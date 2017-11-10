package org.CandyLand.view;

import org.junit.Test;
import java.awt.*;
import static org.junit.Assert.*;
import org.CandyLand.model.GameBoard;

public class GamePathSpaceTest {


    /*
* US: Board Exists Unit Tests
*
* numberOfSpaces71() - confirm 71 spaces generated for board
* colorOfSpaces5() - confirm abilty to set spaces to 5 specified colors
*/

    // this test aint even relevant to game space
//    @Test
//    public void numberOfSpaces71(){
//        GraphicalBoard gb = new GraphicalBoard(new Board(4));
//        assertEquals(gb.getPathSize(), 71);
//    }

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

}