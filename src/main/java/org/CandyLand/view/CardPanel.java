package org.CandyLand.view;

import org.CandyLand.model.CardDeck;

import java.awt.*;
import javax.swing.*;

public class CardPanel extends JPanel {

    private static final Color BACKGROUND_COLOR = Color.GRAY;
    private GraphicalCard drawPile =
            new GraphicalCard(CardType.UPSIDEDOWN);
    private GraphicalCard discardPile = new GraphicalCard(CardType.EMPTY_DISCARD);
    private CardDeck deck;

    public CardPanel() {
        deck = new CardDeck();
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(BACKGROUND_COLOR);
        drawPile.setEnabled(true);
        drawPile.setFocusPainted(false);
        discardPile.setEnabled(false);
        discardPile.setFocusPainted(false);
        this.add(drawPile);
        this.add(discardPile);
    }

    public void drawCard(){
        setCurrentCard(deck.drawCard());
    }

    public void setCurrentCard(GraphicalCard card) {
        if (this.discardPile == null) {
            this.add(card);
            discardPile = card;
        }
        else {
            discardPile = card;
        }
        discardPile.repaint();
    }
}