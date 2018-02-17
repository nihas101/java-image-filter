package de.nihas101.pictureFilter.filters.mirrorFilters;

import de.nihas101.pictureFilter.filters.Filter;
import de.nihas101.pictureFilter.filters.pixelFilters.PixelIterator;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class MirrorHorizontalTop extends PixelIterator implements Filter {
    @Override
    public void applyFilter(Image image, WritableImage writableImage) {
        setup(image, writableImage);

        columnOutwards(imageHeight, imageWidth, (x1,y1,x2,y2) ->{
            Color color = pixelReader.getColor(x1,y1);

            /* Set new color */
            pixelWriter.setColor(x1,y1,color);
            pixelWriter.setColor(x2,y2,color);
        });
    }

    @Override
    public String getFilterName() {
        return "mirrorHorizontalTop";
    }
}
