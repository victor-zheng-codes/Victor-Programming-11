package sample;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class Video {

    //Requires: nothing
    //Modifies: nothing
    //Effects: Retrieves a video and opens it in a new stage
    public static void playVideo() {
        // Create a new stage to play video on
        Stage newStage = new Stage();
        // Create the media source.
        File f = new File("C:\\Users\\zheng\\IdeaProjects\\ProjectTwo\\src\\sample\\GardenVideoTemp.mp4");
        Media m = new Media(f.toURI().toString());

        // Create the player and set to play automatically.
        MediaPlayer mediaPlayer = new MediaPlayer(m);
        mediaPlayer.setAutoPlay(true);

        // Create the view and add the media tp ot.
        MediaView mediaView = new MediaView(mediaPlayer);

        // Store the mediaView inside a vertical box
        VBox mediaArea = new VBox(mediaView);
        // Create a new scene for the VBox
        Scene scene = new Scene(mediaArea, 640, 500);
        // Set the new scene with title and play the video
        newStage.setScene(scene);
        newStage.setTitle("Demonstration Video");
        newStage.show();
        mediaPlayer.play();

    }
}
