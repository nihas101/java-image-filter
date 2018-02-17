package de.nihas101.pictureFilter.filters.pixelFilters;

import de.nihas101.pictureFilter.filters.mirrorFilters.MirrorFilter;
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
        for(int y=0 ; y < imageHeight ; y++) {
            for (int x = 0; x < imageWidth; x++) pixelByPixelFilter.apply(x, y);
        }
    }

    public void columnWise(int imageHeight, int imageWidth, PixelByPixelFilter pixelByPixelFilter){
        for (int x = 0; x < imageWidth; x++) {
            for(int y=0 ; y < imageHeight ; y++) pixelByPixelFilter.apply(x, y);
        }
    }

    protected void rowOutwards(double imageHeight, double imageWidth, MirrorFilter mirrorFilter){
        for(int y=0 ; y < imageHeight ; y++) {
            for (int x = (int) ceil(imageWidth/2); x > 0; x--)
                mirrorFilter.apply(x, y, (int) (imageWidth - x), y);
        }
    }

    protected void columnOutwards(double imageHeight, double imageWidth, MirrorFilter mirrorFilter){
        for(int x=0 ; x < imageWidth ; x++) {
            for (int y = (int) ceil(imageHeight/2); y > 0; y--)
                mirrorFilter.apply(x, y, x, (int) (imageHeight - y));
        }
    }

    protected int[] modulo(int[] xs, double mod) {
        for (int i = 0; i < xs.length; i++) {
            if (xs[i] < 0) xs[i] = (int) (xs[i] + mod);
            if (xs[i] >= mod) xs[i] = (int) (xs[i] - mod);
        }

        return xs;
    }
}
