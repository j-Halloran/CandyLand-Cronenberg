package org.CandyLand.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TokenSpace extends JButton {

    public TokenSpace(Color color) {
        super();
        this.setEnabled(false);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        this.setBorder(emptyBorder);
        this.setBackground(color);
    }

    public void setToken(Token token) {
       // this.setEnabled(true);
        this.setIcon(token);
    }

    public Token getToken() {
        Icon icon = this.getIcon();
        if (icon instanceof Token) {
            return (Token)icon;
        }
        else {
            return null;
        }
    }

    public void removeToken() {
        this.setIcon(null);
        this.setEnabled(false);
    }

}