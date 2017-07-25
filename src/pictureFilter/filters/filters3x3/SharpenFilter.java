package pictureFilter.filters.filters3x3;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import pictureFilter.filters.Filter;

public class SharpenFilter extends PixelIterator3x3 implements Filter {
    @Override
    public void applyFilter(Image image, WritableImage writableImage) {
        if(writableImage == null || image == null) return;

        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        double imageHeight = writableImage.getHeight();
        double imageWidth = writableImage.getWidth();

        // source: http://lodev.org/cgtutor/filtering.html
        rowWise(imageHeight, imageWidth,
                (int x1, int y1, int x2, int y2, int x3, int y3,
                 int x4, int y4, int x,  int y,  int x5, int y5,
                 int x6, int y6, int x7, int y7, int x8, int y8) ->{
                    /* Get colors of neighbours for sharpen calculations */
                    Color color1 = pixelReader.getColor(x1,y1);
                    Color color2 = pixelReader.getColor(x2,y2);
                    Color color3 = pixelReader.getColor(x3,y3);
                    Color color4 = pixelReader.getColor(x4,y4);
                    Color color5 = pixelReader.getColor(x,y);
                    Color color6 = pixelReader.getColor(x5,y5);
                    Color color7 = pixelReader.getColor(x6,y6);
                    Color color8 = pixelReader.getColor(x7,y7);
                    Color color9 = pixelReader.getColor(x8,y8);

                   /* Set new color */
                    Color color = sharpen(color1, color2, color3, color4, color5, color6, color7, color8, color9);
                    pixelWriter.setColor(x,y,color);
                });
    }

    private Color sharpen(Color color1, Color color2, Color color3, Color color4,
                          Color color5, Color color6, Color color7, Color color8, Color color9) {
        double red   =  - color1.getRed() -   color2.getRed() - color3.getRed()
                        - color4.getRed() + 9*color5.getRed() - color6.getRed()
                        - color7.getRed() -   color8.getRed() - color9.getRed();

        double green  =  - color1.getGreen() -   color2.getGreen() - color3.getGreen()
                        - color4.getGreen() + 9*color5.getGreen() - color6.getGreen()
                        - color7.getGreen() -   color8.getGreen() - color9.getGreen();

        double blue =  - color1.getBlue() -   color2.getBlue() - color3.getBlue()
                        - color4.getBlue() + 9*color5.getBlue() - color6.getBlue()
                        - color7.getBlue() -   color8.getBlue() - color9.getBlue();

        if(red < 0) red = 0;
        if(blue < 0) blue = 0;
        if(green < 0) green = 0;

        if(red > 1) red = 1;
        if(blue > 1) blue = 1;
        if(green > 1) green = 1;

        return Color.color(red,green,blue);
    }

    @Override
    public String getFilterName() {
        return "sharpen";
    }
}
