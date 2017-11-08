package org.CandyLand.view;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by 15mik_000 on 11/7/2017.
 */
public class GamePathSpaceTest {


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

}