import java.math.BigInteger;

public class IntegrationWickModel {
    public int sectors;
    public double theta;
    public double sin_theta;
    public double cos_theta;
    public double tan_theta;
    public double total_area;

    public static void main (String[] args) {
        IntegrationWickModel w = new IntegrationWickModel(4);
        w.findMiddle(99);
    }

    public IntegrationWickModel (int sector_count) {
        this.sectors = sector_count;
        this.theta = Math.PI / this.sectors;
        this.sin_theta = Math.sin(this.theta);
        this.cos_theta = Math.cos(this.theta);
        this.tan_theta = Math.tan(this.theta);
        this.total_area = 2*this.theta;
    }

    public double findMiddle (int iterations) {
        if (this.sectors <= 1)
            return 0;
        return this.findMiddle(0,1, 1, iterations);
    }

    private double findMiddle (long left, long right, long iter_pows, int iteration_max) {
        iter_pows *= 2;

        long mid = (left + right);
        System.out.print((int)(Math.log(iter_pows)/Math.log(2)));
        System.out.print(": ");
        System.out.print(BigInteger.valueOf(mid));
        System.out.print("/");
        System.out.println(BigInteger.valueOf(iter_pows));

        if (iteration_max <= 0 || 1 / (double)iter_pows == 0)
            return (double)mid/(double)iter_pows;

        double a = this.area((double)mid/(double)iter_pows);

        if (this.theta < a*2)
            return this.findMiddle(left*2, mid, iter_pows, iteration_max);

        else if (a*2 < this.theta)
            return this.findMiddle(mid, right*2, iter_pows, iteration_max);

        else
            return mid;
    }

    public double area (double x) {
        assert x * x <= x;

        double sum;
        if (x <= this.cos_theta)
            sum = this.tan_theta * x * x;
        else {
            sum = this.theta - Math.PI/2;
            sum += x * Math.sqrt(1 - x * x);
            sum += Math.asin(x);
        }
        return sum;
    }
}
