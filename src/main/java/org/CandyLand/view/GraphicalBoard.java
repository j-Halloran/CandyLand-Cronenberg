package org.CandyLand.view;

import java.awt.*;
import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.ArrayList;

public class GraphicalBoard extends JPanel {

    private static final int ROWS = 11;
    private static final int COLS = 11;
    private static final Color BACKGROUND_COLOR = Color.GRAY;
    private JComponent[][] spaces = new JComponent[ROWS][COLS];
    private GamePathSpace[] path;
    private final Color[] COLORS = {Color.RED,
                                    Color.YELLOW,
                                    Color.BLUE,
                                    Color.GREEN,
                                    Color.ORANGE};

    private void initializeBoard() {
        int row = ROWS - 1;
        int col = 1;
        boolean movingRight = true;
        boolean spacerRow = false;
        ArrayList<GamePathSpace> path =
                new ArrayList<GamePathSpace>(ROWS * COLS);
        path.add(null); //leave a space for start
        int currentColor = 0;

        while (!(row == 0 && col == 0)) { //row >= 0, because grandmas is at 0,0
            GamePathSpace space = new GamePathSpace(COLORS[currentColor]);
            currentColor = (currentColor + 1) % COLORS.length;

            spaces[row][col] = space;
            path.add(space);

           if(col == COLS - 1 && movingRight) {
                row--;
                movingRight = false;
                spacerRow = true;
            }
            else if (col == 0 && !movingRight) {
                row--;
                movingRight = true;
                spacerRow = true;
            }
            else if (spacerRow) {
                row--;
                spacerRow = false;
            }
            else if (movingRight) {
                col++;
            }
            else {
                col--;
            }
        }

        for (int i = 0; i < spaces.length; i++) {
            for (int j = 0; j < spaces[i].length; j++) {
                if (spaces[i][j] == null) {
                    spaces[i][j] = new BackgroundSpace(BACKGROUND_COLOR);
                }
            }
        }

        path.add(null); //leave a space for finish

        this.path = path.toArray(new GamePathSpace[0]);
    }

    public GraphicalBoard() {
        initializeBoard();
        this.setLayout(new GridLayout(ROWS, COLS));
        this.setBackground(BACKGROUND_COLOR);
        for (JComponent[] spaceRow : spaces) {
            for (JComponent space : spaceRow) {
                if (space != null) {
                    this.add(space);
                }
            }
        }
    }
}