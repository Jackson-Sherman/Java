import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ColorPlotter {
    public static void main (String[] args) {

        Pair<Integer, Integer> dim = new Pair<>(100,100);
        BufferedImage img = new BufferedImage(dim.left, dim.right, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < dim.left; y++) {
            for (int x = 0; x < dim.right; x++) {
                int col = (int)(Math.random()*0x1_00_00_00);
                img.setRGB(x, y, col);
            }
        }
        File f = null;
        try {
            f = new File("/Users/jacksonsherman/Desktop/Code/git/Personal/Java/Misc/out/results/ColorPlotter/mandy.png");
            ImageIO.write(img,"png",f);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
