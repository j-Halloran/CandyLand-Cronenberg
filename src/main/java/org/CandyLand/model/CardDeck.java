package org.CandyLand.model;

import org.CandyLand.CardType;
import java.util.ArrayList;



public class CardDeck {
    private static final int NUM_SINGLE_PER_COLOR = 10;
    private static final int NUM_DOUBLE_PER_COLOR = 2;
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

    private static ArrayList<CardType> currentDeck;
    static {shuffleDeck();}

    public CardDeck(){
        shuffleDeck();
    }

    /**
     * Explanation for what happens here: 3 submethods are called to generate all the possible cards
     * and then fast mapped into a single array list which is then shuffled using the java
     * collections framework shuffle for lists.     *
     */
    public static void shuffleDeck(){
        currentDeck = new ArrayList<>();
        System.out.println("Shuffling");
        currentDeck.addAll(generateSingleColorCards());
        currentDeck.addAll(generateDoubleColorCards());
      //  currentDeck.addAll(generateSpecialCards());
        java.util.Collections.shuffle(currentDeck);
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
     * Currently there are no special cards sos this is null, just future proofing
     *
     * @return An Array List containing all necessary special cards
     */
    private static ArrayList<CardType> generateSpecialCards(){
        return null;
    }

    /**
     * Returns the last card in the array list as the next card to draw
     * despite seeming silly, this is by far the most efficient way to handle this
     * operation as removing from the front would require N shift operations and
     * as the list is randomized, there is no specific reason to use any given index
     *
     * @return Returns the last card in the deck object as the next card drawn
     */
    public static CardType drawCard(){
        if(currentDeck.isEmpty()){
            shuffleDeck();
        }
        return currentDeck.remove(currentDeck.size()-1);
    }

    public static boolean isDeckEmpty(){
        return currentDeck.isEmpty();
    }

    /**
     * NOTE: only for testing, size queries should user isDeckEmpty for normal functionality
     *
     * @return The current size of the deck as an int
     */
    public static int getDeckSize(){
        return currentDeck.size();
    }
}
