package lab6;

import java.awt.*;

public class Bubble implements XmasShape {
    private int x;
    private int y;
    //Color lineColor;
    //Color fillColor;
    private Color bubbleColor;

    Bubble(int x, int y, Color bubbleColor) {
        this.x = x;
        this.y = y;
        this.bubbleColor = bubbleColor;
    }

    @Override
    public void render(Graphics2D g2d) {
        // ustaw kolor wypełnienia
        g2d.setColor(bubbleColor);
        g2d.fillOval(x, y, 15, 15);
        // ustaw kolor obramowania
        //g2d.setColor(bubbleColor);
        g2d.drawOval(x, y, 15, 15);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
    }
}
