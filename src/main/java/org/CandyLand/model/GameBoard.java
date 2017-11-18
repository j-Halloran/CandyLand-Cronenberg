package org.CandyLand.model;

import org.CandyLand.SpaceType;
import org.CandyLand.CardType;
import org.CandyLand.IllegalNumberOfPlayersException;
import org.CandyLand.IllegalPlayerNumberException;

public class GameBoard implements java.io.Serializable {

    public static final int NUMBER_OF_SPACES = 71;
    private SpaceType[] spaceTypes = new SpaceType[NUMBER_OF_SPACES];
    private static final SpaceType[] COLORED_SPACES = {SpaceType.RED,
                                                       SpaceType.YELLOW,
                                                       SpaceType.BLUE,
                                                       SpaceType.GREEN,
                                                       SpaceType.ORANGE};
    public int numPlayers;
    private int[] playerPostions;

    public GameBoard(int numPlayers) {
        if (numPlayers < 2 || numPlayers > 4) {
            throw new IllegalNumberOfPlayersException();
        }
        initializeBoard();
        this.numPlayers = numPlayers;
        playerPostions = new int[numPlayers];
    }

    private void initializeBoard() {
        spaceTypes[0] = SpaceType.START;
        spaceTypes[NUMBER_OF_SPACES - 1] = SpaceType.GRANDMAS;

        int currentSpace = 1;
        while (currentSpace < NUMBER_OF_SPACES - 1) {
            spaceTypes[currentSpace] = COLORED_SPACES[(currentSpace - 1) % COLORED_SPACES.length];
            currentSpace++;
        }
    }

    public void movePlayer(int playerNum, CardType card) {
        if (playerNum < 0 || playerNum >= numPlayers) {
            throw new IllegalPlayerNumberException();
        }
        switch (card) {
            case SKIP_TURN:
                return;
            case DOUBLE_RED:
            case DOUBLE_YELLOW:
            case DOUBLE_BLUE:
            case DOUBLE_GREEN:
            case DOUBLE_ORANGE:
                movePlayerToNextColoredSpace(playerNum, card);
            case SINGLE_RED:
            case SINGLE_YELLOW:
            case SINGLE_BLUE:
            case SINGLE_GREEN:
            case SINGLE_ORANGE:
                movePlayerToNextColoredSpace(playerNum, card);
                break;
        }
    }

    private void movePlayerToNextColoredSpace(int playerNum, CardType card) {
        if (playerNum < 0 || playerNum >= numPlayers) {
            throw new IllegalPlayerNumberException();
        }
        if (playerPostions[playerNum] >= NUMBER_OF_SPACES - 1) {
            return;
        }
        do {
            playerPostions[playerNum]++;
        } while (playerPostions[playerNum] < NUMBER_OF_SPACES - 1
                 && !cardAndSpaceCompatible(card, spaceTypes[playerPostions[playerNum]]));
    }

    public static boolean cardAndSpaceCompatible(CardType card, SpaceType space) {
        return (
            (space == SpaceType.RED && (card == CardType.SINGLE_RED || card == CardType.DOUBLE_RED))
            || (space == SpaceType.YELLOW && (card == CardType.SINGLE_YELLOW || card == CardType.DOUBLE_YELLOW))
            || (space == SpaceType.BLUE && (card == CardType.SINGLE_BLUE || card == CardType.DOUBLE_BLUE))
            || (space == SpaceType.GREEN && (card == CardType.SINGLE_GREEN || card == CardType.DOUBLE_GREEN))
            || (space == SpaceType.ORANGE && (card == CardType.SINGLE_ORANGE || card == CardType.DOUBLE_ORANGE))
        );
    }

    public int[] getPlayerPositions() {
        return playerPostions;
    }

    public SpaceType[] getSpaceTypes() {
        return spaceTypes;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public boolean isGameOver() {
        for (int position : playerPostions) {
            if (position == NUMBER_OF_SPACES - 1) {
                return true;
            }
        }

        return false;
    }

    public void resetPlayersToStart() {
        for (int i = 0; i < playerPostions.length; i++) {
            playerPostions[i] = 0;
        }
    }
}
