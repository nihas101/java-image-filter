package pictureFilter.filters.pixelFilters;

import javafx.scene.image.WritableImage;

/**
 * A pictureFilter.filters.pixelFilters.PixelByPixelFilter which can be applied to {@link WritableImage}
 */
public interface PixelByPixelFilter {
    void apply(int x, int y);
}
