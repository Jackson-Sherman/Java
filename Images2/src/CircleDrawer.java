import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Point;
import java.util.TreeMap;
import java.util.HashSet;

public class CircleDrawer {
    public static void main (String[] args) {
        try {
            BufferedImage img = CircleDrawer.createImage(200, 200);
            File outputFile = new File("output.png");
            ImageIO.write(img, "png", outputFile);
        } catch (IOException ignored) {

        }
    }

    private static BufferedImage createImage (int width, int height) {
        BufferedImage img = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
        TreeMap<Long,HashSet<Point>> map = new TreeMap<>();
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                long dist = (long) x * x + (long) y * y;
                HashSet<Point> set = map.getOrDefault(dist, new HashSet<>());
                set.add(new Point(x, y));
                map.put(dist, set);
            }
        }
        int size = map.size();
        int i = 0;
        int max = -1;
        for (HashSet<Point> set : map.values()) {
            max = Math.max(max, set.size());
        }
        System.out.println(max);
        for (HashSet<Point> set : map.values()) {
            int v = (set.size() * 0x1_00 / max) << 24;
            v |= (int) (Math.random() * (1 << 24));
            for (Point p : set) {
                img.setRGB(p.x, p.y, v);
            }
            i++;
        }
        return img;
    }

    private static int rgbAt(int x, int y, BufferedImage img) {
        long output = 0x1_00_00_00L;
        output *= x;
        output /= img.getWidth();
        output /= y + 1;
        output %= 0x1_00_00_00L;
        output += 0xff_00_00_00L;
        return (int)output;
    }
}
