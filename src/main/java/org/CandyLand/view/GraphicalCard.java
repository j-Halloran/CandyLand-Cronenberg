package org.CandyLand.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GraphicalCard extends JButton {
    private boolean isDouble;

    public GraphicalCard(CardType type) {
        setEnabled(false);
        switch (type) {
            case UPSIDEDOWN:
                setBackground(Color.WHITE);
                setText(new String(Character.toChars(0x1f0a0)));
                setFont(new Font("TimesRoman", Font.PLAIN, 256));
                this.addActionListener(e -> {
                    StatusBarPanel.activateNextPlayer();
                    MainFrame.drawCard();
                });
                break;
            case EMPTY_DISCARD:
                setBackground(Color.WHITE);
                setText("Discard Deck Empty");
                setFont(new Font("TimesRoman", Font.PLAIN, 24));
                break;
        }
        this.isDouble = false;
    }
    public GraphicalCard(CardType type, boolean isDouble) {
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
        }
        this.isDouble = isDouble;
    }
}