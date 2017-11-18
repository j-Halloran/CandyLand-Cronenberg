package org.CandyLand.view;

import org.CandyLand.model.CardDeck;

import java.awt.*;
import javax.swing.*;
import org.CandyLand.CardType;
import org.CandyLand.CandyLand;

public class CardPanel extends JPanel {

    private static final Color BACKGROUND_COLOR = Color.GRAY;
    protected GraphicalCard drawPile =
            new GraphicalCard(CardType.UPSIDEDOWN);
    protected GraphicalCard discardPile = new GraphicalCard(CardType.EMPTY_DISCARD);

    public CardPanel() {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(BACKGROUND_COLOR);
        drawPile.setEnabled(true);
        drawPile.setFocusPainted(false);
        discardPile.setEnabled(false);
        discardPile.setFocusPainted(false);
        this.add(drawPile);
        this.add(discardPile);
    }

    public void setDeckEmpty () {
        this.remove(drawPile);
        this.remove(discardPile);
        this.drawPile = new GraphicalCard(CardType.EMPTY_DRAW);
        this.drawPile.setEnabled(false);
        this.add(drawPile);
        this.add(discardPile);
        this.revalidate();
        this.repaint();
        discardPile.addShuffleListener(this);
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

    public void paintFromDeck(CardDeck deck) {
        CardType lastDrawn = deck.getLastDrawn();
        if (lastDrawn != null) {
              setCurrentCard(new GraphicalCard(lastDrawn));
          }
          if (deck.isDeckEmpty()) {
              setDeckEmpty();
          }
      }
  }
