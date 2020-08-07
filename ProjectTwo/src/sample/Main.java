/*
Assignment: Project Two
Started on: June 26, 2020
Programed by: Victor Zheng
Course: Computer Programming 11
 */

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 600, 550);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Garden Scheduler");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
