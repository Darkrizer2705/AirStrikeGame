package airstrike;

import java.awt.*;

public class MissileDrop {

    int x;
    int y = 0;
    public static final int WIDTH = 36;
    public static final int HEIGHT = 36;
    int width = WIDTH;
    int height = HEIGHT;
    int speed = 3;

    public MissileDrop(int x) {
        this.x = x;
    }

    private static java.awt.image.BufferedImage dropImg = null;
    static {
        try {
            java.io.InputStream is = MissileDrop.class.getResourceAsStream("/airstrike/images/Drop.png");
            if (is != null) {
                dropImg = javax.imageio.ImageIO.read(is);
            } else {
                dropImg = javax.imageio.ImageIO.read(new java.io.File("assets/images/Drop.png"));
            }
        } catch (java.io.IOException e) {
            dropImg = null;
        }
    }

    public void move() {
        y += speed;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        if (dropImg != null) {
            g.drawImage(dropImg, x, y, width, height, null);
        } else {
            g.setColor(Color.CYAN);
            g.fillOval(x, y, width, height);
            g.setColor(Color.BLACK);
            g.drawString("M", x + 7, y + 16);
        }
    }
}
