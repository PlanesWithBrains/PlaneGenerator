package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("generator.fxml")); //подгружаем наш шаблон окна

        Scene scene = new Scene(root); //хуйня чисто для scene builder
        stage.setTitle("Plane Generator ©Hobots inc"); //название окна
        stage.setScene(scene); //хуйня чисто для scene builder
        stage.show(); //запускаем окно
    }


    public static void main(String[] args) {
        launch(args);
    }
}
