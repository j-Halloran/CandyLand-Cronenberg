package org.CandyLand;

import org.CandyLand.view.MainFrame;

import java.io.IOException;

public class CandyLand {

    public static void main(String[] args) {
        try{
            MainFrame mainFrame = new MainFrame();
        }catch(IOException e){
            System.err.println(e);
            System.exit(1);
        }

    }

}