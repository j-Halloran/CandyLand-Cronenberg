package org.CandyLand.view;

import java.awt.*;
import javax.swing.*;
import org.CandyLand.view.GraphicalBoard;

public class MainFrame {

    private final int HEIGHT = 800;
    private final int WIDTH = 1200;
    private final String TITLE = "World Of Sweets";
    private final int NUM_PLAYERS=4; //hard coding until we get a start screen working
    private JFrame frame = new JFrame(TITLE);

    public MainFrame() {
        GridBagConstraints constraints = new GridBagConstraints();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GraphicalBoard graphicalBoard = new GraphicalBoard();
        graphicalBoard.addInitialTokens(NUM_PLAYERS);
        CardPanel cardPanel = new CardPanel();

        constraints.weightx = 3;
        constraints.weighty = 1;
        constraints.fill = constraints.BOTH;
        frame.add(cardPanel, constraints);
        constraints.weightx = 7;
        constraints.weighty = 1;
        constraints.fill = constraints.BOTH;
        frame.add(graphicalBoard, constraints);
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        frame.pack();
        frame.setVisible(true);
    }
}