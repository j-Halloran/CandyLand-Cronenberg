package org.CandyLand.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.CandyLand.CardType;

public class MainFrame extends JComponent {

    private final int HEIGHT = 1000;
    private final int WIDTH = 1200;
    private final String TITLE = "World Of Sweets";
    private int numPlayers;
    private JFrame frame = new JFrame(TITLE);
    private File imageFile;
    private BufferedImage myImage;
    private static CardPanel cardPanel;
    private static GraphicalBoard graphicalBoard;

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

        Object[] options = {"2","3","4"};
        String userNumberPlayersResponse = (String) JOptionPane.showInputDialog(new JFrame(), "How Many Players (2-4): ","Enter Players",JOptionPane.QUESTION_MESSAGE,null,options,"2");
        if(userNumberPlayersResponse==null){
            System.exit(0);
        }
        numPlayers = Integer.parseInt(userNumberPlayersResponse);

        graphicalBoard = new GraphicalBoard();
        graphicalBoard.addInitialTokens(numPlayers);
        cardPanel = new CardPanel();


        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 4;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.fill = constraints.BOTH;
        frame.add(new StatusBarPanel(numPlayers), constraints);
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
        }
    }

    public static void reInitGame(){
        graphicalBoard
    }

    public JFrame getFrame(){
        return frame;
    }
    public CardPanel getPanel(){
        return cardPanel;
    }
    public int getNumPlayers(){
        return numPlayers;
    }
}