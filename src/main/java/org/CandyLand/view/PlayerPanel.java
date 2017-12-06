package org.CandyLand.view;

import org.CandyLand.CandyLand;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {

    private static final Color ACTIVE_BACKGROUND = Color.WHITE;
    private static final Color INACTIVE_BACKGROUND = Color.GRAY;
    private static boolean isActive;
    private static int playersCreated = 0;
    private static int[] boomerangsRemaining = {CandyLand.STARTING_BOOMERANGS,CandyLand.STARTING_BOOMERANGS,CandyLand.STARTING_BOOMERANGS,CandyLand.STARTING_BOOMERANGS};
    private String playerName;

    public PlayerPanel(String playerName, boolean active, boolean isStrategic){

        JLabel playerNameLabel = new JLabel(playerName, 0);
        this.playerName = playerName;
        if(isStrategic){
            playerNameLabel.setText("<html>"+playerName + "<br>Boomerangs Remaining: "+boomerangsRemaining[playersCreated++]+"</html>");
        }
        this.setLayout(new BorderLayout());
        this.add(playerNameLabel);
        if(active){
            this.setBackground(ACTIVE_BACKGROUND);
            this.isActive = true;
        }
        else{
            this.setBackground(INACTIVE_BACKGROUND);
            this.isActive = false;
        }
    }
    public void activatePlayer(){
        this.setBackground(ACTIVE_BACKGROUND);
    }
    public void deactivatePlayer(){
        this.setBackground(INACTIVE_BACKGROUND);
    }
    public boolean isActive() {
        return this.getBackground().equals(ACTIVE_BACKGROUND);
    }
    public void useBoomerang(int playerNum){
        for(Component jc: this.getComponents()){
            if(jc instanceof JLabel){
                boomerangsRemaining[playerNum]--;
                ((JLabel) jc).setText("<html>"+playerName + "<br>Boomerangs Remaining: "+boomerangsRemaining[playerNum]+"</html>");
            }
        }
    }
    public void resetBoomerangs(int playerNum){
        playersCreated = 0;
        for(int i=0;i<4;i++){
            boomerangsRemaining[i] = CandyLand.STARTING_BOOMERANGS;
        }
        for(Component jc: this.getComponents()){
            if(jc instanceof JLabel){
                ((JLabel) jc).setText("<html>"+playerName + "<br>Boomerangs Remaining: "+boomerangsRemaining[playerNum]+"</html>");
            }
        }
    }
}
