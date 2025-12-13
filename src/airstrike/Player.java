package airstrike;

import java.awt.*;
import java.util.ArrayList;

public class Player {

    int x = 220;
    int y = 500;
    int width = 40;
    int height = 40;

    ArrayList<Missile> missiles = new ArrayList<>();

    public void moveLeft() {
        if (x > 0)
            x -= 10;
    }

    public void moveRight() {
        if (x < 460)
            x += 10;
    }

    public void fire() {
        missiles.add(new Missile(x + 18, y));
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

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);

        for (Missile m : missiles)
            m.draw(g);
    }
}
