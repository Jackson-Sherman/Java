public class Line {public double slope;
    public double xInt,yInt;

    public Line (Point p, double s) {
        this.slope = s;
        this.yInt = p.y - p.x * this.slope;
        this.xInt = p.x - p.y / this.slope;
    }

    public Line (Point p0, Point p1) {
        this(p0,(p0.y-p1.y) / (p0.x-p1.x));
    }

    public double fofx (double x) { return this.yInt * (1 - x / this.xInt); }
    public double fofy (double y) { return this.xInt * (1 - y / this.yInt); }
}
class Point {
    public double x;
    public double y;

    public Point (double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Point (int x, int y) {
        this.x = x;
        this.y = y;
    }
}
