package airstrike;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EnemyManager {

    ArrayList<Enemy> enemies = new ArrayList<>();
    Random rand = new Random();
    // Higher value -> lower spawn frequency (was 40)
    private int spawnChance = 80;
    // Missile drops spawn less frequently and grant missiles when collected
    private int spawnDropChance = 400;

    ArrayList<MissileDrop> drops = new ArrayList<>();
    ArrayList<KillEffect> effects = new ArrayList<>();
    ArrayList<FloatingText> floatingTexts = new ArrayList<>();

    public void updateEnemies(ScoreManager scoreManager) {
        if (rand.nextInt(spawnChance) == 0) {
            int maxX = Math.max(1, GamePanel.WIDTH - Enemy.WIDTH);
            // try a few times to find a spawn x that isn't too close to others
            boolean spawned = false;
            for (int attempt = 0; attempt < 10 && !spawned; attempt++) {
                int sx = rand.nextInt(maxX);
                if (canSpawnAt(sx, Enemy.WIDTH, Enemy.HEIGHT)) {
                    enemies.add(new Enemy(sx));
                    spawned = true;
                }
            }
        }

        if (rand.nextInt(spawnDropChance) == 0) {
            int maxX = Math.max(1, GamePanel.WIDTH - MissileDrop.WIDTH);
            boolean spawned = false;
            for (int attempt = 0; attempt < 10 && !spawned; attempt++) {
                int sx = rand.nextInt(maxX);
                if (canSpawnAt(sx, MissileDrop.WIDTH, MissileDrop.HEIGHT)) {
                    drops.add(new MissileDrop(sx));
                    spawned = true;
                }
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).move();
            if (enemies.get(i).y > GamePanel.HEIGHT) {
                // enemy passed bottom -> penalize player
                scoreManager.decreaseScore(10);
                // show -10 where the enemy left the screen
                Enemy e = enemies.get(i);
                int cx = e.x + e.width / 2;
                int cy = Math.min(GamePanel.HEIGHT, e.y + e.height / 2);
                addFloatingText("-10", cx, cy, new Color(255, 80, 80));
                AudioManager.play("letGo.wav");
                enemies.remove(i);
                i--;
            }
        }

        for (int i = 0; i < drops.size(); i++) {
            drops.get(i).move();
            if (drops.get(i).y > GamePanel.HEIGHT) {
                drops.remove(i);
                i--;
            }
        }

        // update kill effects
        for (int i = 0; i < effects.size(); i++) {
            effects.get(i).update();
            if (!effects.get(i).isAlive()) {
                effects.remove(i);
                i--;
            }
        }

        // update floating texts
        for (int i = 0; i < floatingTexts.size(); i++) {
            floatingTexts.get(i).update();
            if (!floatingTexts.get(i).isAlive()) {
                floatingTexts.remove(i);
                i--;
            }
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<MissileDrop> getDrops() {
        return drops;
    }

    // Check whether a spawn at x (top-left) with width/height would be at least
    // 10 pixels away from any existing enemy or drop. Returns true if spawn is OK.
    private boolean canSpawnAt(int x, int w, int h) {
        int padding = 10;
        Rectangle candidate = new Rectangle(x - padding, -padding, w + padding * 2, h + padding * 2);

        for (Enemy e : enemies) {
            if (candidate.intersects(e.getBounds())) return false;
        }

        for (MissileDrop d : drops) {
            if (candidate.intersects(d.getBounds())) return false;
        }

        return true;
    }

    public void draw(Graphics g) {
        for (Enemy e : enemies)
            e.draw(g);

        for (MissileDrop d : drops)
            d.draw(g);

        for (KillEffect k : effects)
            k.draw(g);

        for (FloatingText ft : floatingTexts)
            ft.draw(g);
    }

    public void reset() {
        enemies.clear();
        drops.clear();
        effects.clear();
    }

    public void addEffect(int centerX, int centerY) {
        effects.add(new KillEffect(centerX, centerY));
    }

    public void addFloatingText(String text, int centerX, int centerY, Color color) {
        floatingTexts.add(new FloatingText(text, centerX, centerY, color));
    }

}
