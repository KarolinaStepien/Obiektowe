package lab6;

import java.awt.*;

public class Branch implements XmasShape {
    private int x;
    private int y;
    private int a; //roznica wielkosci i polozenia
    private Color branchColor;

    Branch(int x, int y, int a, Color branchColor) {
        this.x = x;
        this.y = y;
        this.a = a;
        this.branchColor = branchColor;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(branchColor);
        int tabX[] = {0, 200 - a, -200 + a};
        int tabY[] = {a, 250, 250};
        g2d.fillPolygon(tabX, tabY, tabX.length);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
    }
}
