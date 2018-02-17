package de.nihas101.pictureFilter.filters.filtersNxN;

import de.nihas101.pictureFilter.filters.Filter;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class FindEdges extends PixelIterator5x5 implements Filter {
    @Override
    public void applyFilter(Image image, WritableImage writableImage) {
        setup(image, writableImage);

        // source: http://lodev.org/cgtutor/filtering.html
        rowWise(imageHeight, imageWidth,
                (FilterNxN) (x, y) ->{
                    /* Get colors of neighbours for blur calculations */
                    Color color1 = pixelReader.getColor(x[3],y[3]);
                    Color color2 = pixelReader.getColor(x[7],y[7]);
                    Color color3 = pixelReader.getColor(x[13],y[13]);
                    Color color4 = pixelReader.getColor(x[18],y[18]);
                    Color color5 = pixelReader.getColor(x[23],y[23]);

                   /* Set new color */
                    Color color = findEdge(color1, color2, color3, color4, color5);
                    pixelWriter.setColor(x[13],y[13],color);
                });
    }

    private Color findEdge(Color color1, Color color2, Color color3, Color color4, Color color5) {
        double red   = findEdge(new double[]{color1.getRed(), color2.getRed(), color3.getRed(), color4.getRed(), color5.getRed()});
        double blue  = findEdge(new double[]{color1.getBlue(), color2.getBlue(), color3.getBlue(), color4.getBlue(), color5.getBlue()});
        double green = findEdge(new double[]{color1.getGreen(), color2.getGreen(), color3.getGreen(), color4.getGreen(), color5.getGreen()});

        red = min(max(red,0),1);
        green = min(max(green,0),1);
        blue = min(max(blue,0),1);

        return Color.color(red, blue, green);
    }

    private double findEdge(double[] colors){
        return - colors[0] - colors[1] + 4*colors[2] - colors[3] - colors[4];
    }

    @Override
    public String getFilterName() {
        return "Find edges";
    }
}
