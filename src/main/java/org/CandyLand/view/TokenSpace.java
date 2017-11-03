package org.CandyLand.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TokenSpace extends JButton {
    private Image image = null;

    public TokenSpace(Color color) {
        super();
        this.setEnabled(false);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        this.setBorder(emptyBorder);
        this.setBackground(color);
    }
    public TokenSpace(Color color, Image image){
        this(color);
        this.setIcon(new ImageIcon(image));
        this.setDisabledIcon(new ImageIcon(image));
        this.image = image;
    }

    public void setToken(Token token) {
        this.setEnabled(false);
        this.setIcon(token);
        this.setDisabledIcon(token);
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
        this.setDisabledIcon(null);
        this.setIcon(null);
        this.setEnabled(false);
        if(image!=null){
            this.setDisabledIcon(new ImageIcon(image));
            this.setIcon(new ImageIcon(image));
        }
    }

}