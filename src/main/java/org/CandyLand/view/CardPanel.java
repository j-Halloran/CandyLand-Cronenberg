package org.CandyLand.view;

import org.CandyLand.model.CardDeck;

import java.awt.*;
import javax.swing.*;
import org.CandyLand.CardType;

public class CardPanel extends JPanel {

    private static final Color BACKGROUND_COLOR = Color.GRAY;
    protected GraphicalCard drawPile =
            new GraphicalCard(CardType.UPSIDEDOWN);
    protected GraphicalCard discardPile = new GraphicalCard(CardType.EMPTY_DISCARD);
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

    public GraphicalCard drawCard(){
        GraphicalCard card = new GraphicalCard(deck.drawCard());
        if (deck.isDeckEmpty()) {
            this.remove(drawPile);
            this.drawPile = new GraphicalCard(CardType.EMPTY_DRAW);
            this.drawPile.setEnabled(false);
            this.add(drawPile);
            this.revalidate();
            this.repaint();
            card.addShuffleListener(this);
        }
        setCurrentCard(card);
        return card;
    }

    public void setCurrentCard(GraphicalCard card) {
        this.remove(discardPile);
        if (this.discardPile == null) {
            this.add(card);
            discardPile = card;
        }
        else {
            discardPile = card;
        }
        this.add(discardPile);
        this.revalidate();
        this.repaint();
    }

    public void shuffleDeck() {
        this.remove(drawPile);
        this.remove(discardPile);
        drawPile = new GraphicalCard(CardType.UPSIDEDOWN);
        discardPile = new GraphicalCard(CardType.EMPTY_DISCARD);
        drawPile.setEnabled(true);
        drawPile.setFocusPainted(false);
        discardPile.setEnabled(false);
        discardPile.setFocusPainted(false);
        this.add(drawPile);
        this.add(discardPile);
        this.revalidate();
        this.repaint();
    }

    public GraphicalCard getDiscardPile(){
        return discardPile;
    }
}