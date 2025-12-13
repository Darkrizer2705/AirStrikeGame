package airstrike;

import java.awt.*;

public class ScoreManager {

    int score = 0;

    public void increaseScore() {
        score += 10;
    }

    public void decreaseScore(int amount) {
        score -= amount;
        if (score < 0) score = 0;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Score : " + score, 20, 20);
    }

    public void reset() {
        score = 0;
    }

    public int getScore() { return score; }

}
