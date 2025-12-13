package airstrike;

import java.awt.*;

public class FloatingText {

    private float x, y;
    private final String text;
    private final Color color;
    private int lifeTicks; // remaining ticks
    private final int totalTicks;

    public FloatingText(String text, int centerX, int centerY, Color color) {
        this.text = text;
        this.color = color;
        this.totalTicks = 40; // 40 * 20ms = 800ms
        this.lifeTicks = totalTicks;
        // start slightly above the center so text sits over the target
        this.x = centerX;
        this.y = centerY - 10;
    }

    public void update() {
        lifeTicks--;
        // float upward over time
        y -= 0.8f;
    }

    public boolean isAlive() { return lifeTicks > 0; }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        float alpha = Math.max(0f, (float) lifeTicks / (float) totalTicks);
        Composite old = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(color);
        FontMetrics fm = g.getFontMetrics();
        int tw = fm.stringWidth(text);
        int th = fm.getAscent();
        g2.drawString(text, Math.round(x - tw / 2f), Math.round(y + th / 2f));
        g2.setComposite(old);
    }
}
