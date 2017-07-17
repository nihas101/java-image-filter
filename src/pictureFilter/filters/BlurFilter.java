package pictureFilter.filters;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class BlurFilter extends PixelIterator implements Filter {
    @Override
    public void applyFilter(Image image, WritableImage writableImage) {
        if(writableImage == null || image == null) return;

        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        double imageHeight = writableImage.getHeight();
        double imageWidth = writableImage.getWidth();

        // source: http://lodev.org/cgtutor/filtering.html
        rowWise(imageHeight, imageWidth, (x,y) ->{
            Color color1 = pixelReader.getColor(x,y);
            /* Edgecase just copy the color */
            if(x<=0 || x>=imageWidth-1 || y<=0 || y>=imageHeight-1){
                pixelWriter.setColor(x,y,color1);
                return;
            }

            /* Get colors of neighbours for blur calculations */
            Color color2 = pixelReader.getColor(x-1,y);
            Color color3 = pixelReader.getColor(x+1,y);
            Color color4 = pixelReader.getColor(x,y-1);
            Color color5 = pixelReader.getColor(x,y+1);

            /* Set new color */
            Color color = avarageOfColors(color1,color2, color3, color4, color5);

            pixelWriter.setColor(x,y,color);
        });
    }

    @Override
    public String getFilterName() {
        return "blur";
    }

    private Color avarageOfColors(Color color1, Color color2, Color color3, Color color4, Color color5){
        double red = color1.getRed() + color2.getRed() + color3.getRed() + color4.getRed() + color5.getRed();
        double blue = color1.getBlue() + color2.getBlue() + color3.getBlue() + color4.getBlue() + color5.getBlue();
        double green = color1.getGreen() + color2.getGreen() + color3.getGreen() + color4.getGreen() + color5.getGreen();

        return new Color(red*.2, green*.2, blue*.2, color1.getOpacity());
    }
}
