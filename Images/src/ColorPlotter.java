import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ColorPlotter {
    private final double x_coefficient;
    private final double x_constant;
    private final double y_coefficient;
    private final double y_constant;
    public static void main (String[] args) {
        Function fun = new Mandelbrot(0x100);
        int width = 700, height = 400;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        double[] vals = new double[width * height];
        ColorPlotter este = new ColorPlotter(-2.5,1,-1,1, width,height);
        for (int y = 0; y < height; y += height-1) {
            for (int x = 0; x < width; x += width-1) {
                Point p = este.map(x,y);
                System.out.printf("(%03d, %03d)->(%+1.3f, %+1.3f)\n",x ,y ,p.x, p.y);
            }
        }
        System.out.println();
        double max = Double.MIN_VALUE, min = Double.MAX_VALUE;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double v = fun.valueAt(este.map(x, y));
                vals[y * width + x] = v;
                max = Math.max(max, v);
                min = Math.min(min, v);
            }
        }
//        double mul = 1d / (max - min);
//        double add = min / (min - max);
        int[] histogram = new int[(int)max+1];
        for (double itcount : vals) {
            histogram[(int)itcount]++;
        }
        histogram[0] = 0;
        for (int i = 1; i < histogram.length; i++) {
            histogram[i] = histogram[i-1] + histogram[i];
        }
        double[] cumulativeovertotal = new double[histogram.length];
        for (int i = 0; i < cumulativeovertotal.length; i++) {
            cumulativeovertotal[i] = ((double)histogram[i]) / ((double)histogram[histogram.length-1]);
        }
        int[] cols = new int[width * height];
        for (int i = 0; i < width*height; i++) {
//            cols[i] = ColorPlotter.blueYellow(vals[i]*mul+add);
            double i0 = cumulativeovertotal[(int)vals[i]];
            double i1;
            try {
                i1 = cumulativeovertotal[1+(int)vals[i]];
            } catch (Exception e) {
                i1 = i0;
            }
            double remain = vals[i]%1;
            if (i == width * 199 + 100) {
                System.out.println(0);
            }
            cols[i] = ColorPlotter.blueYellow(Math.floor(i0)*(1-remain)+Math.floor(i1)*remain);
        }
        img.setRGB(0,0,width, height, cols, 0, width);
        File f = null;
        try {
            f = new File(fun.name()+".png");
            ImageIO.write(img,"png",f);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public ColorPlotter (double x0, double x1, double y0, double y1, double w, double h) {
        this.x_coefficient = (x1 - x0) / (w-1);
        this.y_coefficient = (y0 - y1) / (h-1);
        this.x_constant    = x0;
        this.y_constant    = y1;
    }

    public Point map (int x, int y) {
        return new Point(x*this.x_coefficient+this.x_constant,y*this.y_coefficient+this.y_constant);
    }

    public static int hueR2B (double input) {
        int v = (int) Math.floor(input * 0x3_fc);
        int r = 0;
        int g = 0;
        int b = 0;
        if (v < 0xff) {
            g = v;
            b = 0xff;
        } else if (v < 0x1_fe) {
            g = 0xff;
            b = 0x1_fe - v;
        } else if (v < 0x2_fd) {
            r = v - 0x1_fe;
            g = 0xff;
        } else if (v <= 0x3_fc) {
            r = 0xff;
            g = 0x3_fc - v;
        }
        return r << 0x10 | g << 0x08 | b;
    }

    public static int black2yellow (double input) {
        int v = (int) Math.floor(input * 0x1_ff);
//        if (v < 0x7f) {
//            return v << 0x11;
//        }
//        else if (v < 0x17f) {
//            return 0xff0000 | (v - 0x7f) << 8;
//        }
//        else if (v < 0x1_ff){
//            return
//        }
        return Math.max(0,Math.min(0xff_ff,(Math.min(v, 0xff) << 8 | Math.max(0, v - 0x1_00)))) << 8;
    }

    public static int blueYellow (double input) {
        int v = (int) Math.floor(input * 0x1_fe);
        v %= 0x1_fe;
        if (v < 0x1_00) {
            int vRG = 2*v - 0xff;
            vRG = Math.max(vRG, 0);
            return vRG << 16 | vRG << 8 | v;
        }
        else {
            int vR = 0xff - (v-0xff)*1;
            int vG = 0xff - (v-0xff)*2;
            int vB = 0xff - (v-0xff)*4;
            return vR << 16 | Math.max(vG,0xff-v/2) << 8 | Math.max(vB,0);
        }
    }

    public static int greyscale (double input) {
        int v = (int)Math.floor(input * 0xff);
        return v << 0x10 | v << 0x08 | v;
    }
}
