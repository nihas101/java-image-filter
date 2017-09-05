package de.nihas101.pictureFilter.filters;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import de.nihas101.pictureFilter.filters.pixelFilters.PixelByPixelFilter;

public interface Filter {
    /**
     * Applies the {@link PixelByPixelFilter} to the {@link WritableImage}
     * @param writableImage The image which the filter is to be applied to
     */
    void applyFilter(Image image, WritableImage writableImage);
    String getFilterName();
}
