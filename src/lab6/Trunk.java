package lab6;

import java.awt.*;

public class Trunk implements XmasShape {
    private int x;
    private int y;
    private Color trunkColor;

    Trunk(int x, int y, Color trunkColor) {
        this.x = x;
        this.y = y;
        this.trunkColor = trunkColor;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(trunkColor);
        g2d.fillRect(x, y, 60, 25);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
    }
}
