package org.CandyLand.view;

import java.awt.*;
import javax.swing.*;

public class Prompter {

    public static int promptNumOfPlayers(){
        Object[] options = {"2","3","4"};
        String userNumberPlayersResponse = (String) JOptionPane.showInputDialog(new JFrame(), "How Many Players (2-4): ","Enter Players",JOptionPane.YES_NO_CANCEL_OPTION,null,options,"2");
        if(userNumberPlayersResponse==null){
            System.exit(0);
        }
        return Integer.parseInt(userNumberPlayersResponse);
    }

    public static ContinueOption promptRematch(int winner) {
        Object[] endOptions = {"Rematch", "New Game", "Quit"};
        int gameEndOption = JOptionPane.showOptionDialog(new JFrame(),
                "Player " + winner + " Wins!!!",
                "End of Game Options", 0, JOptionPane.YES_NO_CANCEL_OPTION,
                null, endOptions, "PHP");

        if (gameEndOption == JOptionPane.YES_OPTION){
            return ContinueOption.REMATCH;
        } else if (gameEndOption == JOptionPane.NO_OPTION) {
            return ContinueOption.NEWGAME;
        } else {
            return ContinueOption.EXIT;
        }
    }

    public enum ContinueOption {
        REMATCH,
        NEWGAME,
        EXIT
    }
}