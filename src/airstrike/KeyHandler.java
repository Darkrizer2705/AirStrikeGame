package airstrike;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import airstrike.AudioManager;

public class KeyHandler implements KeyListener {

    Player player;
    GamePanel panel;
    // Key state flags to allow simultaneous inputs
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean firePressed = false;

    public KeyHandler(Player player, GamePanel panel) {
        this.player = player;
        this.panel = panel;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // Global mute toggle: press 'M' to mute/unmute audio
        if (e.getKeyCode() == KeyEvent.VK_M) {
            AudioManager.toggleMute();
            return;
        }

        if (!panel.gameStarted && e.getKeyCode() == KeyEvent.VK_ENTER) {
            panel.gameStarted = true;
            panel.timer.start();
            panel.requestFocusInWindow();
            return;
        }

        if (panel.gameStarted && !panel.gameOver &&
                e.getKeyCode() == KeyEvent.VK_P) {
            panel.paused = !panel.paused;
            panel.repaint();
            return;
        }

        if (panel.gameOver && e.getKeyCode() == KeyEvent.VK_R) {

            panel.gameOver = false;
            panel.paused = false;
            panel.gameStarted = true;

            panel.player.reset();
            panel.enemyManager.reset();
            panel.scoreManager.reset();

            panel.timer.start();
            panel.requestFocusInWindow();

            return;
        }

        if (panel.paused || panel.gameOver || !panel.gameStarted)
            return;

        // update key state flags instead of directly calling player methods
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            leftPressed = true;

        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            rightPressed = true;

        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            firePressed = true;
    }


    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            leftPressed = false;

        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            rightPressed = false;

        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            firePressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    // Accessors for GamePanel to read current key states
    public boolean isLeftPressed() { return leftPressed; }
    public boolean isRightPressed() { return rightPressed; }
    public boolean isFirePressed() { return firePressed; }
}
