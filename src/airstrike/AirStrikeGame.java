package airstrike;

import javax.swing.JFrame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class AirStrikeGame extends JFrame {

    public AirStrikeGame() {
        setTitle("Air Strike System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true); 

        GamePanel panel = new GamePanel();
        add(panel);
        pack();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        
        if (gd.isFullScreenSupported()) {
            gd.setFullScreenWindow(this);
        } else {
            
            setLocationRelativeTo(null);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new AirStrikeGame();
    }
}
