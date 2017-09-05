package de.nihas101.pictureFilter.filters.filtersNxN;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import de.nihas101.pictureFilter.filters.Filter;

public class SNESMosaicFilter extends PixelIterator5x5 implements Filter {
    @Override
    public void applyFilter(Image image, WritableImage writableImage) {
        if(writableImage == null || image == null) return;

        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        double imageHeight = writableImage.getHeight();
        double imageWidth = writableImage.getWidth();

        squareRowWise(imageHeight, imageWidth,
                (x, y) ->{
                    /* Get top-left color */
                    Color color = pixelReader.getColor(x[0],y[0]);

                    /* Apply color to whole square */
                    for(int i=1 ; i < x.length ; i++) {
                        pixelWriter.setColor(x[i], y[i], color);
                    }
                });
    }

    @Override
    public String getFilterName() {
        return "SNES_Mosaic";
    }
}
