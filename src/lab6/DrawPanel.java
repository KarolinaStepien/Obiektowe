package lab6;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawPanel extends JPanel {

    private List<XmasShape> shapes = new ArrayList<>();

    DrawPanel() {
        setBackground(new Color(153, 153, 255));

        //galazki
        int i;
        for (i = 0; i < 5; i++) {
            Branch r = new Branch(500, 380 - 80 * i, 25 * i, new Color(51, 130 + 15 * i, 102));
            shapes.add(r);
        }

        //pien
        Trunk t = new Trunk(235, 315, new Color(96, 31, 31));
        shapes.add(t);

        //gwiazdka
        Star s = new Star(440, 80, new Color(255, 204, 0));
        shapes.add(s);

        //banki
        int k;
        Random generator = new Random();
        for (k = 3; k < 6; k++) {
            for (i = 0; i < 3; i++) {
                int rand1 = generator.nextInt(20);
                int rand2 = generator.nextInt(5);
                Bubble b = new Bubble(5 * rand1 + 200, 10 * rand2 + k * 50, new Color(255, 193, 51));
                shapes.add(b);
            }
            for (i = 0; i < 3; i++) {
                int rand1 = generator.nextInt(20);
                int rand2 = generator.nextInt(5);
                Bubble b = new Bubble(5 * rand1 + 200, 10 * rand2 + k * 50, new Color(204, 102, 255));
                shapes.add(b);
            }
            for (i = 0; i < 3; i++) {
                int rand1 = generator.nextInt(20);
                int rand2 = generator.nextInt(5);
                Bubble b = new Bubble(5 * rand1 + 200, 10 * rand2 + k * 50, new Color(230, 0, 0));
                shapes.add(b);
            }
        }

        //prezenty
        Gift g = new Gift(130, 280, 110, 95, new Color(204, 0, 102));
        shapes.add(g);
        Gift g1 = new Gift(160, 290, 100, 75, new Color(255, 153, 204));
        shapes.add(g1);
        Gift g2 = new Gift(140, 300, 80, 55, new Color(153, 255, 153));
        shapes.add(g2);
        Gift g3 = new Gift(315, 277, 110, 100, new Color(255, 255, 153));
        shapes.add(g3);
        Gift g4 = new Gift(280, 300, 140, 55, new Color(0, 64, 128));
        shapes.add(g4);

        //snieg
        for (i = 0; i < 70; i++) {
            SnowFlake f = new SnowFlake(generator.nextInt(900), generator.nextInt(600), 0.2, new Color(255, 255, 255));
            shapes.add(f);
        }

        //setOpaque(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (XmasShape s : shapes) {
            s.draw((Graphics2D) g);
        }
        g.setColor(new Color(230, 0, 0));
        g.setFont(new Font("Faith And Glory One", Font.BOLD, 60));
        g.drawString("Merry Christmas!", 340, 54);

        /*//napis
        g.setFont(new Font("Helvetica", Font.BOLD, 18));
        g.drawString("Hello World", 20, 20);
        //linia
        g.drawLine(10, 10, 100, 100);
        g.setColor(Color.yellow);
        //elipsa
        g.fillOval(100, 101, 30, 30);
        g.setColor(Color.black);
        g.drawOval(100, 101, 30, 30);
        //wielobok
        int x[] = {286, 253, 223, 200, 148, 119, 69, 45, 0};
        int y[] = {0, 101, 89, 108, 79, 95, 66, 80, 56};
        g.fillPolygon(x, y, x.length);

        //rysuje 12 linii obracając je o 30 stopni
        Graphics2D g2d = (Graphics2D) g;
        // zachowaj macierz przekształcenia
        AffineTransform mat = g2d.getTransform();
        // przesuń początek układu
        g2d.translate(100, 100);
        // zastosuj skalowanie
        g2d.scale(.2, .2);
        // narysuj linie
        for (int i = 0; i < 12; i++) {
            g2d.drawLine(0, 0, 100, 100);
            g2d.rotate(2 * Math.PI / 12);
        }
        //oddtwórz poprzednie ustawienia transformacji układu współrzędnych
        g2d.setTransform(mat);

        //obraca tekst podczas rysowania
        g2d.translate(200, 200);
        // zastosuj skalowanie
        g2d.scale(.2, .2);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.PLAIN, 96);
        g2d.setFont(font);
        for (int i = 0; i < 12; i++) {
            g2d.drawString("Happy new year", 150, 0);
            g2d.rotate(2 * Math.PI / 12);
        }

        //ustawia atrybuty linii
        // zachowaj macierz przekształcenia
        AffineTransform mat1 = g2d.getTransform();
        // przesuń początek układu
        g2d.translate(200, 200);
        // zastosuj skalowanie
        g2d.scale(.2, .2);
        g2d.setStroke(new BasicStroke(50, CAP_ROUND, JOIN_MITER));
        for (int i = 0; i < 12; i++) {
            //g2d.drawString("Happy new year",150,0);
            g2d.drawLine(0, 0, 100, 100);
            g2d.rotate(2 * Math.PI / 12);
        }
        //odtwórz poprzednie ustawienia transformacji układu współrzędnych
        g2d.setTransform(mat1);

        //gradientowy wielobok
        AffineTransform mat2 = g2d.getTransform();
        GradientPaint grad = new GradientPaint(0, 0, new Color(0, 255, 0), 0, 100, new Color(0, 10, 0));
        g2d.setPaint(grad);
        g2d.translate(0, 50);
        g2d.scale(0.7, 0.5);
        int x2[] = {286, 286, 223, 200, 148, 119, 69, 45, 0};
        int y2[] = {0, 131, 89, 108, 79, 95, 66, 80, 56};
        g2d.fillPolygon(x2, y2, x.length);
        g2d.translate(670, 0);
        g2d.scale(-1, 1);
        g2d.fillPolygon(x2, y2, x2.length);
        g2d.setTransform(mat2);*/
    }
}
