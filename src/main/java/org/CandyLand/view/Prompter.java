package org.CandyLand.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

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

    public static NewGameOption promptNewGame() {
        Object[] options = {"New Game", "Load Game"};
        int option = JOptionPane.showOptionDialog(
            null,
            null,
            null,
            0,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            null
        );
        if (option == JOptionPane.YES_OPTION) {
          return NewGameOption.NEWGAME;
        }
        else {
          return NewGameOption.LOADGAME;
        }
    }

    public static String getFileOpenLocation() {
        JFileChooser picker = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("World of Sweets games", "wos");
        picker.setFileFilter(filter);
        int returnValue = picker.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return picker.getCurrentDirectory().toString() + File.separator + picker.getSelectedFile().getName();
        }
        else {
          return null;
        }
    }

    public static String getFileSaveLocation() {
        JFileChooser picker = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("World of Sweets games", "wos");
        picker.setFileFilter(filter);
        int returnValue = picker.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String fileName = picker.getCurrentDirectory().toString() + File.separator + picker.getSelectedFile().getName();
            if (!fileName.endsWith(".wos")) {
              fileName += ".wos";
            }
            return fileName;
        }
        else {
          return null;
        }
    }

    public enum NewGameOption {
        NEWGAME,
        LOADGAME
    }

    public enum ContinueOption {
        REMATCH,
        NEWGAME,
        EXIT
    }
}
