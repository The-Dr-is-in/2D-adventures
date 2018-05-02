import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow(){
        setTitle("2D Adventures");
        setSize(700, 500);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel canvas = new JPanel();
    }
}
