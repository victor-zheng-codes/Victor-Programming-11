package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;

public class Controller {
    public ListView<Plant> plantsList = new ListView<>();
    public TextField notesOnPlant;
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
    public Button mediaView;
    public Label howToUseLabel;
    public ListView quickCreateListView;

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
        quickCreateListView.setDisable(false);
        Object locateName = quickCreateListView.getSelectionModel().getSelectedItem();
        quickCreateListView.getItems().remove(locateName);

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
            i.writeToGrowList();
            i.writeToHarvestList();
        }
    }
    //Requires: Nothing
    //Modifies: Nothing
    //Effects: read each file and write out to the list
    public void viewGrowList(MouseEvent mouseEvent) throws IOException{
        //Clear the labels so it starts off on a blank slate
        clearGrow();
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
                String insert = line.substring(0,line.length()-1);
                plantNameShow.setText(insert);
            }
            //Find the grow date date
            if(counter == 2){
                String insert = line.substring(0,line.length()-1);
                growDateShow.setText(insert);
            }
            //Notes Line
            if(counter == 4) {
                String insert = line.substring(0,line.length()-1);
                notesShow.setText(insert);
            }
        }
        br.close();
    }

    //Requires: Nothing
    //Modifies: Nothing
    //Effects: read each file and write out to the list
    public void viewHarvestList(MouseEvent mouseEvent) throws IOException{
        //Clear the labels so it starts off on a blank slate
        clearHarvest();
        String selected = harvestScheduleList.getSelectionModel().getSelectedItem();
        FileReader fr = new FileReader(selected + ".txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        int counter = 0;

        while ((line = br.readLine()) != null) {
            counter ++;
            System.out.println(counter);
            System.out.println("line is: " + line);

            if(counter == 1){
                String insert = line.substring(0,line.length()-1);
                plantNameShowHarvest.setText(insert);
            }
            //Skip the grow date
            if(counter == 3){
                String insert = line.substring(0,line.length()-1);
                harvestDateShow.setText(insert);
            }
            //The notes line
            if(counter == 4) {
                String insert = line.substring(0,line.length()-1);
                notesShowHarvest.setText(insert);
            }
        }
        br.close();

    }
    //Requires: Nothing
    //Modifies: Nothing
    //Effects: loads the harvestList and growList
    public void loadFromFile(MouseEvent mouseEvent) throws IOException {
        System.out.println("Loading list");
        loadFromFileButton.setDisable(true);
        //plantsList.getItems().clear();

        System.out.println("updating new load");
        FileReader fr = new FileReader("growList.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("line is: " + line);
            growScheduleList.getItems().add(line);
            growAlerts.getItems().add(line);
        }
        br.close();

        FileReader harvestfr = new FileReader("harvestList.txt");
        BufferedReader harvestbr = new BufferedReader(harvestfr);
        String harvestLine;
        while ((harvestLine = harvestbr.readLine()) != null) {
            System.out.println("harvestList line is: " + harvestLine);
            harvestScheduleList.getItems().add(harvestLine);
            harvestAlerts.getItems().add(harvestLine);
        }
        harvestbr.close();


        FileReader possibilitiesFr = new FileReader("C:\\Users\\zheng\\IdeaProjects\\ProjectTwo\\veggieFruitPossibilities\\possibilitiesList.txt");
        BufferedReader possibilitiesBr = new BufferedReader(possibilitiesFr);
        String possibilities;
        while ((possibilities = possibilitiesBr.readLine()) != null) {
            System.out.println("possibilities line is: " + possibilities);
            quickCreateListView.getItems().add(possibilities);
        }
        possibilitiesBr.close();
    }
    //Requires: Nothing
    //Modifies: File growList and File tempFile
    //Effects: removes a  plant from the growList, by writing to a tempFile and then copying the file over to the growList
    public void removePlantGrow(MouseEvent mouseEvent) throws IOException{
        File inputFile = new File ("growList.txt");
        File tempFile = new File ("tempFile.txt");

        Object locateName = growScheduleList.getSelectionModel().getSelectedItem();
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
        copyFile("growList");
        deleteFile(locateName.toString());
        clearGrow();
    }

    //Requires: Nothing
    //Modifies: File harvestList and File tempFile
    //Effects: removes a  plant from the harvestList, by writing to a tempFile and then copying the file over to the harvestList
    public void removePlantHarvest(MouseEvent mouseEvent) throws IOException{
        File inputFile = new File ("harvestList.txt");
        File tempFile = new File ("tempFile.txt");

        Object locateName = harvestScheduleList.getSelectionModel().getSelectedItem();
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
        copyFile("harvestList");
        deleteFile(locateName.toString());
        clearHarvest();
    }

    //Requires: nothing
    //Modifies: File input file and File tempFile
    //Effects: reads tempFile.txt and copies it to the inputFile.txt.
    public void copyFile(String inputFile) throws IOException {
        FileWriter fileWrite = new FileWriter(inputFile + ".txt");
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
        howToUseLabel.setText("Video has opened in another stage");
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

    //Requires: Nothing
    //Modifies: Nothing
    //Effects: Inserts the predefined plants for easy usage
    public void quickCreate(MouseEvent mouseEvent) throws IOException{
        System.out.println("Quick Create Running");
        clearCreate();
        String name = quickCreateListView.getSelectionModel().getSelectedItem().toString();
        FileReader frTemp = new FileReader("C:\\Users\\zheng\\IdeaProjects\\ProjectTwo\\veggieFruitPossibilities\\" + name + ".txt");
        BufferedReader brTemp = new BufferedReader(frTemp);

        int counter = 0;
        String line;

        while ((line = brTemp.readLine()) != null) {
            System.out.println(line);
            counter ++;
            System.out.println("counter" + counter);
            if(counter == 1){
                String insert = line.substring(0,line.length()-1);
                plantName.setText(insert);
            }
            if(counter ==2){
                LocalDate date = LocalDate.now();
                startDate.setValue(date);
            }
            if(counter == 3){
                LocalDate date = LocalDate.of(2020,9,13);
                harvestEstimate.setValue(date);
            }
            if(counter == 4){
                String insert = line.substring(0,line.length()-1);
                notesOnPlant.setText(insert);
            }
        }
        quickCreateListView.setDisable(true);
    }
}
