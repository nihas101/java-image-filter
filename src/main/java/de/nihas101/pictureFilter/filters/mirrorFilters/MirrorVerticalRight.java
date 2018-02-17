package de.nihas101.pictureFilter.filters.mirrorFilters;

import de.nihas101.pictureFilter.filters.Filter;
import de.nihas101.pictureFilter.filters.pixelFilters.PixelIterator;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class MirrorVerticalRight extends PixelIterator implements Filter {
    @Override
    public void applyFilter(Image image, WritableImage writableImage) {
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        double imageHeight = writableImage.getHeight();
        double imageWidth = writableImage.getWidth();

        rowOutwards(imageHeight, imageWidth, (x1,y1,x2,y2) ->{
            Color color = pixelReader.getColor(x2,y2);

            /* Set new color */
            pixelWriter.setColor(x1,y1,color);
            pixelWriter.setColor(x2,y2,color);
        });
    }

    @Override
    public String getFilterName() { return "mirrorVerticalRight"; }
}
