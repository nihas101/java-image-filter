package pictureFilter.filters.filtersNxN;

import pictureFilter.filters.pixelFilters.PixelByPixelFilter;

public abstract class PixelIterator5x5 {
    public void rowWise(double imageHeight, double imageWidth,
                        PixelByPixelFilter pixelByPixelFilter, FilterNxN filterNxN){
        for(int y=0 ; y < imageHeight ; y++) {
            for (int x = 0; x < imageWidth; x++)
                /* Edgecase */
                if(x<=2 || x>=imageWidth-2 || y<=2 || y>=imageHeight-2) pixelByPixelFilter.apply(x, y);
                else{
                    int[] xs = {x-2 , x-1 , x   , x+1 , x+2 ,
                                x-2 , x-1 , x   , x+1 , x+2 ,
                                x-2 , x-1 , x   , x+1 , x+2 ,
                                x-2 , x-1 , x   , x+1 , x+2 ,
                                x-2 , x-1 , x   , x+1 , x+2};

                    int[] ys = {y-2 , y-2 , y-2 , y-2 , y-2 ,
                                y-1 , y-1 , y-1 , y-1 , y-1 ,
                                y   , y   , y   , y   , y   ,
                                y+1 , y+1 , y+1 , y+1 , y+1 ,
                                y+2 , y+2 , y+2 , y+2 , y+2};
                    filterNxN.apply(xs, ys);
                }
            }
        }
}
