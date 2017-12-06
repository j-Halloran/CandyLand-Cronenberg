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
import org.CandyLand.model.GameBoard;

public class MainFrame extends JComponent {

    private final int HEIGHT = 1000;
    private final int WIDTH = 1200;
    private final String TITLE = "World Of Sweets";
    private static int numPlayers;
    private JFrame frame = new JFrame(TITLE);
    private File imageFile;
    private BufferedImage myImage;
    public CardPanel cardPanel;
    public GraphicalBoard graphicalBoard;
    public StatusBarPanel stats;
    public TimePanel timePanel;

    public MainFrame(GameBoard board, PlayerPanel[] loadPanel) {
        // set Background Image of MainFrame
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource("CLbg2.jpg");
        imageFile = new File(url.getPath());
        try
        {
            myImage = ImageIO.read(imageFile);
        }
        catch (IOException e) {
            System.err.println("unable to load background image.");
        }
        frame.setContentPane(new ImagePanel(myImage));

        GridBagConstraints constraints = new GridBagConstraints();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        graphicalBoard = new GraphicalBoard(board);
        cardPanel = new CardPanel(board.isStrategic());
        if(loadPanel==null){
            stats = new StatusBarPanel(board.getPlayerNames(),board.isStrategic());
        }
        else{
            stats = new StatusBarPanel(loadPanel);
        }
        timePanel = new TimePanel();
        SaveButton saveButton = new SaveButton();

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.fill = constraints.BOTH;
        frame.add(timePanel, constraints);
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 2;
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.fill = constraints.BOTH;
        frame.add(stats, constraints);
        constraints.weightx = .5;
        constraints.weighty = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 4;
        constraints.gridy = 1;
        constraints.fill = constraints.BOTH;
        frame.add(saveButton, constraints);
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

    public void exit() {
        frame.setVisible(false);
        frame.dispose();
    }

    public StatusBarPanel getStats(){return stats;}
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
