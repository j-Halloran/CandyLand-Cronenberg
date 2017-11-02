package org.CandyLand.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import org.CandyLand.CardType;

public class GraphicalCard extends JButton {
    private boolean isDouble;
    private CardType cardType;

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
                this.isDouble = false;
                break;
            case EMPTY_DISCARD:
                setBackground(Color.WHITE);
                setText("Discard Deck Empty");
                setFont(new Font("TimesRoman", Font.PLAIN, 24));
                this.isDouble = false;
                break;
            case EMPTY_DRAW:
                setBackground(Color.WHITE);
                setText("Draw Deck Empty");
                setFont(new Font("TimesRoman", Font.PLAIN, 24));
                this.isDouble = false;
                break;
            case SINGLE_RED:
                setBackground(Color.RED);
                this.isDouble = false;
                break;
            case SINGLE_YELLOW:
                setBackground(Color.YELLOW);
                this.isDouble = false;
                break;
            case SINGLE_BLUE:
                setBackground(Color.BLUE);
                this.isDouble = false;
                break;
            case SINGLE_GREEN:
                setBackground(Color.GREEN);
                this.isDouble = false;
                break;
            case SINGLE_ORANGE:
                setBackground(Color.ORANGE);
                this.isDouble = false;
                break;
            case DOUBLE_RED:
                setBackground(Color.RED);
                this.isDouble = true;
                break;
            case DOUBLE_YELLOW:
                setBackground(Color.YELLOW);
                this.isDouble = true;
                break;
            case DOUBLE_BLUE:
                setBackground(Color.BLUE);
                this.isDouble = true;
                break;
            case DOUBLE_GREEN:
                setBackground(Color.GREEN);
                this.isDouble = true;
                break;
            case DOUBLE_ORANGE:
                setBackground(Color.ORANGE);
                this.isDouble = true;
                break;
            case SKIP_TURN:
                setBackground(Color.BLACK);
                setForeground(Color.WHITE);
                setText("Skip Turn");
                setFont(new Font("TimesRoman", Font.PLAIN, 24));
                this.isDouble = false;
                break;
            case GO_TO_MIDDLE:
                setBackground(Color.BLACK);
                setForeground(Color.WHITE);
                setText("Go To Middle");
                setFont(new Font("TimesRoman", Font.PLAIN, 24));
                this.isDouble = false;
                break;
        }
        if(isDouble){
            setText("Double");
            setFont(new Font("TimesRoman", Font.PLAIN, 24));
        }
        cardType = type;
    }

    public boolean isDouble(){
        return isDouble;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void addShuffleListener(CardPanel panel) {
        this.setEnabled(true);
        this.addActionListener(e -> {
            panel.shuffleDeck();
        });
    }
}