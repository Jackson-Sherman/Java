public class Cubic implements Function{
    @Override
    public double valueAt (Point p) {
        return p.x*(p.x*p.x - 3) + p.y*(p.y*p.y - 3);
    }

    @Override
    public BoundingBox bounds() {
        return new BoundingBox(-2,-2,2,2);
    }

    @Override
    public String name() {
        return "Cubic";
    }
}
