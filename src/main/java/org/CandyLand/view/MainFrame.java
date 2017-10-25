package org.CandyLand.view;

import java.awt.*;
import javax.swing.*;
import org.CandyLand.view.GraphicalBoard;

public class MainFrame {

    private final int HEIGHT = 600;
    private final int WIDTH = 800;
    private final String TITLE = "World Of Sweets";

    private JFrame frame = new JFrame(TITLE);

    public MainFrame() {
        GridBagConstraints constraints = new GridBagConstraints();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GraphicalBoard graphicalBoard = new GraphicalBoard();
        CardPanel cardPanel = new CardPanel();
        //cardPanel.get

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