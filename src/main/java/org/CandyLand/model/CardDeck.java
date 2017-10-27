package org.CandyLand.model;

import org.CandyLand.view.CardType;
import org.CandyLand.view.GraphicalCard;
import java.util.ArrayList;


public class CardDeck {
    private static ArrayList<GraphicalCard> currentDeck;

    public CardDeck(){
        shuffleDeck();
    }

    public void shuffleDeck(){
        currentDeck = new ArrayList<>();
        currentDeck.addAll(generateSingleColorCards());
        currentDeck.addAll(generateDoubleColorCards());
        currentDeck.addAll(generateSpecialCards());
    }

    private ArrayList<GraphicalCard> generateSingleColorCards(){
        return null;
    }

    private ArrayList<GraphicalCard> generateDoubleColorCards(){
        return null;
    }

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
    public GraphicalCard getCard(){
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
