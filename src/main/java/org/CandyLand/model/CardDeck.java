package org.CandyLand.model;

import org.CandyLand.CardType;
import java.util.ArrayList;

public class CardDeck implements java.io.Serializable {
    private static final int NUM_SINGLE_PER_COLOR = 10;
    private static final int NUM_DOUBLE_PER_COLOR = 2;
    private static final int NUM_SKIP_TURN_CARDS = 5;
    private static final CardType[] SINGLE_CARDS = {CardType.SINGLE_RED,
                                                    CardType.SINGLE_YELLOW,
                                                    CardType.SINGLE_BLUE,
                                                    CardType.SINGLE_GREEN,
                                                    CardType.SINGLE_ORANGE};
    private static final CardType[] DOUBLE_CARDS = {CardType.DOUBLE_RED,
                                                    CardType.DOUBLE_YELLOW,
                                                    CardType.DOUBLE_BLUE,
                                                    CardType.DOUBLE_GREEN,
                                                    CardType.DOUBLE_ORANGE};
    private ArrayList<CardType> currentDeck;
    private CardType lastDrawn;

    public CardDeck(){
        shuffleDeck();
    }

    /**
     * Explanation for what happens here: 3 submethods are called to generate all the possible cards
     * and then fast mapped into a single array list which is then shuffled using the java
     * collections framework shuffle for lists.     *
     */
    public void shuffleDeck(){
        currentDeck = new ArrayList<>();
        currentDeck.addAll(generateSingleColorCards());
        currentDeck.addAll(generateDoubleColorCards());
        currentDeck.addAll(generateSpecialCards());
        java.util.Collections.shuffle(currentDeck);
        lastDrawn = null;
    }

    private static ArrayList<CardType> generateSingleColorCards(){
        ArrayList<CardType> generatedSingleCards = new ArrayList<>();
        for(int i=0;i<NUM_SINGLE_PER_COLOR;i++){
            for(CardType type: SINGLE_CARDS){
                generatedSingleCards.add(type);
            }
        }
        return generatedSingleCards;
    }

    private static ArrayList<CardType> generateDoubleColorCards(){
        ArrayList<CardType> generatedDoubleCards = new ArrayList<>();
        for(int i=0;i<NUM_DOUBLE_PER_COLOR;i++){
            for(CardType type: DOUBLE_CARDS){
                generatedDoubleCards.add(type);
            }
        }
        return generatedDoubleCards;
    }

    /**
     *
     * @return An Array List containing all necessary special cards
     */
    private static ArrayList<CardType> generateSpecialCards(){
        ArrayList<CardType> generatedSpecialCards = new ArrayList<>();
        for (int i = 0; i < NUM_SKIP_TURN_CARDS; i++){
            generatedSpecialCards.add(CardType.SKIP_TURN);
        }
        //Add individual Special cards
        generatedSpecialCards.add(CardType.ICE_CREAM);
        generatedSpecialCards.add(CardType.LICORICE);
        generatedSpecialCards.add(CardType.CAKE);
        generatedSpecialCards.add(CardType.CANDY_CORN);
        generatedSpecialCards.add(CardType.CHOCOLATE);
        return generatedSpecialCards;
    }

    /**
     * Returns the last card in the array list as the next card to draw
     * despite seeming silly, this is by far the most efficient way to handle this
     * operation as removing from the front would require N shift operations and
     * as the list is randomized, there is no specific reason to use any given index
     *
     * @return Returns the last card in the deck object as the next card drawn
     */
    public CardType drawCard(){
        if(currentDeck.isEmpty()){
            shuffleDeck();
        }
        lastDrawn = currentDeck.remove(currentDeck.size()-1);
        return lastDrawn;
    }

    /**
     *  Iterates over all of the cards in the deck and returns the first occurrence of the card
     *  type that leaves dad at the lowest space position. Would just iterate over the types of
     *  cards, but they may not be present in the deck
     *
     * @param board - the graphical board that cards will be tested against to find the worst possible outcome for dad
     * @return A card of the CardType that causes Dad to have the lowest possible next position
     */
    public CardType drawDadCard(GameBoard board, int playerNum){
        if(currentDeck.isEmpty()){
            shuffleDeck();
        }
        int lowPosCardLoc = 0;
        int lowPos = board.getPotentialLoc(currentDeck.get(0), playerNum);
        for(int i=0;i<currentDeck.size();i++){
            int tempPos = board.getPotentialLoc(currentDeck.get(i), playerNum);
            if(lowPos > tempPos){
                lowPosCardLoc = i;
                lowPos = tempPos;
            }
        }
        return currentDeck.remove(lowPosCardLoc);
    }

    public boolean isDeckEmpty(){
        if(currentDeck == null){
            return true;
        }
        return currentDeck.isEmpty();
    }

    /**
     * NOTE: only for testing, size queries should user isDeckEmpty for normal functionality
     *
     * @return The current size of the deck as an int
     */
    public int getDeckSize(){
        if(currentDeck==null) {
            return 0;
        }
        return  currentDeck.size();
    }

    public CardType getLastDrawn() {
      return lastDrawn;
    }
}
