package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Plane Generator");
        stage.setScene(scene);
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("logo.png")));
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) { launch(args);
    }
}
