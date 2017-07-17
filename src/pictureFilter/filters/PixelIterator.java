package pictureFilter.filters;

import static java.lang.Math.ceil;

public abstract class PixelIterator {
    public void rowWise(double imageHeight, double imageWidth, PixelByPixelFilter pixelByPixelFilter){
        for(int y=0 ; y < imageHeight ; y++) {
            for (int x = 0; x < imageWidth; x++) {
                pixelByPixelFilter.apply(x,y);
            }
        }
    }

    public void columnWise(int imageHeight, int imageWidth, PixelByPixelFilter pixelByPixelFilter){
        for (int x = 0; x < imageWidth; x++) {
            for(int y=0 ; y < imageHeight ; y++) {
                pixelByPixelFilter.apply(x,y);
            }
        }
    }

    public void rowOutwards(double imageHeight, double imageWidth, MirrorFilter mirrorFilter){
        for(int y=0 ; y < imageHeight ; y++) {
            int offset = 0;
            for (int x = (int) ceil(imageWidth/2); x > 0; x--) {
                mirrorFilter.apply(x, y, (int)(imageWidth-x), y);
                offset++;
            }
        }
    }
}
