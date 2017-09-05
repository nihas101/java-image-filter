package de.nihas101.pictureFilter.filters.filtersNxN;

public abstract class PixelIterator5x5 {
    public void rowWise(double imageHeight, double imageWidth, FilterNxN filterNxN) {
        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                    int[] xs = {x - 2, x - 1, x, x + 1, x + 2,
                            x - 2, x - 1, x, x + 1, x + 2,
                            x - 2, x - 1, x, x + 1, x + 2,
                            x - 2, x - 1, x, x + 1, x + 2,
                            x - 2, x - 1, x, x + 1, x + 2};

                    int[] ys = {y - 2, y - 2, y - 2, y - 2, y - 2,
                            y - 1, y - 1, y - 1, y - 1, y - 1,
                            y, y, y, y, y,
                            y + 1, y + 1, y + 1, y + 1, y + 1,
                            y + 2, y + 2, y + 2, y + 2, y + 2};

                    xs = modulo(xs, imageWidth);
                    ys = modulo(ys, imageHeight);

                    filterNxN.apply(xs, ys);
            }
        }
    }

    public void squareRowWise(double imageHeight, double imageWidth, FilterNxN filterNxN){
        for (int y = 0; y < imageHeight; y+=4) {
            for (int x = 0; x < imageWidth; x+=4) {
                int[] xs = {x, x + 1, x + 2, x + 3, x + 4,
                        x, x + 1, x + 2, x + 3, x + 4,
                        x, x + 1, x + 2, x + 3, x + 4,
                        x, x + 1, x + 2, x + 3, x + 4,
                        x, x + 1, x + 2, x + 3, x + 4};

                int[] ys = {y, y, y, y, y,
                        y + 1, y + 1, y + 1, y + 1, y + 1,
                        y + 2, y + 2, y + 2, y + 2, y + 2,
                        y + 3, y + 3, y + 3, y + 3, y + 3,
                        y + 4, y + 4, y + 4, y + 4, y + 4};

                xs = ceil(xs, imageWidth);
                ys = ceil(ys, imageHeight);

                filterNxN.apply(xs, ys);
            }
        }
    }

    private int[] ceil(int[] xs, double mod) {
        for (int i = 0; i < xs.length; i++) {
            if (xs[i] < 0) xs[i] = 0;
            if (xs[i] >= mod) xs[i] = (int) mod-1;
        }

        return xs;
    }

    private int[] modulo(int[] xs, double mod) {
        for (int i = 0; i < xs.length; i++) {
            if (xs[i] < 0) xs[i] = (int) (xs[i] + mod);
            if (xs[i] >= mod) xs[i] = (int) (xs[i] - mod);
        }

        return xs;
    }
}