package de.nihas101.pictureFilter.filters.filtersNxN;

import de.nihas101.pictureFilter.filters.Filter;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import static java.lang.Math.*;

public class BlurFilter5X5 extends PixelIterator5x5 implements Filter {
    @Override
    public void applyFilter(Image image, WritableImage writableImage) {
        if(writableImage == null || image == null) return;

        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        double imageHeight = writableImage.getHeight();
        double imageWidth = writableImage.getWidth();

        // source: http://lodev.org/cgtutor/filtering.html
        rowWise(imageHeight, imageWidth,
                (x, y) ->{
                    /* Get colors of neighbours for blur calculations */
                    Color color1 = pixelReader.getColor(x[2],y[2]);
                    Color color2 = pixelReader.getColor(x[7],y[7]);
                    Color color3 = pixelReader.getColor(x[10],y[10]);
                    Color color4 = pixelReader.getColor(x[11],y[11]);
                    Color color5 = pixelReader.getColor(x[12],y[12]);
                    Color color6 = pixelReader.getColor(x[13],y[13]);
                    Color color7 = pixelReader.getColor(x[14],y[14]);
                    Color color8 = pixelReader.getColor(x[17],y[17]);
                    Color color9 = pixelReader.getColor(x[22],y[22]);

                   /* Set new color */
                    Color color = averageOfColors(color1, color2, color3, color4,
                            color5, color6, color7, color8, color9);
                    pixelWriter.setColor(x[13],y[13],color);
                });
    }

    @Override
    public String getFilterName() { return "blur5x5"; }

    private Color averageOfColors(Color... colors){
        double red = 0;
        double blue = 0;
        double green = 0;

        for (Color color : colors) {
            red   += color.getRed();
            blue  += color.getBlue();
            green += color.getGreen();
        }

        int middle = (int) ceil(colors.length/((double)2));
        red = min(max(red/9,0),1);
        green = min(max(green/9,0),1);
        blue = min(max(blue/9,0),1);

        return new Color(red, green, blue, colors[middle].getOpacity());
    }
}
