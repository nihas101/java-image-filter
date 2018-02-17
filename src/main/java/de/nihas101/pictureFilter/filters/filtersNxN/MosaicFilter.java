package de.nihas101.pictureFilter.filters.filtersNxN;

import de.nihas101.pictureFilter.filters.Filter;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import static java.lang.Math.*;

public class MosaicFilter extends PixelIterator5x5 implements Filter {
    @Override
    public void applyFilter(Image image, WritableImage writableImage) {
        setup(image, writableImage);

        squareRowWise(imageHeight, imageWidth,
                (x, y) ->{
                    /* Get average of colors */
                    Color color = averageOfColors(x,y, pixelReader);

                    /* Apply color to whole square */
                    for(int i=1 ; i < x.length ; i++) {
                        pixelWriter.setColor(x[i], y[i], color);
                    }
                });
    }

    private Color averageOfColors(int[] xs, int[] ys, PixelReader pixelReader){
        double red = 0;
        double blue = 0;
        double green = 0;

        for (int x : xs) {
            for (int y : ys) {
                Color color = pixelReader.getColor(x,y);
                red   += color.getRed();
                blue  += color.getBlue();
                green += color.getGreen();
            }
        }

        double opacity = pixelReader.getColor(
                (int)ceil(xs.length/((double)2)),
                (int)ceil(ys.length/((double)2))
        ).getOpacity();

        int nrOfEle = xs.length * ys.length;

        red = min(max(red/nrOfEle,0),1);
        green = min(max(green/nrOfEle,0),1);
        blue = min(max(blue/nrOfEle,0),1);

        return new Color(red, green, blue, opacity);
    }

    @Override
    public String getFilterName() {
        return "Mosaic";
    }
}
