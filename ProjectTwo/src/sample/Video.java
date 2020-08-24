package sample;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class Video {

    public static void playVideo() {

        Stage newStage = new Stage();
        // Create the media source.
        //String source = getParameters().getRaw().get(0);
        File f = new File("C:\\Users\\zheng\\IdeaProjects\\ProjectTwo\\src\\sample\\Garden_Video3.mp4");
        Media m = new Media(f.toURI().toString());

        // Create the player and set to play automatically.
        MediaPlayer mediaPlayer = new MediaPlayer(m);
        mediaPlayer.setAutoPlay(true);

        // Create the view and add it to the Scene.
        MediaView mediaView = new MediaView(mediaPlayer);

        //stage.setScene(new Scene(root, 680, 400));
        VBox mediaArea = new VBox(mediaView);
        Scene scene = new Scene(mediaArea, 640, 500);

        newStage.setScene(scene);
        newStage.setTitle("Demonstration Video");
        newStage.show();
        mediaPlayer.play();

    }
}
