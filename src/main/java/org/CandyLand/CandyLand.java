package org.CandyLand;

import org.CandyLand.view.MainFrame;
import org.CandyLand.view.Prompter;
import org.CandyLand.view.GraphicalCard;
import org.CandyLand.model.CardDeck;
import org.CandyLand.model.GameBoard;

import java.io.IOException;

public class CandyLand {

    private static int playerNum = 0;
    private static int numPlayers;
    private static GameBoard board;
    private static MainFrame mainFrame;

    public static void main(String[] args) {
        numPlayers = Prompter.promptNumOfPlayers();
        board = new GameBoard(numPlayers);
        mainFrame = new MainFrame(board);
        mainFrame.graphicalBoard.setTokenLocations(board.getPlayerPositions());
    }

    public static void drawCard() {
        CardType card = CardDeck.drawCard();
        board.movePlayer(playerNum, card);
        mainFrame.graphicalBoard.setTokenLocations(board.getPlayerPositions());
        mainFrame.cardPanel.setCurrentCard(new GraphicalCard(card));
        if (board.isGameOver()) {
            Prompter.ContinueOption option = Prompter.promptRematch(playerNum);
            switch (option) {
                case REMATCH:
                    board.resetPlayersToStart();
                    CardDeck.shuffleDeck();
                    mainFrame.graphicalBoard.setTokenLocations(board.getPlayerPositions());
                    break;
                case NEWGAME:
                    numPlayers = Prompter.promptNumOfPlayers();
                    mainFrame.exit();
                    board = new GameBoard(numPlayers);
                    mainFrame = new MainFrame(board);
                    break;
                case EXIT:
                    System.exit(0);
                    break;
            }
        }
        else if (CardDeck.isDeckEmpty()) {
            mainFrame.cardPanel.setDeckEmpty();
        }
        playerNum = (playerNum + 1) % numPlayers;
    }

    public static void shuffleDeck() {
        CardDeck.shuffleDeck();
        mainFrame.cardPanel.shuffleDeck();
    }

}