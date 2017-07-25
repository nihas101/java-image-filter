package pictureFilter.filters.filtersNxN;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import pictureFilter.filters.Filter;

public class FindEdges extends PixelIterator5x5 implements Filter {
    @Override
    public void applyFilter(Image image, WritableImage writableImage) {
        if(writableImage == null || image == null) return;

        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        double imageHeight = writableImage.getHeight();
        double imageWidth = writableImage.getWidth();

        // source: http://lodev.org/cgtutor/filtering.html
        rowWise(imageHeight, imageWidth,
                (x, y) ->{
                    /* Get colors of neighbours for blur calculations */
                    Color color1 = pixelReader.getColor(x[3],y[3]);
                    Color color2 = pixelReader.getColor(x[7],y[7]);
                    Color color3 = pixelReader.getColor(x[13],y[13]);
                    Color color4 = pixelReader.getColor(x[18],y[18]);
                    Color color5 = pixelReader.getColor(x[23],y[23]);

                   /* Set new color */
                    Color color = findEdgeCalc(color1, color2, color3, color4, color5);
                    pixelWriter.setColor(x[13],y[13],color);
                });
    }

    private Color findEdgeCalc(Color color1, Color color2, Color color3, Color color4, Color color5) {
        double red   = - color1.getRed()   - color2.getRed()   + 4*color3.getRed()   - color4.getRed()   - color5.getRed();
        double blue  = - color1.getBlue()  - color2.getBlue()  + 4*color3.getBlue()  - color4.getBlue()  - color5.getBlue();
        double green = - color1.getGreen() - color2.getGreen() + 4*color3.getGreen() - color4.getGreen() - color5.getGreen();

        if(red < 0) red = 0;
        if(blue < 0) blue = 0;
        if(green < 0) green = 0;

        if(red > 1) red = 1;
        if(blue > 1) blue = 1;
        if(green > 1) green = 1;

        return Color.color(red, blue, green);
    }

    @Override
    public String getFilterName() {
        return "findEdges";
    }
}
