package org.CandyLand;

import org.CandyLand.view.MainFrame;
import org.CandyLand.view.Prompter;
import org.CandyLand.view.GraphicalCard;
import org.CandyLand.model.CardDeck;
import org.CandyLand.model.GameBoard;
import org.CandyLand.model.Timer;
import org.CandyLand.view.StatusBarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.DigestInputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

public class CandyLand {

    private static int playerNum = 0;
    private static int gameMode = 0;
    private static GameBoard board;
    private static MainFrame mainFrame;
    private static Timer timer = new Timer();
    private static CardDeck deck = new CardDeck();
    private static final String HASH_EXTENSION = ".hash";
    private static final String SALT = "MSG";
    public static boolean AIplayers[];
    private static Thread AIPlayerThread;


    public static void main(String[] args) {
        promptNewGame();
        mainFrame = new MainFrame(board);
        mainFrame.graphicalBoard.setTokenLocations(board.getPlayerPositions());
        mainFrame.cardPanel.paintFromDeck(deck);
        timer.start();
        spawnAIPlayerThread();
        spawnTimerUpdateThread();
    }

    public static void spawnAIPlayerThread() {
        AIPlayerThread = new Thread() {
            public void run() {
                while (true) {

                    if(AIplayers[mainFrame.getStats().getCurrentPlayer()]){
                        StatusBarPanel.activateNextPlayer();
                        CandyLand.drawCard();
                    }
                    try {
                        Thread.sleep(500); // snooze for HALF a second
                    }
                    catch (InterruptedException e) {
                        // do nothing
                    }
                }
            }
        };
        AIPlayerThread.start();
    }


    public static void promptNewGame() {
        Prompter.NewGameOption option = Prompter.promptNewGame();
        if (option == Prompter.NewGameOption.NEWGAME) {
            promptGameMode();
            int numPlayers = Prompter.promptNumOfPlayers();
            String[] playerNames = Prompter.promptPlayerNames(numPlayers);

            AIplayers = new boolean[numPlayers];
            for (int i = 0; i < numPlayers; i++) {
                if(Prompter.computerPlayer(playerNames[i])){
                    AIplayers[i]= true;
                }
            }

            timer = new Timer();
            deck = new CardDeck();
            board = new GameBoard(numPlayers, playerNames);
        }
        else {
            String fileName = Prompter.getFileOpenLocation();
            if (fileName == null) {
                System.exit(0);
            }
            if (!loadGame(fileName)) {
                promptNewGame();
            }
        }
    }

    public static void promptGameMode(){
        Prompter.GameModeOption option = Prompter.promptGameMode();
        if (option == Prompter.GameModeOption.STRATEGIC){
            // set strategic mode
        }
        // else continue with Classic (Default) mode
    }

    public static void drawCard() {
        timer.start();
        CardType card;
        if(!board.getPlayerName(playerNum).equals("Dad")){
           card = deck.drawCard();
        }
        //Dad mode card draw
        else{
            card = deck.drawDadCard(board, playerNum);
        }
        board.movePlayer(playerNum, card);
        mainFrame.graphicalBoard.setTokenLocations(board.getPlayerPositions());
        mainFrame.cardPanel.setCurrentCard(new GraphicalCard(card));
        if (board.isGameOver()) {
            Prompter.ContinueOption option = Prompter.promptRematch(board.getPlayerName(playerNum));
            timer.reset();
            switch (option) {
                case REMATCH:
                    board.resetPlayersToStart();
                    deck.shuffleDeck();
                    mainFrame.graphicalBoard.setTokenLocations(board.getPlayerPositions());
                    timer.start();
                    break;
                case NEWGAME:
                    mainFrame.exit();
                    promptNewGame();
                    mainFrame = new MainFrame(board);
                    mainFrame.graphicalBoard.setTokenLocations(board.getPlayerPositions());
                    mainFrame.cardPanel.paintFromDeck(deck);
                    timer.start();
                    break;
                case EXIT:
                    System.exit(0);
                    break;
            }
        }
        else if (deck.isDeckEmpty()) {
            mainFrame.cardPanel.setDeckEmpty();
        }
        playerNum = (playerNum + 1) % board.numPlayers;
    }

    public static void shuffleDeck() {
        timer.start();
        deck.shuffleDeck();
        mainFrame.cardPanel.shuffleDeck();
    }
    static Thread timerUpdateThread;

    private static void spawnTimerUpdateThread() {



         timerUpdateThread = new Thread() {
            public void run() {
                while (true) {
                    mainFrame.timePanel.setTime(timer.getSeconds());

                    mainFrame.timePanel.getTimeButton().addActionListener(new TimerButtonListener());

                    try {
                        Thread.sleep(500); // snooze for HALF a second
                    }
                    catch (InterruptedException e) {
                        // do nothing
                    }
                }
            }
        };
        timerUpdateThread.start();


    }


    static class TimerButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                if(!timer.isRunning()){
                    timer.start();
                }else {
                    timer.stop();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }


    public static boolean saveGame(String fileName) {
        boolean success;
        try ( FileOutputStream fileOut = new FileOutputStream(fileName);
              ObjectOutputStream gameOut = new ObjectOutputStream(fileOut); ) {
            gameOut.writeObject(board);
            boolean running = timer.isRunning();
            timer.stop();
            gameOut.writeObject(timer);
            if (running) {
              timer.start();
            }
            gameOut.writeObject(deck);
            gameOut.close();
            success = true;
        } catch (IOException e) {
            success = false;
            e.printStackTrace();
        }
        success &= saveFileHash(fileName);
        return success;
    }

    public static boolean saveFileHash(String filename) {
        byte[] hash = getFileHash(filename);
        try (FileOutputStream hashOut = new FileOutputStream(filename + HASH_EXTENSION)) {
            hashOut.write(hash);
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }

    public static byte[] getFileHash(String filename) {
        MessageDigest sha256;
        try {
            sha256 = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            System.err.println("algorithm not supported");
            return null;
        }
        try (FileInputStream fileIn = new FileInputStream(filename)) {
            DigestInputStream sha256In = new DigestInputStream(fileIn, sha256);
            while (sha256In.available() > 0)
            {
                sha256In.read();
            }
        }
        catch (IOException e) {
            return null;
        }
        try {
            sha256.update(SALT.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException e) {
            System.err.println("encoding not supported");
            return null;
        }
        return sha256.digest();
    }

    public static boolean checkFileHash(String filename) {
        byte[] expectedHash = getFileHash(filename);
        if (expectedHash == null) {
            return false;
        }
        byte[] hashGot = new byte[expectedHash.length];
        try (FileInputStream hashIn = new FileInputStream(filename + HASH_EXTENSION)) {
            hashIn.read(hashGot);
        }
        catch (IOException e) {
            return false;
        }
        return Arrays.equals(expectedHash, hashGot);
    }

    public static boolean loadGame(String fileName) {
        boolean success;
        try ( FileInputStream fileIn = new FileInputStream(fileName);
              ObjectInputStream gameIn = new ObjectInputStream(fileIn); ) {
            board = (GameBoard)gameIn.readObject();
            timer = (Timer)gameIn.readObject();
            deck = (CardDeck)gameIn.readObject();
            success = true;
        } catch (IOException | ClassNotFoundException e) {
            success = false;
            e.printStackTrace();
        }
        boolean corrupt = !checkFileHash(fileName);
        success &= !corrupt;
        if (corrupt) {
            Prompter.notifyCorruptFile();
        }
        return success;
    }
}
