package pictureFilter;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class TestFilter implements Filter {
    @Override
    public void apply(Image image, WritableImage writableImage) {
        if(writableImage == null || image == null) return;

        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        double imageHeight = writableImage.getHeight();
        double imageWidth = writableImage.getWidth();

        /* Iterate through all pixels, replicating the image */
        for(int y=0 ; y < imageHeight ; y++) {
            for(int x=0 ; x < imageWidth ; x++) pixelWriter.setColor(x, y, pixelReader.getColor(x, y));
        }
    }

    @Override
    public String getFilterName() {
        return "test";
    }
}
