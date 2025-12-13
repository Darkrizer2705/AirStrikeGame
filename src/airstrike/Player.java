package airstrike;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player {

    int x = (GamePanel.WIDTH - 40) / 2;
    int y = GamePanel.HEIGHT - 100;
    public static final int WIDTH = 60;
    public static final int HEIGHT = 60;
    int width = WIDTH;
    int height = HEIGHT;
    private static BufferedImage playerImg = null;

    static {
        try {
            java.io.InputStream is = Player.class.getResourceAsStream("/airstrike/images/player.png");
            if (is != null) {
                playerImg = ImageIO.read(is);
            } else {
                // fallback to filesystem
                playerImg = ImageIO.read(new File("assets/images/player.png"));
            }
        } catch (IOException e) {
            playerImg = null;
        }
    }

    ArrayList<Missile> missiles = new ArrayList<>();
    // Limited missile ammo
    private int missileCount = 10;
    private final int maxMissiles = 100;

    public void moveLeft() {
        if (x > 0)
            x -= 10;
    }

    public void moveRight() {
        if (x < GamePanel.WIDTH - width)
            x += 10;
    }

    public void fire() {
        if (missileCount <= 0)
            return;

        int mx = x + width / 2 - Missile.WIDTH / 2;
        missiles.add(new Missile(mx, y));
        AudioManager.play("shoot.wav");
        missileCount--;
    }

    public void updateMissiles() {
        for (int i = 0; i < missiles.size(); i++) {
            missiles.get(i).move();
            if (missiles.get(i).y < 0) {
                missiles.remove(i);
                i--;
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getMissileCount() { return missileCount; }

    public void addMissiles(int count) {
        missileCount += count;
        if (missileCount > maxMissiles) missileCount = maxMissiles;
    }

    public void draw(Graphics g) {
        if (playerImg != null) {
            g.drawImage(playerImg, x, y, width, height, null);
        } else {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, width, height);
        }

        for (Missile m : missiles)
            m.draw(g);
    }

        public void reset() {
            x = (GamePanel.WIDTH - width) / 2;
            y = GamePanel.HEIGHT - 100;
        missiles.clear();
            missileCount = 50;
    }

}
