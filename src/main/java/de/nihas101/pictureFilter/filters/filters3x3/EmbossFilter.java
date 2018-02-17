package de.nihas101.pictureFilter.filters.filters3x3;

import de.nihas101.pictureFilter.filters.Filter;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class EmbossFilter extends PixelIterator3x3 implements Filter {
    @Override
    public void applyFilter(Image image, WritableImage writableImage) {
        setup(image, writableImage);

        // source: http://lodev.org/cgtutor/filtering.html
        rowWise(imageHeight, imageWidth,
                (int x1, int y1, int x2, int y2, int x3, int y3,
                 int x4, int y4, int x,  int y,  int x5, int y5,
                 int x6, int y6, int x7, int y7, int x8, int y8) ->{
                    /* Get colors of neighbours for sharpen calculations */
                    Color color1 = pixelReader.getColor(x1,y1);
                    Color color2 = pixelReader.getColor(x2,y2);
                    Color color3 = pixelReader.getColor(x4,y4);
                    Color color4 = pixelReader.getColor(x5,y5);
                    Color color5 = pixelReader.getColor(x7,y7);
                    Color color6 = pixelReader.getColor(x8,y8);

                   /* Set new color */
                    Color color = emboss(color1, color2, color3, color4, color5, color6);
                    pixelWriter.setColor(x,y,color);
                });
    }

    private Color emboss(Color color1, Color color2, Color color3, Color color4, Color color5, Color color6) {

        double bias = .5;

        double red = - color1.getRed() - color2.getRed() - color3.getRed()
                     + color4.getRed() + color5.getRed() + color6.getRed() + bias;
        double green = - color1.getGreen() - color2.getGreen() - color3.getGreen()
                        + color4.getGreen() + color5.getGreen() + color6.getGreen() + bias;
        double blue  = - color1.getBlue() - color2.getBlue() - color3.getBlue()
                        + color4.getBlue() + color5.getBlue() + color6.getBlue() + bias;

        red  = min(max(red, 0),1);
        green = min(max(green, 0),1);
        blue = min(max(blue, 0),1);

        return Color.color(red,green,blue);
    }

    @Override
    public String getFilterName() {
        return "Emboss";
    }
}
