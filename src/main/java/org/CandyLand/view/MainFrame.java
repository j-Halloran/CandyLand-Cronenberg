package org.CandyLand.view;

import java.awt.*;
import javax.swing.*;
import org.CandyLand.view.GraphicalBoard;

public class MainFrame {

    private final int HEIGHT = 1000;
    private final int WIDTH = 1200;
    private final String TITLE = "World Of Sweets";
    private int numPlayers=3; //hard coding until we get a start screen working
    private JFrame frame = new JFrame(TITLE);

    public MainFrame() {
        GridBagConstraints constraints = new GridBagConstraints();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        Object[] options = {"2","3","4"};
        String userNumberPlayersResponse = (String) JOptionPane.showInputDialog(new JFrame(), "How Many Players (2-4): ","Enter Players",JOptionPane.QUESTION_MESSAGE,null,options,"2");
        if(userNumberPlayersResponse==null){
            System.exit(0);
        }
        numPlayers = Integer.parseInt(userNumberPlayersResponse);

        GraphicalBoard graphicalBoard = new GraphicalBoard();
        graphicalBoard.addInitialTokens(numPlayers);
        CardPanel cardPanel = new CardPanel();

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
}