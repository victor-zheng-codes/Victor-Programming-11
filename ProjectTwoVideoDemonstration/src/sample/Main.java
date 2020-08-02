package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    public VBox mediaArea;

    @Override

    public void start(Stage primaryStage) throws Exception{
        // Create and set the Scene.
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 700, 600);

        //Scene scene = new Scene(new Group(), 640, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Garden Scheduler");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
