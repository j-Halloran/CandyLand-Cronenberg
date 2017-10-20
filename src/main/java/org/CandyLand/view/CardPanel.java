package org.CandyLand.view;

import java.awt.*;
import javax.swing.*;

public class CardPanel extends JPanel {

    private static final Color BACKGROUND_COLOR = Color.GRAY;
    private GraphicalCard drawPile;
    private GraphicalCard discardPile;

    public CardPanel() {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(BACKGROUND_COLOR);
        this.add(drawPile);
    }

}