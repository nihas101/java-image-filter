package de.nihas101.pictureFilter.filters.filters3x3;

import de.nihas101.pictureFilter.filters.pixelFilters.PixelIterator;

public abstract class PixelIterator3x3 extends PixelIterator {
    public void rowWise(double imageHeight, double imageWidth, Filter3x3 filter3X3){
        for(int y=0 ; y < imageHeight ; y++) {
            for (int x = 0; x < imageWidth; x++) {

                int[] xs = {x - 1, x, x + 1,
                            x - 1, x, x + 1,
                            x - 1, x, x + 1};
                int[] ys = {y - 1, y - 1, y - 1,
                            y,     y,     y,
                            y + 1, y + 1, y + 1};

                xs = modulo(xs,imageWidth);
                ys = modulo(ys,imageHeight);

                filter3X3.apply(xs[0], ys[0], xs[1], ys[1], xs[2], ys[2],
                        xs[3], ys[3], xs[4], ys[4], xs[5], ys[5],
                        xs[6], ys[6], xs[7], ys[7], xs[8], ys[8]);
            }
        }
    }

    private int[] modulo(int[] xs, double mod) {
        for (int i = 0; i < xs.length; i++) {
            if (xs[i] < 0) xs[i] = (int) (xs[i] + mod);
            if (xs[i] >= mod) xs[i] = (int) (xs[i] - mod);
        }

        return xs;
    }
}
