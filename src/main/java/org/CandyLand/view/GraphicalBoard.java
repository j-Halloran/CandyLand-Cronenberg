package org.CandyLand.view;

import org.CandyLand.CardType;

import java.awt.*;
import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;

public class GraphicalBoard extends JPanel {

    private static final int ROWS = 11;
    private static final int COLS = 11;
    private static final Color BACKGROUND_COLOR = new Color(0,0,0,0);
    private JComponent[][] spaces = new JComponent[ROWS][COLS];
    private GamePathSpace[] path;
    private static int[] tokenLocations; //tracker variable where player number is used as an index to retrieve current token path space
    private static Token[] tokens; //Tracker that contains all of the tokens so they can be removed from a space and moved around
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
        ArrayList<GamePathSpace> path =  new ArrayList<>(ROWS * COLS);

        //leave a space for start
        GamePathSpace startSpace = new GamePathSpace(Color.WHITE);
        path.add(startSpace);
        spaces[row][0] = startSpace;

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



        //leave a space for finish
        JLabel grandmaLabel = new JLabel("Grandma's",0);
        GamePathSpace grandma = new GamePathSpace(Color.WHITE);
        grandma.setLayout(new BorderLayout());
        grandma.add(grandmaLabel);
        path.add(grandma);
        spaces[0][0] = grandma;

        this.path = new GamePathSpace[path.size()];
        path.toArray(this.path);
    }

    public GraphicalBoard() {
        initializeBoard();
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

    public int getPathSize(){
        return path.length;
    }
  
    /**
     * Loops for each player in the game adding their token to the starting space
     *
     * @param numPlayers
     */
    public void addInitialTokens(int numPlayers){
        if(numPlayers > GamePathSpace.getMaxPlayerCount()){
            numPlayers = GamePathSpace.getMaxPlayerCount();
        }

        tokenLocations = new int[numPlayers];
        tokens = new Token[numPlayers];
        for(int i=0;i<numPlayers;i++){
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            URL url = classloader.getResource("player"+(i+1)+"_small.png");
            tokens[i] = new Token(url.getPath());
            tokenLocations[i] = 0; //initialize location tracker to position 0 so in the future we can fast update locations without needing to scan all board spaces
            try{
                path[0].addToken(tokens[i]);
            }catch(NoSpaceForTokenException e){
                System.err.println("Error more tokens than space created. Should not be possible");
                System.exit(10);
            }
            //path[1].removeToken(tokens[i]);
        }
    }

    public void moveAvatar(int playerNumber, GraphicalCard card){
        int getNextSpace = getNextSpace(card,tokenLocations[playerNumber]);
        if(card.getCardType().equals(CardType.DOUBLE_BLUE) || card.getCardType().equals(CardType.DOUBLE_GREEN) || card.getCardType().equals(CardType.DOUBLE_RED) ||
                card.getCardType().equals(CardType.DOUBLE_YELLOW) || card.getCardType().equals(CardType.DOUBLE_ORANGE)){
            getNextSpace = getNextSpace(card,getNextSpace);
        }

        path[tokenLocations[playerNumber]].removeToken(tokens[playerNumber]);
        try{
            path[getNextSpace].addToken(tokens[playerNumber]);
        }
        catch (NoSpaceForTokenException e){
            System.err.println("Error more tokens than players. Exiting");
            System.exit(1);
        }
        tokenLocations[playerNumber] = getNextSpace;

        if(atGrandmas(playerNumber) == true){
            // doVictoryStuff, end game
        }
    }

    protected int getNextSpace(GraphicalCard card, int curLoc){
        //Another sanity check
        if(card.getCardType() == CardType.EMPTY_DISCARD || card.getCardType() == CardType.UPSIDEDOWN){
            return 0;
        }
        else if (card.getCardType() == CardType.SKIP_TURN) {
            // we aint goin nowhere
            return curLoc;
        }
        for(int i=curLoc+1;i<path.length-1;i++){
            if(path[i].getSpaceColor().equals(card.getBackground())){
                return i;
            }
        }
        return path.length-2;
    }

    public void resetTokens(){
        for(int i = 0; i < tokens.length; i++) {
            path[tokenLocations[i]].removeToken(tokens[i]);
            tokenLocations[i] = 0; //initialize location tracker to position 0 so in the future we can fast update locations without needing to scan all board spaces
            try {
                path[0].addToken(tokens[i]);
            } catch (NoSpaceForTokenException e) {
                System.err.println("Error more tokens than space created. Should not be possible");
                System.exit(10);
            }
        }
    }

    // check if token has reached grandmas house
    public boolean atGrandmas(int playerNumber){
        //end of board currently set to path.length-2, until bug fix by Jake
        if(tokenLocations[playerNumber] == path.length-2){
            return true;
        }
        return false;
    }

    public Color getBackgroundColor(){
        return BACKGROUND_COLOR;
    }

    public GamePathSpace[] getPath(){
        return path;
    }

}
