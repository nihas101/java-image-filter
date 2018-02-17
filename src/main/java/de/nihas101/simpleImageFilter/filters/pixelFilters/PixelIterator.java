package de.nihas101.simpleImageFilter.filters.pixelFilters;

import de.nihas101.simpleImageFilter.filters.mirrorFilters.MirrorFilter;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import static java.lang.Math.ceil;

public abstract class PixelIterator {
    protected PixelReader pixelReader;
    protected PixelWriter pixelWriter;

    protected double imageHeight;
    protected double imageWidth;

    protected void setup(Image image, WritableImage writableImage){
        this.pixelReader = image.getPixelReader();
        this.pixelWriter = writableImage.getPixelWriter();

        imageHeight = writableImage.getHeight();
        imageWidth = writableImage.getWidth();
    }

    public void rowWise(double imageHeight, double imageWidth, PixelByPixelFilter pixelByPixelFilter){
        iterateThroughAllPixels((int)imageHeight, (int)imageWidth, (y,x) -> pixelByPixelFilter.apply(x,y));
    }

    public void columnWise(double imageHeight, double imageWidth, PixelByPixelFilter pixelByPixelFilter){
        iterateThroughAllPixels((int)imageWidth, (int)imageHeight, pixelByPixelFilter::apply);
    }

    private void iterateThroughAllPixels(int outerLimit, int innerLimit, PixelFilter pixelFilter){
        for (int x = 0; x < outerLimit; x++) {
            for(int y=0 ; y < innerLimit ; y++) pixelFilter.apply(x, y);
        }
    }

    protected void rowOutwards(double imageHeight, double imageWidth, MirrorFilter mirrorFilter){
        iterateThroughPixels(
                (int)imageHeight,
                (int)ceil(imageWidth/2),
                (y, x) -> mirrorFilter.apply(x, y,(int) (imageWidth - x), y)
        );
    }

    protected void columnOutwards(double imageHeight, double imageWidth, MirrorFilter mirrorFilter){
        iterateThroughPixels(
                (int)imageWidth,
                (int)ceil(imageHeight/2),
                (x, y) -> mirrorFilter.apply(x, y, x, (int) (imageHeight - y))
        );
    }

    private void iterateThroughPixels(int outerLimit, int innerLimit, PixelFilter pixelFilter){
        for(int x=0 ; x < outerLimit ; x++)
            for(int y = innerLimit; y > 0; y--) pixelFilter.apply(x, y);
    }

    protected int[] modulo(int[] xs, double mod) {
        for (int i = 0; i < xs.length; i++) {
            if (xs[i] < 0) xs[i] = (int) (xs[i] + mod);
            if (xs[i] >= mod) xs[i] = (int) (xs[i] - mod);
        }

        return xs;
    }
}
