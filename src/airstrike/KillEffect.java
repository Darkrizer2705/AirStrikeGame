package airstrike;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class KillEffect {

    int x, y; // top-left
    int width = 60;
    int height = 60;
    int lifeTicks = 12; // 12 ticks * 20ms = 240ms

    private static BufferedImage img = null;

    static {
        try {
            java.io.InputStream is = KillEffect.class.getResourceAsStream("/airstrike/images/kill.png");
            if (is != null) img = ImageIO.read(is);
        } catch (Exception ignored) {
            img = null;
        }
    }

    public KillEffect(int centerX, int centerY) {
        this.x = centerX - width/2;
        this.y = centerY - height/2;
    }

    public void update() {
        lifeTicks--;
    }

    public boolean isAlive() { return lifeTicks > 0; }

    public void draw(Graphics g) {
        if (img != null) {
            g.drawImage(img, x, y, width, height, null);
        } else {
            g.setColor(Color.RED);
            g.fillOval(x, y, width, height);
        }
    }
}
