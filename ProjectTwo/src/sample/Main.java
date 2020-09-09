/*
Assignment: Project Two
Started on: June 26, 2020
Programed by: Victor Zheng
Course: Computer Programming 11
Project last edited on September 8, 2020
 */

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    // Create the primary stage for the main controller
    public void start(Stage primaryStage) throws Exception{
        // Link up the sample.fxml with this stage
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 569, 429);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Garden Scheduler");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
