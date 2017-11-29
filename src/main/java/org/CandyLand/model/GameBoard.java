package org.CandyLand.model;

import org.CandyLand.SpaceType;
import org.CandyLand.CardType;
import org.CandyLand.IllegalNumberOfPlayersException;
import org.CandyLand.IllegalPlayerNumberException;
import org.CandyLand.NameCountPlayerCountMismatchException;

import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.HashMap;

public class GameBoard implements java.io.Serializable {

    public static final int NUMBER_OF_SPACES = 71;
    private SpaceType[] spaceTypes = new SpaceType[NUMBER_OF_SPACES];
    private static final SpaceType[] COLORED_SPACES = {SpaceType.RED,
                                                       SpaceType.YELLOW,
                                                       SpaceType.BLUE,
                                                       SpaceType.GREEN,
                                                       SpaceType.ORANGE};
    private static HashMap<CardType, Integer> specialLocations;
    private static HashMap<Integer, SpaceType> specialSpaceTypes;

    public int numPlayers;
    private int[] playerPostions;
    private String[] playerNames;

    public GameBoard(int numPlayers, String[] playerNames) {
        if (numPlayers < 2 || numPlayers > 4) {
            throw new IllegalNumberOfPlayersException();
        }
        if (numPlayers != playerNames.length) {
            throw new NameCountPlayerCountMismatchException();
        }
        fillSpecialLocations();
        initializeBoard();
        this.numPlayers = numPlayers;
        this.playerNames = playerNames;
        playerPostions = new int[numPlayers];
    }

    private void initializeBoard() {
        spaceTypes[0] = SpaceType.START;
        spaceTypes[NUMBER_OF_SPACES - 1] = SpaceType.GRANDMAS;

        int currentSpace = 1;
        int specialSpacesAdded = 0;
        ArrayList<Integer> specialSpaces = new ArrayList<>(specialLocations.values());
        while (currentSpace < NUMBER_OF_SPACES - 1) {
            //Additional terrible code to add special spaces
            if(specialSpaces.contains(currentSpace)){
                spaceTypes[currentSpace] = specialSpaceTypes.get(currentSpace);
                specialSpacesAdded++;
            }
            else{
                spaceTypes[currentSpace] = COLORED_SPACES[(currentSpace - (1+specialSpacesAdded)) % COLORED_SPACES.length];
            }
            currentSpace++;
        }
    }

    private void fillSpecialLocations() {
        specialLocations = new HashMap<>();
        specialSpaceTypes = new HashMap<>();
        specialLocations.put(CardType.ICE_CREAM, 12);
        specialSpaceTypes.put(12,SpaceType.ICE_CREAM);
        specialLocations.put(CardType.LICORICE, 24);
        specialSpaceTypes.put(24,SpaceType.LICORICE);
        specialLocations.put(CardType.CANDY_CORN, 36);
        specialSpaceTypes.put(36,SpaceType.CANDY_CORN);
        specialLocations.put(CardType.CAKE, 48);
        specialSpaceTypes.put(48,SpaceType.CAKE);
        specialLocations.put(CardType.CHOCOLATE, 60);
        specialSpaceTypes.put(60,SpaceType.CHOCOLATE);
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
            case CAKE:
            case LICORICE:
            case CANDY_CORN:
            case CHOCOLATE:
            case ICE_CREAM:
                movePlayerToSpecialSpace(playerNum, card);
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

    private void movePlayerToSpecialSpace(int playerNum, CardType card){
        if (playerNum < 0 || playerNum >= numPlayers) {
            throw new IllegalPlayerNumberException();
        }
        if (playerPostions[playerNum] >= NUMBER_OF_SPACES - 1) {
            return;
        }
        playerPostions[playerNum] = specialLocations.get(card);
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

    public String getPlayerName(int playerNum) {
        if (playerNum < 0 || playerNum > playerNames.length) {
            return null;
        }
        return playerNames[playerNum];
    }

    public String[] getPlayerNames() {
        return playerNames;
    }
}
