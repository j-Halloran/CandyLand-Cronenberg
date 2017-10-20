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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicalBoard graphicalBoard = new GraphicalBoard();
        frame.add(graphicalBoard);
        frame.pack();

        frame.setVisible(true);
    }
}