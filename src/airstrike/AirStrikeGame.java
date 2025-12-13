package airstrike;

import javax.swing.JFrame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class AirStrikeGame extends JFrame {

    public AirStrikeGame() {
        setTitle("Air Strike System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // allow the user to resize the window and keep window decorations
        setResizable(true);
        setUndecorated(false);

        GamePanel panel = new GamePanel();
        add(panel);

        // Let the panel determine preferred size (if implemented) and pack.
        pack();

        // Do NOT force fullscreen. Allow windowed mode so the user can resize the frame.
        setLocationRelativeTo(null);

        // Make the frame visible after packing
        setVisible(true);
    }

    public static void main(String[] args) {
        new AirStrikeGame();
    }
}
