package lab6;

import java.awt.*;

public class SnowFlake implements XmasShape {
    private int x;
    private int y;
    private double scale;
    private Color snowColor;

    SnowFlake(int x, int y, double scale, Color snowColor) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.snowColor = snowColor;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.translate(100, 100);
        g2d.scale(.2, .2);
        g2d.setColor(snowColor);
        for (int i = 0; i < 12; i++) {
            g2d.drawLine(0, 0, 100, 100);
            g2d.rotate(2 * Math.PI / 12);
        }
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
        g2d.scale(scale, scale);
    }
}
