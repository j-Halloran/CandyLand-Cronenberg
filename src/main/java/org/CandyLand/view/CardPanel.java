package org.CandyLand.view;

import java.awt.*;
import javax.swing.*;

public class CardPanel extends JPanel {

    private static final Color BACKGROUND_COLOR = Color.GRAY;
    private GraphicalCard drawPile =
            new GraphicalCard(CardType.UPSIDEDOWN);
    private GraphicalCard discardPile;

    public CardPanel() {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(BACKGROUND_COLOR);
        drawPile.setEnabled(true);
        this.add(drawPile);
    }

    public void setCurrentCard(CardType type) {
        GraphicalCard card = new GraphicalCard(type);
        if (this.discardPile == null) {
            this.add(card);
            discardPile = card;
        }
        else {
            discardPile = card;
        }
    }
}