package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.*;
import java.nio.Buffer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Controller {
    public ListView<Plant> plantsList = new ListView<>();
    public TextArea notesOnPlant;
    public TextField plantName;
    public DatePicker harvestEstimate;
    public DatePicker startDate;

    //public DatePicker startDate;
    public Label plantNameShow;
    public Label growDateShow;
    public Label notesShow;
    public Label plantNameShowHarvest;
    public Label harvestDateShow;
    public Label notesShowHarvest;
    public HBox harvestScheduleView;
    public HBox growScheduleView;
    //Init to alerts class
    public ListView<String> harvestAlerts = new ListView<>();
    public ListView<String> growAlerts = new ListView<>();
    public Button createPlantButton;
    public ListView<String> growScheduleList = new ListView<>();
    public ListView<String> harvestScheduleList = new ListView<>();
    public MediaView media;
    public Button removePlantGrowButton;
    public Button removePlantHarvestButton;
    public Button loadFromFileButton;

    /*
    public void loadGrowList() throws IOException {
        ObservableList<String> plantList = growScheduleList.getItems();
        Object listName = growScheduleList.getSelectionModel().getSelectedItem();

        //Clear the file first
        FileWriter fileWrite = new FileWriter(listName + ".txt", false);
        BufferedWriter bufferWrite = new BufferedWriter(fileWrite);
        bufferWrite.write("");
        bufferWrite.close();

        System.out.println("saving to file: " + listName);

        for (Plant i : plantList) {
            System.out.println("writing this: " + i);
            i.writeToFile(listName + ".txt");
        }
    }

     */
    //Requires: Nothing
    //Modifies: nothing
    //Effects: clears the text fields that are for creating a new plant
    public void clearCreate(){
        plantName.clear();
        startDate.getEditor().clear();
        harvestEstimate.getEditor().clear();
        notesOnPlant.clear();
    }

    //Requires: Nothing
    //Modifies: nothing
    //Effects: clears the labels for grow
    public void clearGrow(){
        plantNameShow.setText("");
        growDateShow.setText("");
        notesShow.setText("");
    }

    //Requires: Nothing
    //Modifies: nothing
    //Effects: clears the labels for grow
    public void clearHarvest(){
        plantNameShowHarvest.setText("");
        harvestDateShow.setText("");
        notesShowHarvest.setText("");
    }


    //Requires: nothing
    //Modifies: this
    //Effects: creates a new plant and saves it to file
    public void createPlant(ActionEvent actionEvent) throws IOException{
        String name = plantName.getText();
        LocalDate plantStartDate = startDate.getValue();
        LocalDate harvestDate = harvestEstimate.getValue();
        String notes = notesOnPlant.getText();

        Plant newPlant = new Plant(name, plantStartDate.toString(), harvestDate.toString(), notes);
        System.out.println(newPlant);
        //loadGrowList();

        growScheduleList.getItems().add(newPlant.getName());
        harvestScheduleList.getItems().add(newPlant.getName());
        plantsList.getItems().add(newPlant);

        growAlerts.getItems().add(newPlant.getName() + " ---- " + newPlant.getStartDate());
        harvestAlerts.getItems().add(newPlant.getName() + " ---- " + newPlant.getHarvestDate());
        //Clear the create fields for the next plant
        clearCreate();

        ObservableList<Plant> plantListings = plantsList.getItems();

        for(Plant i : plantListings) {
            System.out.println("writing this: " + i);
            i.writeToFile(newPlant + ".txt", false);
            i.writeToList();
        }
    }

    public void viewGrowList(MouseEvent mouseEvent) throws IOException{
        String selected = growScheduleList.getSelectionModel().getSelectedItem();
        FileReader fr = new FileReader(selected + ".txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        int counter = 0;
        String notes = "";

        while ((line = br.readLine()) != null) {
            counter ++;
            System.out.println(counter);
            System.out.println("line is: " + line);

            if(counter == 1){
                plantNameShow.setText(line);
            }
            //Find the grow date date
            if(counter == 2){
                growDateShow.setText(line);
            }
            //The first line for notes
            if(counter == 4) {
                notes = line;
            }
            //Only works if not empty
            if(counter == 5 && !line.equals(";")){
                System.out.println("Counter is 5");
                notesShow.setText(notes + "\r" + line);
                break;
            }
            else if(line.equals(";")){
                notesShow.setText(notes);
            }
        }
        br.close();
    }

    public void viewHarvestList(MouseEvent mouseEvent) throws IOException{
        String selected = harvestScheduleList.getSelectionModel().getSelectedItem();
        FileReader fr = new FileReader(selected + ".txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        int counter = 0;
        String notes = "";

        while ((line = br.readLine()) != null) {
            counter ++;
            System.out.println(counter);
            System.out.println("line is: " + line);

            if(counter == 1){
                plantNameShowHarvest.setText(line);
            }
            //Skip the grow date
            if(counter == 3){
                harvestDateShow.setText(line);
            }
            //The first line for notes
            if(counter == 4) {
                notes = line;
            }
            //Only works if not empty
            if(counter == 5 && !line.equals(";")){
                System.out.println("Counter is 5");
                notesShowHarvest.setText(notes + "\r" + line);
                break;
            }
            else if(line.equals(";")){
                notesShowHarvest.setText(notes);
            }
        }
        br.close();

    }

    public void loadFromFile(MouseEvent mouseEvent) throws IOException {
        System.out.println("Loading list");
        loadFromFileButton.setDisable(true);
        //plantsList.getItems().clear();

        System.out.println("updating new load");
        FileReader fr = new FileReader("plantList.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("line is: " + line);
            //plantsList.getItems().add(line);
            harvestScheduleList.getItems().add(line);
            growScheduleList.getItems().add(line);
            harvestAlerts.getItems().add(line);
            growAlerts.getItems().add(line);
        }
        br.close();

    }

    public void removePlantGrow(MouseEvent mouseEvent) throws IOException{
        File inputFile = new File ("plantList.txt");
        File tempFile = new File ("tempFile.txt");

        Object locateName = growScheduleList.getSelectionModel().getSelectedItem();
        growScheduleList.getItems().remove(locateName);
        harvestScheduleList.getItems().remove(locateName);

        System.out.println("Removing this: " + locateName);

        FileReader fr = new FileReader(inputFile);
        BufferedReader br = new BufferedReader(fr);
        String line;

        //Write to a temporary file that we can copy back to later
        FileWriter fileWrite = new FileWriter(tempFile);
        BufferedWriter bufferWrite = new BufferedWriter(fileWrite);

        while ((line = br.readLine()) != null) {
            System.out.println("line located: " + line + " located: " + locateName);
            //When we locate the line, we can delete this from the list
            if(line.equals(locateName)){
                System.out.println("line located: " + line + " located: " + locateName);
                System.out.println("line is: " + line);
                growScheduleList.getItems().remove(line);

                //Remove file from folder
                File file = new File (line + ".txt");
                System.out.println(file.delete());
            }
            else{
                //Else, we write the file back into the list
                System.out.println("wrote: " + line);
                bufferWrite.write(line + "\r");
            }
        }
        br.close();
        bufferWrite.close();
        copyFile();
        deleteFile(locateName.toString());

    }

    public void removePlantHarvest(MouseEvent mouseEvent) throws IOException{
        File inputFile = new File ("plantList.txt");
        File tempFile = new File ("tempFile.txt");

        Object locateName = harvestScheduleList.getSelectionModel().getSelectedItem();
        harvestScheduleList.getItems().remove(locateName);
        growScheduleList.getItems().remove(locateName);

        System.out.println("Removing this: " + locateName);

        FileReader fr = new FileReader(inputFile);
        BufferedReader br = new BufferedReader(fr);
        String line;

        //Write to a temporary file that we can copy back to later
        FileWriter fileWrite = new FileWriter(tempFile);
        BufferedWriter bufferWrite = new BufferedWriter(fileWrite);

        while ((line = br.readLine()) != null) {
            System.out.println("line located: " + line + " located: " + locateName);
            //When we locate the line, we can delete this from the list
            if(line.equals(locateName)){
                System.out.println("line located: " + line + " located: " + locateName);
                System.out.println("line is: " + line);
                harvestScheduleList.getItems().remove(line);

                //Remove file from folder
                File file = new File (line + ".txt");
                System.out.println(file.delete());
            }
            else{
                //Else, we write the file back into the list
                System.out.println("wrote: " + line);
                bufferWrite.write(line + "\r");
            }
        }
        br.close();
        bufferWrite.close();
        copyFile();
        deleteFile(locateName.toString());

    }

    //Requires: nothing
    //Modifies: File plantList and File tempFile
    //Effects: reads tempFile.txt and copies it to the plantList.txt.
    public void copyFile() throws IOException {
        FileWriter fileWrite = new FileWriter("plantList.txt");
        BufferedWriter bufferWrite = new BufferedWriter(fileWrite);

        FileReader frTemp = new FileReader("tempFile.txt");
        BufferedReader brTemp = new BufferedReader(frTemp);

        String line;

        while ((line = brTemp.readLine()) != null) {
            bufferWrite.write(line + "\r");
        }
        bufferWrite.close();
        brTemp.close();
    }

    //Requires: nothing
    //Modifies: nothing
    //Effects: Deletes specified file
    public void deleteFile(String fileName) throws IOException{
        File newFile = new File("C:\\Users\\zheng\\IdeaProjects\\ProjectTwo\\", fileName + ".txt");
        System.out.println(newFile.getAbsolutePath());
        if(newFile.delete()){
            System.out.println("Deleted File");
        }
        else {
            System.out.println("Failed to delete");
        }
    }


    //Requires: Nothing
    //Modifies: Nothing
    //Effects: Plays the video and audio

    public void playVideo(MouseEvent mouseEvent) {
        String path = "C:\\Users\\zheng\\IdeaProjects\\ProjectTwo\\src\\sample\\Garden_Video.mp4";
        Media media = new Media(new File(path).toURI().toString());

        // Create the player and set to play automatically.
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        // Create the view and add it to the Scene.
        MediaView mediaView = new MediaView(mediaPlayer);
        Group newRoot = new Group();
        newRoot.getChildren().add(mediaView);
        mediaPlayer.play();
    }
}
