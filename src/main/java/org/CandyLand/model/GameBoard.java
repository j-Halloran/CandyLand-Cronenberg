package org.CandyLand.model;

import org.CandyLand.*;

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
    private int[] numBoomerangs;

    private static boolean isStrategic;
    public static boolean isStrategic() {
        return isStrategic;
    }
    public void setIsStrategic(boolean isStrategic){
        this.isStrategic = isStrategic;
    }

    public GameBoard(int numPlayers, String[] playerNames, boolean isStrategic) {
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
        this.isStrategic = isStrategic;
        if(isStrategic){
            numBoomerangs = new int[numPlayers];
            for(int i=0;i<numPlayers;i++){
                numBoomerangs[i] = CandyLand.STARTING_BOOMERANGS;
            };
        }
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

    public void movePlayer(int playerNum, CardType card, int boomerangTarget) {
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
                movePlayerToNextColoredSpace(playerNum, card, boomerangTarget);
            case SINGLE_RED:
            case SINGLE_YELLOW:
            case SINGLE_BLUE:
            case SINGLE_GREEN:
            case SINGLE_ORANGE:
                movePlayerToNextColoredSpace(playerNum, card, boomerangTarget);
                break;
            case CAKE:
            case LICORICE:
            case CANDY_CORN:
            case CHOCOLATE:
            case ICE_CREAM:
                movePlayerToSpecialSpace(playerNum, card, boomerangTarget);
        }
    }

    private void movePlayerToNextColoredSpace(int playerNum, CardType card, int boomerangTarget) {
        if (playerNum < 0 || playerNum >= numPlayers) {
            throw new IllegalPlayerNumberException();
        }
        if (playerPostions[playerNum] >= NUMBER_OF_SPACES - 1) {
            return;
        }
        if(boomerangTarget==-1) {
            do {
                playerPostions[playerNum]++;
            } while (playerPostions[playerNum] < NUMBER_OF_SPACES - 1
                    && !cardAndSpaceCompatible(card, spaceTypes[playerPostions[playerNum]]));
        }else{
            if(playerPostions[boomerangTarget]==0){
                return;
            }
            playerNum = boomerangTarget;
            do {
                playerPostions[playerNum]--;
            } while (playerPostions[playerNum] > 0
                    && !cardAndSpaceCompatible(card, spaceTypes[playerPostions[playerNum]]));
        }
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

    private void movePlayerToSpecialSpace(int playerNum, CardType card, int boomerangTarget){
        if (playerNum < 0 || playerNum >= numPlayers) {
            throw new IllegalPlayerNumberException();
        }
        if (playerPostions[playerNum] >= NUMBER_OF_SPACES - 1) {
            return;
        }
        if(boomerangTarget!=-1){
            playerNum = boomerangTarget;
        }
        if(specialLocations==null){
            fillSpecialLocations();
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
            numBoomerangs[i] = CandyLand.STARTING_BOOMERANGS;
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

    public int getPotentialLoc(CardType cardToTest, int playerNum){
        int resetPos = playerPostions[playerNum];
        movePlayer(playerNum, cardToTest, -1);
        int potentialLoc = playerPostions[playerNum];
        //need to undo the move
        playerPostions[playerNum] = resetPos;
        return potentialLoc;
    }

    public String[] getOtherPlayerNames(int playerNum){
        String[] results = new String[numPlayers-1];
        int j=0;
        for(int i = 0; i< numPlayers;i++){
            if(i!=playerNum){
                results[j++] = playerNames[i];
            }
        }
        return results;
    }

    public int getPlayerNumberByName(String name){
        for(int i=0;i<numPlayers;i++){
            if(playerNames[i].equals(name)){
                return i;
            }
        }
        return -1;
    }

    public boolean hasBoomerangs(int playerNum){
        return isStrategic && numBoomerangs[playerNum] > 0;
    }

    public void useBoomerang(int playerNum){
        numBoomerangs[playerNum]--;
    }
}
