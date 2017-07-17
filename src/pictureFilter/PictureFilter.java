package pictureFilter;

import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import pictureFilter.filters.Filter;
import pictureFilter.filters.FilterFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import static java.lang.System.out;
import static java.nio.file.Files.*;
import static java.nio.file.Paths.get;
import static javax.imageio.ImageIO.write;

public class PictureFilter{

    public void applyFilter(String[] args) throws FileNotFoundException {
        Path imagePath;
        Image image = null;
        String fileArg, filterArg, format;
        WritableImage writableImage = null;
        BufferedImage bufferedImage = null;
        FilterFactory filterFactory = new FilterFactory(new HashMap<>());
        boolean write = false;

        if(args.length < 2) throw new Error("Not enough Arguments...");

        fileArg = args[0];
        filterArg = args[1];

        /* Check if file exists */
        imagePath = get(fileArg);
        if( !exists(get(fileArg)) && !isDirectory(get(fileArg)) )
            throw new FileNotFoundException("The File " + args[0] + " was not found or is a directory");

        /* Load the image */
        image = new Image("file:" + fileArg);

        format = getImageFormat(imagePath);
        format = format.substring(format.indexOf("/")+1);

        /* Create WritableImage */
        writableImage = new WritableImage((int)image.getWidth(), (int)image.getHeight());

        /* Create and apply the filter */
        Filter filter = filterFactory.getFilter(filterArg);
        filter.apply(image, writableImage);

        /* Write the image */
        String newFileName = fileArg.substring(0, fileArg.indexOf(".")) + "_" +  filter.getFilterName()
                + fileArg.substring(fileArg.indexOf("."));

        File file = new File(newFileName);
        try {
            write = write(SwingFXUtils.fromFXImage(writableImage, null), format, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!write) out.println("Saving the image failed");
        else out.println("Image saved under: " + newFileName);
    }

    /**
     * Returns the format of the image
     * @param path The path to the image
     * @return The images format
     */
    private static String getImageFormat(Path path){
        try { return probeContentType(path); }
        catch (IOException e) { e.printStackTrace(); }

        return "";
    }

    public static void main(String[] args) throws IOException {
        /* Init javafx core */
        final JFXPanel fxPanel = new JFXPanel();
        new PictureFilter().applyFilter(args);
    }

    /* TODO: fix ImageIO.write bug on jpg pictures, add more filters, add a save button, adjust size of imageview with pictures, mabe instead of chooser drop down menu? etc*/
}
