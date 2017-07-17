package pictureFilter;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import pictureFilter.filters.Filter;
import pictureFilter.filters.FilterFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import static java.lang.String.CASE_INSENSITIVE_ORDER;
import static javafx.collections.FXCollections.observableArrayList;
import static pictureFilter.PictureFilter.getImageFormat;

public class Controller implements Initializable {
    @FXML
    private TextField pathTextField;
    @FXML
    public Button saveButton;
    @FXML
    private ImageView beforeImageView;
    @FXML
    private ImageView afterImageView;
    @FXML
    private Button loadImageButton;
    @FXML
    private ChoiceBox filterPicker;

    private Image image;
    private WritableImage writableImage;
    private static FilterFactory filterFactory = new FilterFactory(new HashMap<>());
    private Filter filter;
    private String format;
    private PictureFilter pictureFilter;

    public void openFileManager(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        BufferedImage bufferedImage = null;
        /* Set filter */
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        File file = fileChooser.showOpenDialog(null);


        if(file != null){
            try {bufferedImage = ImageIO.read(file); }
            catch (IOException e) { e.printStackTrace(); }
        }else return;

        if(bufferedImage != null){
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            beforeImageView.setImage(image);
        }else return;

        format = pictureFilter.getFormat(file.toPath());

        pathTextField.setText(file.getAbsolutePath());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> filters = new ArrayList<>();

        filterFactory.getFilters().forEach( filter -> filters.add(filter.getFilterName()) );

        filters.sort(CASE_INSENSITIVE_ORDER);

        System.out.println("Possible filters: " + filters.toString());

        filterPicker.setItems( observableArrayList(filters) );

        filterPicker.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(image == null) return;
            /* Apply filter and display */
            writableImage = new WritableImage((int)image.getWidth(), (int)image.getHeight());
            filter = filterFactory.getFilter(newValue.toString());
            filter.applyFilter(image, writableImage);
            afterImageView.setImage(writableImage);
        });

        pictureFilter = new PictureFilter();
    }

    public void writeImageToDisk(ActionEvent actionEvent) {
        pictureFilter.writeImageToDisk(pathTextField.getText().trim(), writableImage, format, "");
    }
}
