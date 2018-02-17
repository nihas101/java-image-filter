package de.nihas101.simpleImageFilter.filters.filters3x3;

import de.nihas101.simpleImageFilter.filters.Filter;
import de.nihas101.simpleImageFilter.filters.pixelFilters.GrayScaleFilter;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class BumpMap extends PixelIterator3x3 implements Filter {
    @Override
    public void applyFilter(Image image, WritableImage writableImage){
        new EmbossFilter().applyFilter(image, writableImage);
        new GrayScaleFilter().applyFilter(writableImage, writableImage);
    }

    @Override
    public String getFilterName() { return "Bump map"; }
}
