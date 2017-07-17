package pictureFilter.filters;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class TestFilter extends PixelIterator implements Filter {
    @Override
    public void applyFilter(Image image, WritableImage writableImage) {
        if(writableImage == null || image == null) return;

        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        double imageHeight = writableImage.getHeight();
        double imageWidth = writableImage.getWidth();

        rowWise(imageHeight, imageWidth, (x,y) ->{
            pixelWriter.setColor(x, y, pixelReader.getColor(x, y));
        });
    }

    @Override
    public String getFilterName() {
        return "test";
    }
}
