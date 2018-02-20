package de.nihas101.simpleImageFilter;

import de.nihas101.simpleImageFilter.filters.Filter;
import de.nihas101.simpleImageFilter.filters.FilterFactory;
import de.nihas101.simpleImageFilter.filters.utils.NotEnoughArgumentsException;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.logging.Logger;

import static java.lang.System.out;
import static java.nio.file.Files.*;
import static java.nio.file.Paths.get;
import static javafx.embed.swing.SwingFXUtils.fromFXImage;
import static javax.imageio.ImageIO.write;

public class ImageFilter {
    private String format;
    private static Logger logger = Logger.getLogger(ImageFilter.class.getName());

    public static void main(String[] args) throws IOException, NotEnoughArgumentsException {
        /* Init javafx core */
        new JFXPanel();
        new ImageFilter().filterImage(args);
    }

    private void filterImage(String[] args) throws FileNotFoundException, NotEnoughArgumentsException {
        if (args.length < 2) throw new NotEnoughArgumentsException();

        String fileArg = args[0];
        String filterArg = args[1];
        Image image;

        /* Check if file exists */
        Path imagePath = get(fileArg);
        if (!exists(get(fileArg)) && !isDirectory(get(fileArg)))
            throw new FileNotFoundException("The File " + args[0] + " was not found or is a directory");

        image = loadImage(fileArg, imagePath);

        FilterFactory filterFactory = new FilterFactory(new HashMap<>());
        Filter filter = filterFactory.getFilter(filterArg);
        Image newImage = applyFilter(filter, image);

        writeImageToDisk(fileArg, newImage, format, "_" + filter.getFilterName());
    }

    /**
     * Returns the format of the image
     *
     * @param path The path to the image
     * @return The images format
     */
    private static String getImageFormat(Path path) {
        try {
            return probeContentType(path);
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }

        return "";
    }

    private Image loadImage(String fileArg, Path imagePath) {
        /* Load the image */
        Image image = new Image("file:" + fileArg);
        /* Extract format */
        format = getFormat(imagePath);
        return image;
    }

    public Image loadImage(URI uri) {
        /* Load the image */
        Image image = new Image(uri.toString());
        /* Extract format */
        format = getFormat(Paths.get(uri));
        return image;
    }

    public String getFormat(Path imagePath) {
        format = getImageFormat(imagePath);
        return format.substring(format.indexOf("/") + 1);
    }

    public Image applyFilter(Filter filter, Image image) {
        /* Create WritableImage */
        WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());

        /* Create and apply the filter */
        filter.applyFilter(image, writableImage);
        return writableImage;
    }

    public void writeImageToDisk(String fileArg, Image image, String format, String fileNameAddition) {
        boolean write = false;
        BufferedImage bufferedImage;

        /* Write the image */
        String newFileName = fileArg.substring(0, fileArg.indexOf(".")) + fileNameAddition
                + fileArg.substring(fileArg.indexOf("."));

        File file = new File(newFileName);

        if ("jpeg".equals(format)) bufferedImage = createBufferedImageFromJPG(image);
        else bufferedImage = createBufferedImage(image);

        try {
            write = write(bufferedImage, format, file);
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }

        if (!write) out.println("Saving the image failed");
        else out.println("Image saved under: " + newFileName);
    }

    /**
     * Hack to remove alpha from JPG images, otherwise they are tinted pink
     *
     * @param image The image to convert to JPG without alpa
     * @return The image without alpha values
     */
    private BufferedImage createBufferedImageFromJPG(Image image) {
        BufferedImage newImage = fromFXImage(image, null);
        BufferedImage imageRGB = new BufferedImage((int) image.getWidth(), (int) image.getHeight(), BufferedImage.OPAQUE);

        Graphics2D graphics = imageRGB.createGraphics();
        graphics.drawImage(newImage, 0, 0, null);
        graphics.dispose();

        return imageRGB;
    }

    private BufferedImage createBufferedImage(Image image) {
        return fromFXImage(image, null);
    }
}
