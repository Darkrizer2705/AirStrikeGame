package airstrike;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Missile {

    int x, y;
    public static final int WIDTH = 6;
    public static final int HEIGHT = 15;
    int width = WIDTH;
    int height = HEIGHT;

    private static BufferedImage missileImg = null;

    static {
        try {
            java.io.InputStream is = Missile.class.getResourceAsStream("/airstrike/images/missile.png");
            if (is != null) {
                missileImg = ImageIO.read(is);
            } else {
                missileImg = ImageIO.read(new File("assets/images/missile.png"));
            }
        } catch (IOException e) {
            missileImg = null;
        }
    }

    public Missile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        y -= 24; // triple the original speed (8 -> 24)
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        if (missileImg != null) {
            g.drawImage(missileImg, x, y, width, height, null);
        } else {
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, width, height);
        }
    }
}
