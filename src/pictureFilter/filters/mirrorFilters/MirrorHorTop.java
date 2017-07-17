package pictureFilter.filters.mirrorFilters;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import pictureFilter.filters.Filter;
import pictureFilter.filters.PixelIterator;

public class MirrorHorTop extends PixelIterator implements Filter {
    @Override
    public void applyFilter(Image image, WritableImage writableImage) {
        if(writableImage == null || image == null) return;

        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        double imageHeight = writableImage.getHeight();
        double imageWidth = writableImage.getWidth();

        columnOutwards(imageHeight, imageWidth, (x1,y1,x2,y2) ->{
            Color color = pixelReader.getColor(x1,y1);

            /* Set new color */
            pixelWriter.setColor(x1,y1,color);
            pixelWriter.setColor(x2,y2,color);
        });
    }

    @Override
    public String getFilterName() {
        return "mht";
    }
}
