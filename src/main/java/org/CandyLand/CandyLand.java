package org.CandyLand;

import org.CandyLand.view.MainFrame;

import java.io.IOException;

public class CandyLand {

    public static void main(String[] args) {
        try{
            MainFrame mainFrame = new MainFrame(0);
        }catch(IOException e){
            System.err.println("Background image not found.");
            System.exit(1);
        }

    }

}