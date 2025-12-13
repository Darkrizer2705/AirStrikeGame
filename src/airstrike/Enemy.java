package airstrike;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Enemy {

    int x, y;
    public static final int WIDTH = 60;
    public static final int HEIGHT = 60;
    int width = WIDTH;
    int height = HEIGHT;

    private static BufferedImage enemyImg = null;

    static {
        try {
            java.io.InputStream is = Enemy.class.getResourceAsStream("/airstrike/images/enemy.png");
            if (is != null) {
                enemyImg = ImageIO.read(is);
            } else {
                enemyImg = ImageIO.read(new File("assets/images/enemy.png"));
            }
        } catch (IOException e) {
            enemyImg = null;
        }
    }

    public Enemy(int x) {
        this.x = x;
        this.y = 0;
    }

    public void move() {
        y += 3;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        if (enemyImg != null) {
            g.drawImage(enemyImg, x, y, width, height, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
        }
    }
}
