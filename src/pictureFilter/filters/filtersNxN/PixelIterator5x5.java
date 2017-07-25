package pictureFilter.filters.filtersNxN;

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

    private int[] modulo(int[] xs, double mod) {
        for (int i = 0; i < xs.length; i++) {
            if (xs[i] < 0) xs[i] = (int) (xs[i] + mod);
            if (xs[i] >= mod) xs[i] = (int) (xs[i] - mod);
        }

        return xs;
    }
}