import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pictureFilter.PictureFilter;
import pictureFilter.PictureFilterGUI;
import pictureFilter.filters.FilterFactory;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

import static java.lang.Math.ceil;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PictureFilterTester {

    private static PictureFilter pictureFilter = new PictureFilter();
    private static FilterFactory filterFactory = new FilterFactory(new HashMap<>());
    private static Image image;
    private static double imageHeight, imageWidth;

    @Test
    void bwTester_IL() throws Exception {
        WritableImage writableImage = new WritableImage((int)imageWidth, (int)imageHeight);
        filterFactory.getFilter("bw").applyFilter(image, writableImage);

        PixelReader oPixelReader = image.getPixelReader();
        PixelReader nPixelReader = writableImage.getPixelReader();

        for( int x=0 ; x < imageWidth ; x++ ){
            for( int y=0 ; y < imageHeight ; y++ ){
                Color oColor = oPixelReader.getColor(x,y);
                Color nColor = nPixelReader.getColor(x,y);

                double grey = oColor.getRed()*.3 + oColor.getBlue()*.59 + oColor.getGreen()*.11;

                double eps = .01;
                assertEquals(grey, nColor.getRed(), eps);
                assertEquals(grey, nColor.getBlue(), eps);
                assertEquals(grey, nColor.getGreen(), eps);
            }
        }
    }

    @Test
    void mhTester_IL() throws Exception {
        WritableImage writableImage = new WritableImage((int)imageWidth, (int)imageHeight);
        filterFactory.getFilter("mh").applyFilter(image, writableImage);

        PixelReader oPixelReader = image.getPixelReader();
        PixelReader nPixelReader = writableImage.getPixelReader();

        for(int x=0 ; x < imageWidth ; x++) {
            for (int y = (int) ceil(imageHeight/2); y > 0; y--) {
                Color color1 = oPixelReader.getColor(x, y);
                Color color2 = nPixelReader.getColor(x, (int)(imageHeight - y));

                assertEquals( color1, color2 );
            }
        }
    }

    @Test
    void mhbTester_IL(){
        WritableImage writableImage = new WritableImage((int)imageWidth, (int)imageHeight);
        filterFactory.getFilter("mhb").applyFilter(image, writableImage);

        PixelReader oPixelReader = image.getPixelReader();
        PixelReader nPixelReader = writableImage.getPixelReader();

        for(int x=0 ; x < imageWidth ; x++) {
            for (int y = (int) ceil(imageHeight/2); y > 0; y--) {
                Color color1 = nPixelReader.getColor(x, y);
                Color color2 = nPixelReader.getColor(x, (int)(imageHeight - y));

                assertEquals( color1, color2 );
            }
        }
    }

    @Test
    void mhtTester_IL(){
        WritableImage writableImage = new WritableImage((int)imageWidth, (int)imageHeight);
        filterFactory.getFilter("mht").applyFilter(image, writableImage);

        PixelReader oPixelReader = image.getPixelReader();
        PixelReader nPixelReader = writableImage.getPixelReader();

        for(int x=0 ; x < imageWidth ; x++) {
            for (int y = (int) ceil(imageHeight/2); y > 0; y--) {
                Color color1 = oPixelReader.getColor(x, y);
                Color color2 = nPixelReader.getColor(x, (int)(imageHeight - y));

                assertEquals( color1, color2 );
            }
        }
    }

    @Test
    void mvTester_IL(){
        WritableImage writableImage = new WritableImage((int)imageWidth, (int)imageHeight);
        filterFactory.getFilter("mv").applyFilter(image, writableImage);

        PixelReader oPixelReader = image.getPixelReader();
        PixelReader nPixelReader = writableImage.getPixelReader();

        for(int y=0 ; y < imageHeight ; y++) {
            for (int x = (int) ceil(imageWidth/2); x > 0; x--) {
                Color color1 = oPixelReader.getColor(x, y);
                Color color2 = nPixelReader.getColor((int)(imageWidth-x), y);

                assertEquals( color1, color2 );
            }
        }
    }

    @Test
    void mvlTester_IL(){
        WritableImage writableImage = new WritableImage((int)imageWidth, (int)imageHeight);
        filterFactory.getFilter("mvl").applyFilter(image, writableImage);

        PixelReader nPixelReader = writableImage.getPixelReader();

        for(int y=0 ; y < imageHeight ; y++) {
            for (int x = (int) ceil(imageWidth/2); x > 0; x--) {
                Color color1 = nPixelReader.getColor(x, y);
                Color color2 = nPixelReader.getColor((int)(imageWidth-x), y);

                assertEquals( color1, color2 );
            }
        }
    }

    @Test
    void mvrTester_IL(){
        WritableImage writableImage = new WritableImage((int)imageWidth, (int)imageHeight);
        filterFactory.getFilter("mvr").applyFilter(image, writableImage);
        
        PixelReader nPixelReader = writableImage.getPixelReader();

        for(int y=0 ; y < imageHeight ; y++) {
            for (int x = (int) ceil(imageWidth/2); x > 0; x--) {
                Color color1 = nPixelReader.getColor(x, y);
                Color color2 = nPixelReader.getColor((int)(imageWidth-x), y);

                assertEquals( color1, color2 );
            }
        }
    }

    @Test
    void GUI_Tester() {
        try {
            PictureFilterGUI.main(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeAll
    static void initFX() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JFXPanel(); // initializes JavaFX environment
                latch.countDown();
            }
        });
        latch.await();

        String resource = "testresources/feelOfBlank.jpg";
        image = pictureFilter.loadImage(resource, Paths.get(resource));
        imageHeight = image.getHeight();
        imageWidth = image.getWidth();
    }
}
