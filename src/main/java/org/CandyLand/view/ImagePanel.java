package org.CandyLand.view;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JComponent {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // image, positioning, and dimensions
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}