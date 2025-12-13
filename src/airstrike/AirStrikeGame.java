package airstrike;

import javax.swing.JFrame;

public class AirStrikeGame extends JFrame {

    public AirStrikeGame() {
        setTitle("Air Strike System");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(new GamePanel());

        setVisible(true);
    }

    public static void main(String[] args) {
        new AirStrikeGame();
    }
}
