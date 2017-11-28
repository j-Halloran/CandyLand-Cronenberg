package org.CandyLand;

import org.CandyLand.view.MainFrame;
import org.CandyLand.view.Prompter;
import org.CandyLand.view.GraphicalCard;
import org.CandyLand.model.CardDeck;
import org.CandyLand.model.GameBoard;
import org.CandyLand.model.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CandyLand {

    private static int playerNum = 0;
    private static GameBoard board;
    private static MainFrame mainFrame;
    private static Timer timer = new Timer();
    private static CardDeck deck = new CardDeck();

    public static void main(String[] args) {
        promptNewGame();
        mainFrame = new MainFrame(board);
        mainFrame.graphicalBoard.setTokenLocations(board.getPlayerPositions());
        mainFrame.cardPanel.paintFromDeck(deck);
        timer.start();
        spawnTimerUpdateThread();
    }

    public static void promptNewGame() {
        Prompter.NewGameOption option = Prompter.promptNewGame();
        if (option == Prompter.NewGameOption.NEWGAME) {
            int numPlayers = Prompter.promptNumOfPlayers();
            String[] playerNames = Prompter.promptPlayerNames(numPlayers);
            timer = new Timer();
            deck = new CardDeck();
            board = new GameBoard(numPlayers, playerNames);
        }
        else {
            String fileName = Prompter.getFileOpenLocation();
            if (fileName == null) {
                System.exit(0);
            }
            loadGame(fileName);
        }
    }

    public static void drawCard() {
        timer.start();
        CardType card = deck.drawCard();
        board.movePlayer(playerNum, card);
        mainFrame.graphicalBoard.setTokenLocations(board.getPlayerPositions());
        mainFrame.cardPanel.setCurrentCard(new GraphicalCard(card));
        if (board.isGameOver()) {
            Prompter.ContinueOption option = Prompter.promptRematch(playerNum + 1);
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
                        Thread.sleep(500); // snooze for a second
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
        return success;
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
        return success;
    }
}
