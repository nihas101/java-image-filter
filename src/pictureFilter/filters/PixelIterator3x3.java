package pictureFilter.filters;

public class PixelIterator3x3 {
    public void rowWise(double imageHeight, double imageWidth,
                        PixelByPixelFilter pixelByPixelFilter, Filter3x3 filter3X3){
        for(int y=0 ; y < imageHeight ; y++) {
            for (int x = 0; x < imageWidth; x++)
                /* Edgecase */
                if(x<=0 || x>=imageWidth-1 || y<=0 || y>=imageHeight-1) pixelByPixelFilter.apply(x, y);
                else filter3X3.apply(   x-1, y-1, x, y-1, x+1, y-1,
                                        x-1, y, x, y, x+1, y,
                                        x-1, y+1, x, y+1, x+1, y+1);
        }
    }
}
