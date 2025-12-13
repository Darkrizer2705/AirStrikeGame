package airstrike;

import java.awt.*;

public class Missile {

    int x, y;
    int width = 4;
    int height = 10;

    public Missile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        y -= 8;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
    }
}
