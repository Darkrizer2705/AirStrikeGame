package airstrike;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    Player player;

    public KeyHandler(Player player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            player.moveLeft();

        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            player.moveRight();

        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            player.fire();
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
