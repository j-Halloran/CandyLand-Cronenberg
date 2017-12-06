package org.CandyLand.view;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.*;

public class StatusBarPanel extends JPanel implements Serializable{
    private static final Color BACKGROUND_COLOR = Color.GRAY;
    private static PlayerPanel[] players;

    public StatusBarPanel(String[] playerNames, boolean isStrategic){
        int numPlayers = playerNames.length;
        this.setLayout(new GridLayout(1, numPlayers));
        this.setBackground(BACKGROUND_COLOR);
        players = new PlayerPanel[numPlayers];
        for(int i=0;i<numPlayers;i++){
            players[i] = new PlayerPanel(playerNames[i], i == 0, isStrategic);
            this.add(players[i]); //add a panel for each player (1st is auto-selected as active)
        }
    }

    public StatusBarPanel(PlayerPanel[] players){
        this.players = players;
        this.setLayout(new GridLayout(1,players.length));
        for(PlayerPanel player: players){
            this.add(player);
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

    public static void useBoomerang(){
        for(int i =0;i<players.length;i++){
            if(players[i].isActive()){
                players[(i+(getNumberOfPlayers()-1))%getNumberOfPlayers()].useBoomerang(i);
                break;
            }
        }
    }

    public static int getNumberOfPlayers(){
        return players.length;
    }

    public static int getCurrentPlayer(){
        for(int i=0;i<players.length;i++){
            if(players[i].isActive()){
                return i;
            }
        }
        return -1;
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

    public static void resetBoomerangs(){
        int i =0;
        for(PlayerPanel panel : players){
            panel.resetBoomerangs(i++);
        }
    }

    public static PlayerPanel[] getPlayers(){
        return players;
    }
}
