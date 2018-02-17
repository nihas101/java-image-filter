package de.nihas101.simpleImageFilter.filters.filtersNxN;

import de.nihas101.simpleImageFilter.filters.Filter;
import de.nihas101.simpleImageFilter.filters.utils.ColorSum;
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
                    applyToSquare(x, y, color);
                });
    }

    protected void applyToSquare(int[] x, int[] y, Color color){
        for(int i=1 ; i < x.length ; i++) pixelWriter.setColor(x[i], y[i], color);
    }

    private Color averageOfColors(int[] xs, int[] ys, PixelReader pixelReader){
        ColorSum colorSum = new ColorSum();

        for (int x : xs) {
            for (int y : ys) {
                Color color = pixelReader.getColor(x,y);
                colorSum.add(color);
            }
        }

        double opacity = pixelReader.getColor(
                (int)ceil(xs.length/((double)2)),
                (int)ceil(ys.length/((double)2))
        ).getOpacity();

        int nrOfEle = xs.length * ys.length;

        colorSum.setRed(min(max(colorSum.getRed()/nrOfEle,0),1));
        colorSum.setGreen(min(max(colorSum.getGreen()/nrOfEle,0),1));
        colorSum.setBlue(min(max(colorSum.getBlue()/nrOfEle,0),1));

        return new Color(colorSum.getRed(), colorSum.getGreen(), colorSum.getBlue(), opacity);
    }

    @Override
    public String getFilterName() {
        return "Mosaic";
    }
}
