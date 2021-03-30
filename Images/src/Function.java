public interface Function {
    double valueAt(Point p);
    BoundingBox bounds ();
    String name ();
}
