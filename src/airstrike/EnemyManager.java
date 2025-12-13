package airstrike;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EnemyManager {

    ArrayList<Enemy> enemies = new ArrayList<>();
    Random rand = new Random();

    public void updateEnemies() {
        if (rand.nextInt(40) == 0) {
            enemies.add(new Enemy(rand.nextInt(460)));
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).move();
            if (enemies.get(i).y > 600) {
                enemies.remove(i);
                i--;
            }
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void draw(Graphics g) {
        for (Enemy e : enemies)
            e.draw(g);
    }
}
