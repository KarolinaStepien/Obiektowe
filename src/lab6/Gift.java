package lab6;

import java.awt.*;

public class Gift implements XmasShape {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color giftColor;

    Gift(int x, int y, int width, int height, Color giftColor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.giftColor = giftColor;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(giftColor);
        g2d.fillRect(x, y, width, height);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
    }
}
