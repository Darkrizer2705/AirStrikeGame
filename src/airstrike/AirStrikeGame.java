package airstrike;

import javax.swing.JFrame;

public class AirStrikeGame extends JFrame {

    public AirStrikeGame() {
        setTitle("Air Strike System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        GamePanel panel = new GamePanel();
        add(panel);
        pack();

        // center on screen
        setLocationRelativeTo(null);

        setVisible(true);
    }

    public static void main(String[] args) {
        new AirStrikeGame();
    }
}
