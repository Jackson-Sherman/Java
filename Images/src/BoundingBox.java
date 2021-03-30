public class BoundingBox {
    public double x0;
    public double x1;
    public double y0;
    public double y1;

    public BoundingBox (Point point1, Point point2) { this(point1.x, point1.y, point2.x, point2.y); }

    public BoundingBox (double x0, double y0, double x1, double y1) {
        this.x0 = Math.min(x0, x1);
        this.y0 = Math.min(y0, y1);
        this.x1 = Math.max(x0, x1);
        this.y1 = Math.max(y0, y1);
    }

    public BoundingBox (int[] values) { this(values[0],values[1],values[2], values[3]); }

    public double[] getBounds () {
        return new double[]{this.x0, this.y0, this.x1, this.y1};
    }

    public boolean inBounds (Point p) {
        return this.x0<=p.x && p.x<this.x1 && this.y0<=p.y && p.y<this.y1;
    }
}
