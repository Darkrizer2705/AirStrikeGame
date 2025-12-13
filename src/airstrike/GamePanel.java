package airstrike;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {

    Player player;
    EnemyManager enemyManager;
    CollisionManager collisionManager;
    ScoreManager scoreManager;

    Timer timer;
    boolean gameOver = false;

    public GamePanel() {
        setBackground(Color.BLACK);
        setFocusable(true);

        player = new Player();
        enemyManager = new EnemyManager();
        collisionManager = new CollisionManager();
        scoreManager = new ScoreManager();

        addKeyListener(new KeyHandler(player));

        timer = new Timer(20, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("GAME OVER", 150, 300);
            return;
        }

        player.draw(g);
        enemyManager.draw(g);
        scoreManager.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) return;

        player.updateMissiles();
        enemyManager.updateEnemies();

        if (collisionManager.checkMissileHit(player, enemyManager, scoreManager)) {
            // hit detected
        }

        if (collisionManager.checkPlayerHit(player, enemyManager)) {
            gameOver = true;
            timer.stop();
        }

        repaint();
    }
}
