package org.CandyLand.view;

import org.CandyLand.CardType;
import org.CandyLand.SpaceType;
import org.CandyLand.model.GameBoard;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GraphicalBoard extends JPanel {

    private static final int ROWS = 11;
    private static final int COLS = 11;
    private static final Color BACKGROUND_COLOR = new Color(0,0,0,0);
    private JComponent[][] spaces = new JComponent[ROWS][COLS];
    private GamePathSpace[] path = new GamePathSpace[GameBoard.NUMBER_OF_SPACES];
    private static Token[] tokens; //Tracker that contains all of the tokens so they can be removed from a space and moved around
    private static int[] tokenLocations;

    private void initializePath() {
        int row = ROWS - 1;
        int col = 0;
        boolean movingRight = true;
        boolean spacerRow = false;
        int currentSpace = 0;

        while (row >= 0 && col >= 0) {
            spaces[row][col] = path[currentSpace++];

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
    }

    private void setSpaceTypes(SpaceType[] types) {
        for (int i = 0; i < types.length; i++) {
            path[i] = generateSpaceFromType(types[i]);
        }
    }

    private GamePathSpace generateSpaceFromType(SpaceType type) {
        GamePathSpace space = null;
        switch (type) {
            case RED:
                space = new GamePathSpace(Color.RED);
                break;
            case YELLOW:
                space = new GamePathSpace(Color.YELLOW);
                break;
            case BLUE:
                space = new GamePathSpace(Color.BLUE);
                break;
            case GREEN:
                space = new GamePathSpace(Color.GREEN);
                break;
            case ORANGE:
                space = new GamePathSpace(Color.ORANGE);
                break;
            case START:
                space = new GamePathSpace(Color.WHITE);
                break;
            case GRANDMAS:
                space = new GamePathSpace(Color.WHITE, "rainbowSpaceBG");
                break;
        }
        return space;
    }

    public GraphicalBoard(GameBoard board) {
        setSpaceTypes(board.getSpaceTypes());
        initializePath();
        intitializeTokens(board.getNumPlayers());
        GridLayout layout = new GridLayout(ROWS,COLS);
        layout.setVgap(0);
        this.setLayout(layout);
        this.setBackground(BACKGROUND_COLOR);
        for (JComponent[] spaceRow : spaces) {
            for (JComponent space : spaceRow) {
                if (space != null) {
                    this.add(space);
                }
            }
        }
    }

    public void intitializeTokens(int numPlayers) {
        tokens = new Token[numPlayers];
        tokenLocations = new int[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            URL url = classloader.getResource("player"+(i+1)+"_small.png");
            tokens[i] = new Token(url.getPath());
        }
    }

    public void setTokenLocations(int[] tokenLocations) {
        for (int i = 0; i < tokenLocations.length; i++) {
            updateTokenLocation(i, tokenLocations[i]);
            this.tokenLocations[i] = tokenLocations[i];
        }
    }

    private void updateTokenLocation(int playerNum, int spaceToMoveTo) {
        path[tokenLocations[playerNum]].removeToken(tokens[playerNum]);
        path[spaceToMoveTo].addToken(tokens[playerNum]);
    }

    public int getPathSize(){
        return path.length;
    }

    public Color getBackgroundColor(){
        return BACKGROUND_COLOR;
    }

    public GamePathSpace[] getPath(){
        return path;
    }

    // temporary for Testing purposes only
    public int getPlayerLocation(int playerNumber){ return tokenLocations[playerNumber]; }

}
