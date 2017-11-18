package org.CandyLand.view;

import java.awt.*;
import javax.swing.*;

import org.CandyLand.CandyLand;

public class SaveButton extends JButton {

  private static final String TEXT = "Save Game";

  public SaveButton() {
      this.setText(TEXT);
      this.addActionListener(a -> {
          String fileName = Prompter.getFileSaveLocation();
          if (fileName != null) {
            CandyLand.saveGame(fileName);
          }
      });
  }

}
