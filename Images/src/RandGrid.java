import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RandGrid {
    private final int x;
    private final int y;
    private final int xpx;
    private final int ypx;
    private int[][] colors;
    private final int[] color_options;
    public BufferedImage img;

    public RandGrid (int x, int y, int xpx, int ypx, int[] color_options) {
        this.x = x;
        this.y = y;
        this.xpx = xpx;
        this.ypx = ypx;
        this.color_options = color_options;
        this.colors = new int[this.y][this.x];
        for (int i = 0; i < this.y; i++) {
            for (int j = 0; j < this.x; j++) {
                this.colors[i][j] = this.color_options[(int)Math.floor(Math.random()*this.color_options.length)];
            }
        }
        this.img = new BufferedImage(this.x * this.xpx, this.y * this.ypx, BufferedImage.TYPE_INT_RGB);
    }

    public RandGrid (int x, int y, int xpx, int ypx) {
        this(x, y, xpx, ypx, new int[] {0x750787, 0x004dff, 0x008026, 0xffed00, 0xff8c00, 0xe40303});
    }

    public RandGrid (int x, int y) {
        this(x, y, 1, 1);
    }

    public static void main (String[] args) {
        RandGrid r = new RandGrid(20, 20, 20, 20);
        r.drawLines(5);
        try {
            File f = new File("randgrid.png");
            ImageIO.write(r.img, "png", f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw () {
        this.img = new BufferedImage(this.x * this.xpx, this.y * this.ypx, BufferedImage.TYPE_INT_RGB);
        for (int iy = 0; iy < this.y; iy++) {
            for (int ix = 0; ix < this.x; ix++) {
                this.drawpx(ix, iy);
            }
        }
    }

    public void drawLines (int len) {
        this.img = new BufferedImage(this.x * this.xpx, this.y * this.ypx, BufferedImage.TYPE_INT_RGB);
        for (int iy = 0; iy < this.y * this.ypx; iy++) {
            for (int ix = 0; ix < this.x * this.xpx; ix++) {
                img.setRGB(ix, iy, 0);
            }
        }
        int i = 0;
        int l = 0;
        while (i < this.x * this.y) {
            int ix = (int)Math.floor(Math.random() * this.x);
            int iy = (int)Math.floor(Math.random() * this.y);
            boolean isHorizontal = Math.random() < 0.5;
            int dir = Math.random() < 0.5 ? 1 : -1;
            if (isHorizontal) {
                for (int j = this.x; j < len + this.x; j++) {
                    this.drawpx((ix + dir * j) % this.x, iy, this.color_options[l]);
                }
            }
            else {
                for (int j = this.y; j < len + this.y; j++) {
                    this.drawpx(ix, (iy + dir * j) % this.y, this.color_options[l]);
                }
            }
            while (i < this.x * this.y && this.rgbIsBlack(i % this.y, i / this.y)) {
                i ++;
            }
            l++;
            l %= this.color_options.length;
        }
    }

    public void drawpx (int x, int y, int color) {
        for (int i = y * this.ypx; i < (y+1) * this.ypx; i++) {
            for (int j = x * this.xpx; j < (x+1) * this.xpx; j++) {
                this.img.setRGB(j, i, color);
            }
        }
    }

    public void drawpx (int x, int y) {
        this.drawpx(x, y, this.colors[y][x]);
    }

    public boolean rgbIsBlack (int x, int y) {
        return 0 == this.img.getRGB(this.xpx * x, this.ypx * y);
    }
}
