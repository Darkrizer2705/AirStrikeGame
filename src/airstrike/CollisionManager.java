package airstrike;

public class CollisionManager {

    public boolean checkMissileHit(Player player, EnemyManager enemyManager, ScoreManager score) {

        for (int i = 0; i < player.missiles.size(); i++) {
            for (int j = 0; j < enemyManager.getEnemies().size(); j++) {

                if (player.missiles.get(i).getBounds()
                        .intersects(enemyManager.getEnemies().get(j).getBounds())) {

                    player.missiles.remove(i);
                    enemyManager.getEnemies().remove(j);
                    score.increaseScore();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkPlayerHit(Player player, EnemyManager enemyManager) {

        for (Enemy e : enemyManager.getEnemies()) {
            if (player.getBounds().intersects(e.getBounds()))
                return true;
        }
        return false;
    }
}
