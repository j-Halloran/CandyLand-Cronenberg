package org.CandyLand.model;

import org.CandyLand.view.CardType;
import org.CandyLand.view.GraphicalCard;
import java.util.ArrayList;



public class CardDeck {
    private static final int NUM_SINGLE_PER_COLOR = 10;
    private static final int NUM_DOUBLE_PER_COLOR = 2;
    private static final CardType[] CARD_COLORS = {CardType.RED,CardType.YELLOW,CardType.BLUE,CardType.GREEN,CardType.ORANGE};

    private static ArrayList<GraphicalCard> currentDeck;

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
        System.out.println("Shuffling");
        currentDeck.addAll(generateSingleColorCards());
        currentDeck.addAll(generateDoubleColorCards());
      //  currentDeck.addAll(generateSpecialCards());
        java.util.Collections.shuffle(currentDeck);
    }

    private ArrayList<GraphicalCard> generateSingleColorCards(){
        ArrayList<GraphicalCard> generatedSingleCards = new ArrayList<>();
        for(int i=0;i<NUM_SINGLE_PER_COLOR;i++){
            for(CardType type: CARD_COLORS){
                generatedSingleCards.add(new GraphicalCard(type,false));
            }
        }
        return generatedSingleCards;
    }

    private ArrayList<GraphicalCard> generateDoubleColorCards(){
        ArrayList<GraphicalCard> generatedDoubleCards = new ArrayList<>();
        for(int i=0;i<NUM_DOUBLE_PER_COLOR;i++){
            for(CardType type: CARD_COLORS){
                generatedDoubleCards.add(new GraphicalCard(type,true));
            }
        }
        return generatedDoubleCards;
    }

    /**
     * Currently there are no special cards sos this is null, just future proofing
     *
     * @return An Array List containing all necessary special cards
     */
    private ArrayList<GraphicalCard> generateSpecialCards(){
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
    public GraphicalCard drawCard(){
        if(currentDeck.isEmpty()){
            shuffleDeck();
        }
        return currentDeck.remove(currentDeck.size()-1);
    }

    public boolean isDeckEmpty(){
        return currentDeck.isEmpty();
    }

    /**
     * NOTE: only for testing, size queries should user isDeckEmpty for normal functionality
     *
     * @return The current size of the deck as an int
     */
    public int getDeckSize(){
        return currentDeck.size();
    }
}
