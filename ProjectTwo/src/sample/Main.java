/*
Assignment: Project Two
Started on: June 26, 2020
Programed by: Victor Zheng
Course: Computer Programming 11
 */

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Garden Scheduler");
        primaryStage.setScene(new Scene(root, 600, 480));
        primaryStage.show();

        String path = "C:\\Users\\zheng\\IdeaProjects\\ProjectTwo\\src\\sample\\music.mp3";
        Media media = new Media(new File(path).toURI().toString());

        // Create the player and set to play automatically.
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        //Lower the volume
        mediaPlayer.setVolume(0.1);

        // Create the view and add it to the Scene.
        MediaView mediaView = new MediaView(mediaPlayer);
        Group newRoot = new Group();
        newRoot.getChildren().add(mediaView);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
