package org.CandyLand.view;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class StatusBarPanel extends JPanel{
    private static final Color BACKGROUND_COLOR = Color.GRAY;
    private static PlayerPanel[] players;

    public StatusBarPanel(int numPlayers){
        this.setLayout(new GridLayout(1, numPlayers));
        this.setBackground(BACKGROUND_COLOR);
        players = new PlayerPanel[numPlayers];
        for(int i=0;i<numPlayers;i++){
            players[i] = new PlayerPanel(i);
            this.add(players[i]); //add a panel for each player (1st is auto-selected as active)
        }
    }

    public static void activateNextPlayer(){
        for(int i =0;i<players.length;i++){
            if(players[i].isActive()){
                players[i].deactivatePlayer();
                players[(i+1)%players.length].activatePlayer();
                break;
            }
        }
    }

    public static int getNumberOfPlayers(){
        return players.length;
    }

    public static int getCurrentTurn(int player) throws IOException{
        if(player >= players.length){
            throw new IOException("Invalid player");
        }

        if(players[player-1].isActive()) {
            // it is this players turn
            return 0;
        }

        // it is ot currently this players turn
        else return 1;
    }
}
