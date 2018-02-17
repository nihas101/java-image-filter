package de.nihas101.pictureFilter.filters.filtersNxN;

import de.nihas101.pictureFilter.filters.Filter;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class SNESMosaicFilter extends PixelIterator5x5 implements Filter {
    @Override
    public void applyFilter(Image image, WritableImage writableImage) {
        setup(image, writableImage);

        squareRowWise(imageHeight, imageWidth,
                (x, y) ->{
                    /* Get top-left color */
                    Color color = pixelReader.getColor(x[0],y[0]);

                    /* Apply color to whole square */
                    for(int i=1 ; i < x.length ; i++) {
                        pixelWriter.setColor(x[i], y[i], color);
                    }
                });
    }

    @Override
    public String getFilterName() {
        return "SNES Mosaic";
    }
}
