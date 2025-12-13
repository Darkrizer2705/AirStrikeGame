package airstrike;

public class CollisionManager {

    public boolean checkMissileHit(Player player, EnemyManager enemyManager, ScoreManager score) {

        for (int i = 0; i < player.missiles.size(); i++) {
            for (int j = 0; j < enemyManager.getEnemies().size(); j++) {

                if (player.missiles.get(i).getBounds()
                        .intersects(enemyManager.getEnemies().get(j).getBounds())) {
                    java.awt.Rectangle eb = enemyManager.getEnemies().get(j).getBounds();
                    int cx = eb.x + eb.width / 2;
                    int cy = eb.y + eb.height / 2;

                    player.missiles.remove(i);
                    enemyManager.getEnemies().remove(j);

                    enemyManager.addEffect(cx, cy);
                    score.increaseScore();
                    enemyManager.addFloatingText("+10", cx, cy, new java.awt.Color(120, 255, 140));
                    return true;
                }
            }
        }

        for (int i = 0; i < player.missiles.size(); i++) {
            for (int j = 0; j < enemyManager.getDrops().size(); j++) {
                if (player.missiles.get(i).getBounds()
                        .intersects(enemyManager.getDrops().get(j).getBounds())) {

                    java.awt.Rectangle db = enemyManager.getDrops().get(j).getBounds();
                    int cx = db.x + db.width / 2;
                    int cy = db.y + db.height / 2;

                    player.missiles.remove(i);
                    enemyManager.getDrops().remove(j);

                    enemyManager.addEffect(cx, cy);
                    return true;
                }
            }
        }
        return false;
    }

    public void checkPlayerPickup(Player player, EnemyManager enemyManager) {
        for (int i = 0; i < enemyManager.getDrops().size(); i++) {
            if (player.getBounds().intersects(enemyManager.getDrops().get(i).getBounds())) {
                enemyManager.getDrops().remove(i);
                i--;
                player.addMissiles(10);
            }
        }
    }

    public boolean checkPlayerHit(Player player, EnemyManager enemyManager) {

        for (Enemy e : enemyManager.getEnemies()) {
            if (player.getBounds().intersects(e.getBounds()))
                return true;
        }
        return false;
    }
}
