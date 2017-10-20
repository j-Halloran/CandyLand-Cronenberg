package org.CandyLand.view;

import java.awt.*;
import javax.swing.*;

public class GraphicalCard extends JButton {

    public GraphicalCard(CardType type) {
        setEnabled(false);
        switch (type) {
            case RED:
                setBackground(Color.RED);
                break;
            case YELLOW:
                setBackground(Color.YELLOW);
                break;
            case BLUE:
                setBackground(Color.BLUE);
                break;
            case GREEN:
                setBackground(Color.GREEN);
                break;
            case ORANGE:
                setBackground(Color.ORANGE);
                break;
            case UPSIDEDOWN:
                setBackground(Color.WHITE);
                setText(new String(Character.toChars(0x1f0a0)));
                setFont(new Font("TimesRoman", Font.PLAIN, 128));
                break;
        }
    }
}