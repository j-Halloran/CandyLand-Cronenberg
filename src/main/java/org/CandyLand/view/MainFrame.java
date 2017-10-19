import java.awt.*;
import javax.swing.*;

public class MainFrame {

    private final int HEIGHT = 600;
    private final int WIDTH = 800;
    private final String TITLE = "World Of Sweets"

    private JFrame frame = new JFrame(TITLE);

    public MainFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}