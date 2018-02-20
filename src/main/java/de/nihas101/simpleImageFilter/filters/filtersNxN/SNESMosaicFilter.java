package de.nihas101.simpleImageFilter.filters.filtersNxN;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class SNESMosaicFilter extends MosaicFilter {
    @Override
    public void applyFilter(Image image, WritableImage writableImage) {
        setup(image, writableImage);

        squareRowWise(imageHeight, imageWidth,
                (x, y) -> {
                    /* Get top-left color */
                    Color color = pixelReader.getColor(x[0], y[0]);
                    applyToSquare(x, y, color);
                });
    }

    @Override
    public String getFilterName() {
        return "SNES Mosaic";
    }
}
