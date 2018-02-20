package de.nihas101.simpleImageFilter.filters.filters3x3;

import de.nihas101.simpleImageFilter.filters.Filter;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import static java.lang.Math.*;

public class MotionBlurFilterDiagonal3x3 extends PixelIterator3x3 implements Filter {

    @Override
    public void applyFilter(Image image, WritableImage writableImage) {
        setup(image, writableImage);

        // source: http://lodev.org/cgtutor/filtering.html
        rowWise(imageHeight, imageWidth,
                (int x1, int y1, int x2, int y2, int x3, int y3,
                 int x4, int y4, int x, int y, int x5, int y5,
                 int x6, int y6, int x7, int y7, int x8, int y8) -> {
                    /* Get colors of neighbours for blur calculations */
                    Color color1 = pixelReader.getColor(x1, y1);
                    Color color2 = pixelReader.getColor(x, y);
                    Color color3 = pixelReader.getColor(x8, y8);

                    /* Set new color */
                    Color color = averageOfColors(color1, color2, color3);
                    pixelWriter.setColor(x, y, color);
                });
    }

    private Color averageOfColors(Color... colors) {
        double red = 0;
        double blue = 0;
        double green = 0;

        for (Color color : colors) {
            red += color.getRed();
            blue += color.getBlue();
            green += color.getGreen();
        }

        int middle = (int) ceil(colors.length / ((double) 2));
        red = min(max(red / 3, 0), 1);
        green = min(max(green / 3, 0), 1);
        blue = min(max(blue / 3, 0), 1);

        return new Color(red, green, blue, colors[middle].getOpacity());
    }

    @Override
    public String getFilterName() {
        return "Motionblur Diagonal 3x3";
    }
}
