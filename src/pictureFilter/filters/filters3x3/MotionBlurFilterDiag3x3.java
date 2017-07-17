package pictureFilter.filters.filters3x3;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import pictureFilter.filters.Filter;

import static java.lang.Math.*;

public class MotionBlurFilterDiag3x3 extends PixelIterator3x3 implements Filter {

    @Override
    public void applyFilter(Image image, WritableImage writableImage) {
        if(writableImage == null || image == null) return;

        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        double imageHeight = writableImage.getHeight();
        double imageWidth = writableImage.getWidth();

        // source: http://lodev.org/cgtutor/filtering.html
        rowWise(imageHeight, imageWidth,
                (x,y) -> {
                    Color color = pixelReader.getColor(x, y);
                    /* Edgecase just copy the color */
                    pixelWriter.setColor(x, y, color);
                },
                (int x1, int y1, int x2, int y2, int x3, int y3,
                 int x4, int y4, int x,  int y,  int x5, int y5,
                 int x6, int y6, int x7, int y7, int x8, int y8) ->{
                    /* Get colors of neighbours for blur calculations */
                    Color color1 = pixelReader.getColor(x1,y1);
                    Color color2 = pixelReader.getColor(x,y);
                    Color color3 = pixelReader.getColor(x8,y8);

                   /* Set new color */
                   Color color = averageOfColors(color1, color2, color3);
                   pixelWriter.setColor(x,y,color);
        });
    }

    private Color averageOfColors(Color... colors){
        double red = 0;
        double blue = 0;
        double green = 0;

        for (Color color : colors) {
            red   += color.getRed();
            blue  += color.getBlue();
            green += color.getGreen();
        }

        int middle = (int) ceil(colors.length/2);

        return new Color(red/3, green/3, blue/3, colors[middle].getOpacity());
    }

    @Override
    public String getFilterName() {
        return "motionblurDiag3x3";
    }
}
