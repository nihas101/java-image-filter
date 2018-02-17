package de.nihas101.pictureFilter.filters.pixelFilters;

import de.nihas101.pictureFilter.filters.Filter;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import static java.lang.Double.max;
import static java.lang.Math.min;

public class InvertFilter extends PixelIterator implements Filter {
    @Override
    public void applyFilter(Image image, WritableImage writableImage) {
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        double imageHeight = writableImage.getHeight();
        double imageWidth = writableImage.getWidth();

        rowWise(imageHeight, imageWidth, (x, y) -> {
            Color color = pixelReader.getColor(x,y);

            /* Invert colors */
            double red = 1 - color.getRed();
            double green = 1 - color.getGreen();
            double blue = 1 - color.getBlue();

            /* Make sure values are in range */
            red = min(max(0,red),1);
            green = min(max(0,green),1);
            blue = min(max(0,blue),1);

            Color grayColor = new Color(red, green, blue, color.getOpacity());

            /* Set new color */
            pixelWriter.setColor(x,y,grayColor);
        });
    }

    @Override
    public String getFilterName() {
        return "invert";
    }
}
