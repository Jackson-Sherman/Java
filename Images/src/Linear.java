public class Linear implements Function{
    @Override
    public double valueAt (Point p) {
        return p.x;
    }

    @Override
    public BoundingBox bounds() {
        double nf = Double.NEGATIVE_INFINITY;
        double pf = Double.POSITIVE_INFINITY;
        return new BoundingBox(nf, nf, pf, pf);
    }

    @Override
    public String name() {
        return "Linear";
    }
}
