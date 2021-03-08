import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JFrame;

import java.util.ArrayList;

public class DiscoverCard extends JPanel{
    // 2.1265 in  34.5 circles 12 + 5 + 17.5
    // 54.01 mm
    //by
    // 3.369 in
    // 85.58 mm
    //by
    // 0.0315 in
    // 0.81 mm

    // 227 px per inch
    public static final double  width_in = 3.375;
    public static final double height_in = 2.125;
    public static final double   round_r = 0.125;

    public static final double       ppi = 227;
    public static final double  rowcount = 34.5;
    public static final int        width = (int) Math.round(width_in  * ppi);
    public static final int       height = (int) Math.round(height_in * ppi);
    public static final int       margin = (int) Math.round(round_r   * ppi);
    public static final int   row_height = (int) Math.round(height_in * ppi / rowcount);
    public static final int    col_width = (int) Math.round(0.03790625 * ppi);


    public static BufferedImage image = new BufferedImage(2 * margin + width, 3 * margin + width, BufferedImage.TYPE_INT_ARGB);

    public static void circleDrawer (double x, double y) {
        x += margin;
        y += 2 * margin;
        double s = x / rowcount;
        int color = 0x000000;
        int int_rad = (int) (s / 2);
        Circle c = new Circle(x, y, s / 2);
        ArrayList<Integer> a = new ArrayList<>((int)(s*s));
        int x_cnt, y_cnt = 0;
        for (int i = (int)Math.floor(y); i < s+y; i++) {
            x_cnt = 0;
            y_cnt++;
            for (int j = (int)Math.floor(x); j < s+x; j++) {
                a.add(color + (c.contains(i,j) ? 0x00000000 : 0xFF000000));
                x_cnt++;
            }
        }
    }

    public static void main (String[] args) {
        JFrame frame = new JFrame("Discover Card");
        frame.setSize(2*margin + width, 3*margin + height);
        DiscoverCard card = new DiscoverCard();
        frame.add(card);
        frame.setVisible(true);
    }

    public static class Circle {
        public final double x, y, r, x2, y2, r2;

        public Circle (double x, double y, double r) {
            this.x  = x;
            this.x2 = x * x;
            this.y  = y;
            this.y2 = y * y;
            this.r  = r;
            this.r2 = r * r;
        }

        public boolean contains (int x, int y) {
            return this.r2<(this.x-x)*(this.x-x)+(this.y-y)*(this.y-y);
            //     this.r2+2*(x*this.x+y*this.y)<this.x2+this.y2
        }
    }
}
