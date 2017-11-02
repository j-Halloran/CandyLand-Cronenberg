package org.CandyLand.view;

import java.awt.*;
import javax.swing.*;

public class GamePathSpace extends JPanel {

    private static int TOKEN_SPACE_ROWS = 2;
    private static int TOKEN_SPACE_COLS = 2;
    private TokenSpace[] tokenSpaces =
            new TokenSpace[TOKEN_SPACE_ROWS * TOKEN_SPACE_COLS];
    private Color spaceColor;
    private Image image = null;

    public GamePathSpace(Color color) {
        super();
        this.setLayout(new GridLayout(TOKEN_SPACE_ROWS, TOKEN_SPACE_COLS));

        for (int i = 0; i < tokenSpaces.length; i++) {
            tokenSpaces[i] = new TokenSpace(color);
        }

        for (TokenSpace space : tokenSpaces) {
            this.add(space);
        }

        spaceColor = color;
    }
    public GamePathSpace(Color color, Image image) {
        super();
        this.setLayout(new GridLayout(1, 1));
        tokenSpaces[0] = new TokenSpace(color);
        //this.add(tokenSpaces[0]);
        spaceColor = color;
        this.image = image;
    }

    public void addToken(Token token) throws NoSpaceForTokenException {
        for (TokenSpace space : tokenSpaces) {
            if (space.getToken() == null) {
                space.setToken(token);
                return;
            }
        }

        throw new NoSpaceForTokenException();
    }

    public void removeToken(Token token) {
        for(TokenSpace space : tokenSpaces) {
            if (space.getToken() == token) {
                space.removeToken();
            }
        }
    }

    public Color getSpaceColor(){
        return spaceColor;
    }

    public static int getMaxPlayerCount(){
        return TOKEN_SPACE_COLS * TOKEN_SPACE_ROWS;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (image != null)
        {
            g.drawImage(image,0,0, getWidth(), getHeight(),this);
        }
    }
}