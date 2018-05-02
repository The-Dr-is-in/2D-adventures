import javax.swing.*;
import java.awt.*;

public class TwoDeeAdventures {

    private static JFrame frame;

    public static void main(String[] args) {
        initFrame();
    }
    public static void replaceContentPane(JPanel panel) {
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.validate();
        frame.repaint();
    }
    private static void initFrame() {
        frame = new JFrame("2D Platformer Game LoL");

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
