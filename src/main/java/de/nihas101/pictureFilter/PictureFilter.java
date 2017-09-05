package de.nihas101.pictureFilter;

import de.nihas101.pictureFilter.filters.Filter;
import de.nihas101.pictureFilter.filters.FilterFactory;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

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
    private String format;

    public static void main(String[] args) throws IOException {
        /* Init javafx core */
        new JFXPanel();
        new PictureFilter().filterImage(args);
    }

    public void filterImage(String[] args) throws FileNotFoundException {
        if(args.length < 2) throw new Error("Not enough Arguments...");

        String fileArg = args[0];
        String filterArg = args[1];
        Image image;

        /* Check if file exists */
        Path imagePath = get(fileArg);
        if( !exists(get(fileArg)) && !isDirectory(get(fileArg)) )
            throw new FileNotFoundException("The File " + args[0] + " was not found or is a directory");

        image = loadImage(fileArg, imagePath);

        FilterFactory filterFactory = new FilterFactory(new HashMap<>());
        Filter filter = filterFactory.getFilter(filterArg);
        Image newImage = applyFilter(filter, image);

        writeImageToDisk(fileArg, newImage, format, "_" + filter.getFilterName());
    }

    /**
     * Returns the format of the image
     * @param path The path to the image
     * @return The images format
     */
    public static String getImageFormat(Path path){
        try { return probeContentType(path); }
        catch (IOException e) { e.printStackTrace(); }

        return "";
    }

    public Image loadImage(String fileArg, Path imagePath){
        /* Load the image */
        Image image = new Image("file:" + fileArg);
        /* Extract format */
        format = getFormat(imagePath);
        return image;
    }

    public String getFormat(Path imagePath){
        format = getImageFormat(imagePath);
        return format.substring(format.indexOf("/")+1);
    }

    public Image applyFilter(Filter filter, Image image){
        /* Create WritableImage */
        WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());

        /* Create and apply the filter */
        filter.applyFilter(image, writableImage);
        return writableImage;
    }

    public void writeImageToDisk(String fileArg, Image image, String format, String fileNameAddition){
        boolean write = false;

        /* Write the image */
        String newFileName = fileArg.substring(0, fileArg.indexOf(".")) + fileNameAddition
                + fileArg.substring(fileArg.indexOf("."));

        File file = new File(newFileName);
        try {
            write = write(SwingFXUtils.fromFXImage(image, null), format, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!write) out.println("Saving the image failed");
        else out.println("Image saved under: " + newFileName);
    }

    /* TODO: fix ImageIO.write bug on jpg pictures, add more filters, maybe instead of chooser drop down menu? etc*/
}
