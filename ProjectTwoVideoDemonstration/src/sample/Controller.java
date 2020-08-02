//Project was to test how to implement a video on JavaFX
//Took a while to get used to stages and scenes. Took around 10 hrs of learning for me to really understand what was going on
// Started July 27, Ended August 2
// Victor Zheng was the sole developer. All work was made by me, and is not intended for the use of others.
package sample;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class Controller {
    public VBox mediaArea;


    public void playVideo(MouseEvent mouseEvent) {
        Stage newStage = new Stage();
        // Create the media source.
        //String source = getParameters().getRaw().get(0);
        File f = new File("C:\\Users\\zheng\\IdeaProjects\\ProjectTwoVideoDemonstration\\src\\sample\\Garden_Video.mp4");
        Media m = new Media(f.toURI().toString());

        // Create the player and set to play automatically.
        MediaPlayer mediaPlayer = new MediaPlayer(m);
        mediaPlayer.setAutoPlay(true);

        // Create the view and add it to the Scene.
        MediaView mediaView = new MediaView(mediaPlayer);

        //stage.setScene(new Scene(root, 680, 400));
        mediaArea = new VBox(mediaView);
        Scene scene = new Scene(mediaArea, 640, 500);

        newStage.setScene(scene);
        newStage.setTitle("Demonstration Video");
        newStage.show();
        mediaPlayer.play();
    }
}
