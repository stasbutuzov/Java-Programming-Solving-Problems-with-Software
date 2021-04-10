import edu.duke.*;
import java.io.*;

/**
 * Programming Exercise (PDF-version):
 * https://d396qusza40orc.cloudfront.net/phoenixassets/duke-java-programming/ProgrammingExercise-BatchGrayscale.pdf
 *
 * @author stasbutuzov
 * @version Apr 3, 2021
 */

public class BatchInversion {
    public ImageResource makeInversion(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel p : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(p.getX(), p.getY());

            int red = (255 - inPixel.getRed());
            int green = (255 - inPixel.getGreen());
            int blue = (255 - inPixel.getBlue());

            p.setRed(red);
            p.setGreen(green);
            p.setBlue(blue);
        }
        return outImage;
    }

    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeInversion(inImage);

            String fileName = inImage.getFileName();
            String newName = "inverted-" + fileName;

            gray.setFileName(newName);
            gray.draw();
            gray.save();
        }
    }

    public static void main(String args[]) {
        BatchInversion run = new BatchInversion();
        run.selectAndConvert();
    }
}