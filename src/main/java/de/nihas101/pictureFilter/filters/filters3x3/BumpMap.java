package de.nihas101.pictureFilter.filters.filters3x3;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import de.nihas101.pictureFilter.filters.Filter;
import de.nihas101.pictureFilter.filters.pixelFilters.Grayscale_Filter;

public class BumpMap extends PixelIterator3x3 implements Filter {
    @Override
    public void applyFilter(Image image, WritableImage writableImage){
        new EmbossFilter().applyFilter(image, writableImage);
        new Grayscale_Filter().applyFilter(writableImage, writableImage);
    }

    @Override
    public String getFilterName() { return "bumpMap"; }
}
