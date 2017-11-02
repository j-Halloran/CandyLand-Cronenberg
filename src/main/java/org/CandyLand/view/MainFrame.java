package org.CandyLand.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.CandyLand.CardType;
import org.CandyLand.model.CardDeck;

public class MainFrame extends JComponent {

    private final int HEIGHT = 1000;
    private final int WIDTH = 1200;
    private final String TITLE = "World Of Sweets";
    private static int numPlayers;
    private JFrame frame = new JFrame(TITLE);
    private File imageFile;
    private BufferedImage myImage;
    private static CardPanel cardPanel;
    private static GraphicalBoard graphicalBoard;
    private static StatusBarPanel stats;

    public MainFrame() throws IOException {
        // set Background Image of MainFrame
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource("CLbg2.jpg");
        imageFile = new File(url.getPath());
        myImage = ImageIO.read(imageFile);
        frame.setContentPane(new ImagePanel(myImage));

        GridBagConstraints constraints = new GridBagConstraints();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        numPlayers = setNumOfPlayers();

        graphicalBoard = new GraphicalBoard();
        graphicalBoard.addInitialTokens(numPlayers);
        cardPanel = new CardPanel();
        stats = new StatusBarPanel(numPlayers);

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 4;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.fill = constraints.BOTH;
        frame.add(stats, constraints);
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.fill = constraints.BOTH;
        frame.add(cardPanel, constraints);
        constraints.gridwidth = 3;
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.fill = constraints.BOTH;
        frame.add(graphicalBoard, constraints);
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setVisible(true);
        frame.pack();
    }

    public static void drawCard(){
        GraphicalCard nextCard = cardPanel.drawCard();
        graphicalBoard.moveAvatar(StatusBarPanel.getCurrentPlayer(),nextCard);
        if(graphicalBoard.atGrandmas(StatusBarPanel.getCurrentPlayer()) == true){
            // reinitialize and/or rematch
            Object[] endOptions = {"Rematch", "New Game", "Quit"};
            int gameEndOption = JOptionPane.showOptionDialog(new JFrame(),
                    "PLayer " + StatusBarPanel.getCurrentPlayer() + " Wins!!!",
                    "End of Game Options", 0, JOptionPane.YES_NO_CANCEL_OPTION,
                    null, endOptions, "PHP");

            if (gameEndOption == JOptionPane.YES_OPTION){
                reInitBoard(false);
            } else if (gameEndOption == JOptionPane.NO_OPTION) {
                reInitBoard(true);
            } else {
                System.exit(0);
            }
        }
    }

    public static int setNumOfPlayers(){
        Object[] options = {"2","3","4"};
        String userNumberPlayersResponse = (String) JOptionPane.showInputDialog(new JFrame(), "How Many Players (2-4): ","Enter Players",JOptionPane.YES_NO_CANCEL_OPTION,null,options,"2");
        if(userNumberPlayersResponse==null){
            System.exit(0);
        }
        numPlayers = Integer.parseInt(userNumberPlayersResponse);
        return numPlayers;
    }

    public static void reInitBoard(boolean newGame){
        CardDeck.shuffleDeck();
        cardPanel.setCurrentCard(true, null);
        graphicalBoard.resetTokens();
        if(newGame == true) {
            graphicalBoard.clearTokens();
            graphicalBoard.addInitialTokens(setNumOfPlayers());
            stats.setNumberOfPLayers(numPlayers);
        }
    }

    public JFrame getFrame(){
        return frame;
    }
    public CardPanel getPanel(){
        return cardPanel;
    }
    //for UNit testing purposes
    public int getPlayerPosition(int playerNumber){
        return graphicalBoard.getPlayerLocation(playerNumber);
    }
}