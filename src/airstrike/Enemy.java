package airstrike;

import java.awt.*;

public class Enemy {

    int x, y;
    int width = 40;
    int height = 40;

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
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }
}
