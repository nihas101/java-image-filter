package de.nihas101.simpleImageFilter.filters.pixelFilters;

import javafx.scene.image.WritableImage;

/**
 * A {@link PixelByPixelFilter} which can be applied to {@link WritableImage}
 */
public interface PixelByPixelFilter {
    void apply(int x, int y);
}
