import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Mandelbrot implements Function {
    public final int maxItter;
    @Override
    public double valueAt (Point p) {
        double x0 = p.x, y0 = p.y;
        double x = 0, y = 0;
        double x2 = 0, y2 = 0;
        int itter = -1;
        while (x2 + y2 <= 0x1_00 && itter < this.maxItter-1) {
            y = (x + x) * y + y0;
            x = x2 - y2 + x0;
            x2 = x * x;
            y2 = y * y;
            itter++;
        }
        if (2 < x2 + y2) {
            // sqrt of inner term removed using log simplification rules.
//            double log_zn = Math.log(x*x + y*y) / 2;
//            double nu = Math.log(log_zn / Math.log(1<<8)) / Math.log(2);
            // Rearranging the potential function.
            // Dividing log_zn by log(2) instead of log(N = 1<<8)
            // because we want the entire palette to range from the
            // center to radius 2, NOT our bailout radius.
//            itter += 1 - nu;
            itter += 1 - Math.pow(2, 2-Math.sqrt(x2+y2));
        }
        return itter;
    }
    public Mandelbrot (int maxItter) {this.maxItter = maxItter;}
    @Override
    public BoundingBox bounds() {
        return new BoundingBox(-2,-2,3,3);
    }

    @Override
    public String name() {
        return "Mandelbrot";
    }
}
