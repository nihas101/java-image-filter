package de.nihas101.simpleImageFilter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ImageFilterGUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("main.fxml"));
        Pane pane = loader.load();

        primaryStage.setTitle("SimpleImageFilter");
        primaryStage.setScene(new Scene(pane));

        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
