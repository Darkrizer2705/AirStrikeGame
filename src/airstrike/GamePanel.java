package airstrike;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// background image removed; keep simple gradient rendering only

public class GamePanel extends JPanel implements ActionListener {

    Player player;
    EnemyManager enemyManager;
    CollisionManager collisionManager;
    ScoreManager scoreManager;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 900;

    Timer timer;
    boolean gameStarted = false;
    boolean paused = false;
    boolean gameOver = false;
    
    // Input handler reference so we can read key states each tick
    private KeyHandler keyHandler;

    // Simple fire cooldown (ticks). Timer runs every 20ms; 10 ticks = 200ms.
    private int fireCooldown = 0;


    public GamePanel() {
        setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        player = new Player();
        enemyManager = new EnemyManager();
        collisionManager = new CollisionManager();
        scoreManager = new ScoreManager();

        keyHandler = new KeyHandler(player, this);
        addKeyListener(keyHandler);

        timer = new Timer(20, this);
        timer.start();
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Background: draw a gradient first so something is visible even if the
        // image is transparent or contains large transparent areas.
        Graphics2D g2 = (Graphics2D) g;
        Color c1 = new Color(0, 0, 0);
        Color c2 = new Color(5, 5, 64);
        g2.setPaint(new java.awt.GradientPaint(0, 0, c1, 0, getHeight(), c2));
        g2.fillRect(0, 0, getWidth(), getHeight());

        // If the game hasn't started yet, show centered title/prompt over the background
        if (!gameStarted) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            String title = "AIR STRIKE";
            int titleW = g.getFontMetrics().stringWidth(title);
            int cx = getWidth() / 2;
            int cy = getHeight() / 2 - 40;
            g.drawString(title, cx - titleW / 2, cy);

            g.setFont(new Font("Arial", Font.PLAIN, 18));
            String prompt = "Press ENTER to Start";
            int pW = g.getFontMetrics().stringWidth(prompt);
            g.drawString(prompt, cx - pW / 2, cy + 40);
            return;
        }

        if (paused) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            String txt = "PAUSED";
            int w = g.getFontMetrics().stringWidth(txt);
            g.drawString(txt, getWidth() / 2 - w / 2, getHeight() / 2);

            g.setFont(new Font("Arial", Font.PLAIN, 16));
            String resume = "Press P to Resume";
            g.drawString(resume, getWidth() / 2 - g.getFontMetrics().stringWidth(resume) / 2, getHeight() / 2 + 30);
            return;
        }

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            String go = "GAME OVER";
            int gw = g.getFontMetrics().stringWidth(go);
            g.drawString(go, getWidth() / 2 - gw / 2, getHeight() / 2 - 20);

            g.setFont(new Font("Arial", Font.PLAIN, 18));
            String restart = "Press R to Restart";
            g.drawString(restart, getWidth() / 2 - g.getFontMetrics().stringWidth(restart) / 2, getHeight() / 2 + 20);
            
            // draw final score under the restart prompt
            String finalScore = "Final Score: " + scoreManager.getScore();
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString(finalScore, getWidth() / 2 - g.getFontMetrics().stringWidth(finalScore) / 2, getHeight() / 2 + 50);
            return;
        }

        // HUD: score and missile count
        scoreManager.draw(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Missiles: " + player.getMissileCount(), 20, 40);

        // Mute indicator (top-right)
        if (AudioManager.isMuted()) {
            String txt = "Muted (M to toggle)";
            FontMetrics fm = g.getFontMetrics();
            int tw = fm.stringWidth(txt);
            g.setColor(new Color(255, 100, 100));
            g.drawString(txt, getWidth() - tw - 10, 120);
            g.setColor(Color.WHITE);
        }

        // Controls panel
        int panelX = 600;
        int panelY = 10;
        int lineHeight = 16;
        g.setColor(new Color(0,0,0,150));
        g.fillRect(panelX - 10, panelY - 10, 170, 110);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("Controls:", panelX, panelY + (lineHeight * 0));
        g.drawString("Left/Right: Move", panelX, panelY + (lineHeight * 1));
        g.drawString("Space: Shoot", panelX, panelY + (lineHeight * 2));
        g.drawString("P: Pause", panelX, panelY + (lineHeight * 3));
        g.drawString("M: Mute", panelX, panelY + (lineHeight * 4));
        player.draw(g);
        enemyManager.draw(g);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (!gameStarted || paused || gameOver)
            return;

        // Handle continuous input based on key states
        if (keyHandler.isLeftPressed())
            player.moveLeft();

        if (keyHandler.isRightPressed())
            player.moveRight();

        if (fireCooldown > 0)
            fireCooldown--;

        if (keyHandler.isFirePressed() && fireCooldown <= 0) {
            player.fire();
            fireCooldown = 10; // ~200ms between shots
        }

        player.updateMissiles();
        enemyManager.updateEnemies(scoreManager);

        collisionManager.checkMissileHit(player, enemyManager, scoreManager);

        // check player pickups (collect missile drops by colliding with them)
        collisionManager.checkPlayerPickup(player, enemyManager);

        if (collisionManager.checkPlayerHit(player, enemyManager)) {
            gameOver = true;
            AudioManager.play("gameOver.wav");
            timer.stop();
        }

        repaint();
    }

}
