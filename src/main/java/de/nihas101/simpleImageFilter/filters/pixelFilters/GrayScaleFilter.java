package de.nihas101.simpleImageFilter.filters.pixelFilters;

import de.nihas101.simpleImageFilter.filters.Filter;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Applies a black and white filter to an image using following formula: 0.21 R + 0.72 G + 0.07 B
 */
public class GrayScaleFilter extends PixelIterator implements Filter {

    public void applyFilter(Image image, WritableImage writableImage) {
        setup(image, writableImage);

        rowWise(imageHeight, imageWidth, (x, y) -> {
            Color color = pixelReader.getColor(x, y);

            /* Apply formula for grayscale source: http://www.tannerhelland.com/3643/grayscale-image-algorithm-vb6/ */
            double grey = color.getRed() * .3 + color.getBlue() * .59 + color.getGreen() * .11;

            Color grayColor = new Color(grey, grey, grey, color.getOpacity());

            /* Set new color */
            pixelWriter.setColor(x, y, grayColor);
        });
    }

    @Override
    public String getFilterName() {
        return "Grayscale";
    }
}
