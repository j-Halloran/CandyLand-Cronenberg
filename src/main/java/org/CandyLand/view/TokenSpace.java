package org.CandyLand.view;

import javax.swing.*;
import java.awt.*;

public class TokenSpace extends JButton {

    public TokenSpace(Color color) {
        super();
        this.setBackground(color);
    }

    public void setToken(Token token) {
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
    }

}