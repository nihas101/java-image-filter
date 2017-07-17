package pictureFilter;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

/**
 * A pictureFilter.Filter which can be applied to {@link WritableImage}
 */
public interface Filter {
    /**
     * Applies the {@link Filter} to the {@link WritableImage}
     * @param writableImage The image which the filter is to be applied to
     */
    void apply(Image image, WritableImage writableImage);

    String getFilterName();
}
