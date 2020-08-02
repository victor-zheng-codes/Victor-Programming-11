package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    public VBox mediaArea;

    @Override
    /*
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }



    public void start(Stage stage) throws Exception{
        // Create and set the Scene.
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Scene scene = new Scene(root, 640, 500);

        //Scene scene = new Scene(new Group(), 640, 500);
        stage.setScene(scene);

        // Name and display the Stage.
        stage.setTitle("Hello Media");
        stage.show();

        // Create the media source.
        //String source = getParameters().getRaw().get(0);
        File f = new File("C:\\Users\\zheng\\IdeaProjects\\ProjectTwoVideoDemonstration\\src\\sample\\Garden_Video.mp4");
        Media m = new Media(f.toURI().toString());

        // Create the player and set to play automatically.
        MediaPlayer mediaPlayer = new MediaPlayer(m);
        mediaPlayer.setAutoPlay(true);

        // Create the view and add it to the Scene.
        MediaView mediaView = new MediaView(mediaPlayer);
        //((Group) scene.getRoot()).getChildren().add(mediaView);
        //stage.setScene(new Scene(root, 680, 400));
        mediaPlayer.play();
        stage.show();
    }

     */
    public void start(Stage primaryStage) throws Exception{
        // Create and set the Scene.
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 640, 500);

        //Scene scene = new Scene(new Group(), 640, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Garden Scheduler");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

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
        //((Group) scene.getRoot()).getChildren().add(mediaView);
        //stage.setScene(new Scene(root, 680, 400));
        mediaArea = new VBox(mediaView);
        mediaArea.setDisable(false);
        mediaArea.setVisible(true);

        Scene scene = new Scene(mediaArea, 640, 500);

        //Scene scene = new Scene(new Group(), 640, 500);
        newStage.setScene(scene);
        newStage.setTitle("Demonstration Video");

        newStage.show();
        mediaPlayer.play();
    }
}
