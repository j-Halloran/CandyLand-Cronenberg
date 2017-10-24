package org.CandyLand.view;

import java.awt.*;
import javax.swing.*;

public class GamePathSpace extends JPanel {

    private static int TOKEN_SPACE_ROWS = 2;
    private static int TOKEN_SPACE_COLS = 2;
    private TokenSpace[] tokenSpaces =
            new TokenSpace[TOKEN_SPACE_ROWS * TOKEN_SPACE_COLS];

    public GamePathSpace(Color color) {
        super();
        this.setLayout(new GridLayout(TOKEN_SPACE_ROWS, TOKEN_SPACE_COLS));

        for (int i = 0; i < tokenSpaces.length; i++) {
            tokenSpaces[i] = new TokenSpace(color);
        }

        for (TokenSpace space : tokenSpaces) {
            this.add(space);
        }
    }

    public void addToken(Token token) throws NoSpaceForTokenException {
        for (TokenSpace space : tokenSpaces) {
            if (space.getToken() == null) {
                System.out.println("woo");
                space.setToken(token);
                System.out.println("woo3");
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

    public static int getMaxPlayerCount(){
        return TOKEN_SPACE_COLS * TOKEN_SPACE_ROWS;
    }
}