import java.util.HashSet;

public class WickTester {
    public int wickCount;
    public double maxAngle;
    public double minAngle;
    public int ppi;
    public HashSet<Pair<Double,Double>> points;

    public static void main (String[] args) {
        WickTester w = new WickTester(3,1000);
        System.out.println(w.findMin());
    }

    public WickTester (int wickCount, int ppi) {
        this.wickCount = wickCount;
        this.maxAngle = Math.PI / (double)this.wickCount;
        this.minAngle = -this.maxAngle;
        this.points = new HashSet<>();
        this.ppi = ppi;
        for (int x = 0; x <= this.ppi; x++) {
            for (int y = 0; y <= this.ppi; y++) {
                Pair<Double,Double> p = new Pair<>(((double)x)/this.ppi, ((double)y)/this.ppi);
                if (this.pointInSector(p)) {
                    this.points.add(p);
                }
            }
        }
    }

    public boolean pointInSector (Pair<Double,Double> point) {
        if (1 < point.x * point.x + point.y * point.y)
            return false;

        double angle = Math.atan2(point.y,point.x);
        return this.minAngle < angle && angle <= this.maxAngle;
    }

    public Pair<Double,Double> generatePointInSector () {
        Pair<Double,Double> output = new Pair<>(Math.random()*2-1,Math.random()*2-1);
        return this.pointInSector(output) ? output : this.generatePointInSector();
    }

    public double distAverage (double x_position) {
        double sum = 0;
        for (Pair<Double,Double> p : this.points) {
            sum += Math.sqrt(p.x*p.x-2*p.x*x_position+x_position*x_position + p.y*p.y);
        }
        return sum/this.ppi/this.ppi;
    }

    public double findMin () {
        return this.findMin(0,1);
    }

    public double findMin (double left, double right) {
        if (right - left < 1d/10000000000000000d)
            return this.distAverage((left+right)/2);
        double[] vals = new double[11];
        for (int i = 0; i < vals.length; i++) {
            vals[i] = i/((double)vals.length-1) * (right - left) + left;
        }
        double[] outs = new double[11];
        for (int i = 0; i < vals.length; i++) {
            outs[i] = this.distAverage(vals[i]);
        }
        int new_min_index = 0;
        for (int i = 0; i < vals.length; i++) {
            if (outs[new_min_index] > outs[i]) {
                new_min_index = i;
            }
        }
        System.out.println(outs[new_min_index]);

        if (new_min_index == 0)
            return this.findMin(vals[0], vals[1]);

        if (new_min_index == vals.length-1)
            return this.findMin(vals[vals.length-2], vals[vals.length-1]);

        return this.findMin(vals[new_min_index-1], vals[new_min_index+1]);
    }
}