package lab5;

public class BoundingBox {
    Double xmin;
    Double xmax;
    Double ymin;
    Double ymax;

    /**
     * Powiększa BB tak, aby zawierał punkt (x,y)
     * @param x - współrzędna x
     * @param y - współrzędna y
     */
    void addPoint(double x, double y) {
        if (this.isEmpty()) {
            xmin = x;
            xmax = x;
            ymin = y;
            ymax = y;
        } else {
            if (x < xmin) xmin = x;
            if (x > xmax) xmax = x;
            if (y < ymin) ymin = y;
            if (y > ymax) ymax = y;
        }
    }

    /**
     * Sprawdza, czy BB zawiera punkt (x,y)
     * @param x
     * @param y
     * @return
     */
    boolean contains(double x, double y) {
        return !this.isEmpty() && x > xmin && x < xmax && y > ymin && y < ymax;
    }

    /**
     * Sprawdza czy dany BB zawiera bb
     * @param bb
     * @return
     */
    boolean contains(BoundingBox bb) {
        return xmin < bb.xmin && xmax > bb.xmax && ymin < bb.ymin && ymax > bb.ymax;
    }

    /**
     * Sprawdza, czy dany BB przecina się z bb
     * @param bb
     * @return
     */
    boolean intersects(BoundingBox bb) {
        if (!isEmpty() && !bb.isEmpty()) {
            return this.contains(bb.xmin, bb.ymin) || this.contains(bb.xmin, bb.ymax) ||
                    this.contains(bb.xmax, bb.ymin) || this.contains(bb.xmax, bb.ymax) ||
                    bb.contains(xmin, ymin) || bb.contains(xmin, ymax) ||
                    bb.contains(xmax, ymin) || bb.contains(xmax, ymax);
        }
        return false;
    }

    /**
     * Powiększa rozmiary tak, aby zawierał bb oraz poprzednią wersję this
     * @param bb
     * @return
     */
    BoundingBox add(BoundingBox bb) {
        BoundingBox newBox = new BoundingBox();
        newBox.xmin = Math.min(xmin, bb.xmin);
        newBox.xmax = Math.max(xmax, bb. xmax);
        newBox.ymin = Math.min(ymin, bb.ymin);
        newBox.ymax = Math.max(ymax, bb.ymax);
        return newBox;
    }

    /**
     * Sprawdza czy BB jest pusty
     * @return
     */
    boolean isEmpty() {
        return xmin == null || xmax == null || ymin == null || ymax == null;
    }

    /**
     * Oblicza i zwraca współrzędną x środka
     * @return if !isEmpty() współrzędna x środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterX() {
        if(this.isEmpty()) {
            throw new RuntimeException("Not implemented");
        }
        return (xmax+xmin)/2;
    }

    /**
     * Oblicza i zwraca współrzędną y środka
     * @return if !isEmpty() współrzędna y środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterY() {
        if(this.isEmpty()) {
            throw new RuntimeException("Not implemented");
        }
        return (ymax+ymin)/2;
    }

    /**
     * Oblicza odległość pomiędzy środkami this bounding box oraz bbx
     * @param bbx prostokąt, do którego liczona jest odległość
     * @return if !isEmpty odległość, else wyrzuca wyjątek lub zwraca maksymalną możliwą wartość double
     * Ze względu na to, że są to współrzędne geograficzne, zamiast odległosci euklidesowej możesz użyć wzoru haversine
     * (ang. haversine formula)
     */
    double distanceTo(BoundingBox bbx) {
        if(!this.isEmpty() && !bbx.isEmpty()) {
            return CoordinatesCalculator.distanceBetween(this.getCenterX(), this.getCenterY(), bbx.getCenterX(), bbx.getCenterY());
        }
        return 4007500;
    }

}