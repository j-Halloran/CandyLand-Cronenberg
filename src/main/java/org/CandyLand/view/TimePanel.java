package org.CandyLand.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TimePanel extends JPanel {

    private static final Color ACTIVE_COLOR = Color.BLACK;
    private static final Color INACTIVE_COLOR = Color.RED;
    private static JButton timeButton = new JButton();

    public TimePanel() {
        this.setLayout(new BorderLayout());
        this.add(timeButton);
    }

    public void setTime(long seconds) {
        long minutes = seconds / 60;
        seconds = seconds % 60;
        String time = minutes + ":" + (seconds < 10 ? "0" : "") + seconds;
        timeButton.setText(time);
    }

    public JButton getTimeButton(){
        return timeButton;
    }

    public void enable() {
        timeButton.setForeground(ACTIVE_COLOR);
    }

    public void disable() {
        timeButton.setForeground(INACTIVE_COLOR);
    }

//    private class TimerButtonListener implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//
//        }
//    }

}