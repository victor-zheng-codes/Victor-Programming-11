package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public Button removePlantGrowButton;
    public Button removePlantHarvestButton;
    public Button loadFromFileButton;
    public Button mediaView;
    public Label howToUseLabel;
    public ListView quickCreateListView;
    public Label createPlantLabel;
    public VBox createVBox;
    public Label notesShowExtra;
    public Label notesShowHarvestExtra;
    public Button permanentlyDeleteButtonHarvest;
    public Button permanentlyDeleteButtonGrow;

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
        notesShowExtra.setText("");
    }

    //Requires: Nothing
    //Modifies: nothing
    //Effects: clears the labels for grow
    public void clearHarvest(){
        plantNameShowHarvest.setText("");
        harvestDateShow.setText("");
        notesShowHarvest.setText("");
        notesShowHarvestExtra.setText("");
    }

    //Requires: nothing
    //Modifies: this
    //Effects: creates a new plant and saves it to file
    public void createPlant(ActionEvent actionEvent) throws IOException{
        String name = plantName.getText();

        //Determine if the new plant is already in either list
        //If true, then display the label telling user that there is a duplicate
        if(compareLists(name,"harvestList.txt") || compareLists(name, "growList.txt")){
            System.out.println("Plant already in a list");
            createPlantLabel.setText("Plant already in a list");
            //Use Color library to find a green colour for the label
            createPlantLabel.setTextFill(Color.web("#FFC300"));

        }

        //If statement to determine if user has not entered the name, or either dates, or notes.
        else if(plantName.getText().isEmpty() || startDate.getValue() == null || harvestEstimate.getValue() == null || notesOnPlant.getText().isEmpty()){
            System.out.println("Missing an input");
            createPlantLabel.setTextFill(Color.web("#FF00FB"));
            createPlantLabel.setText("Missing an input");
        }

        else{
            createPlantLabel.setText("");
            //Disable the quick create list view for creation of more plants
            quickCreateListView.setDisable(false);
            Object locateName = quickCreateListView.getSelectionModel().getSelectedItem();
            //Remove the quick create object from GUI.
            quickCreateListView.getItems().remove(locateName);

            //Get the rest of the info provided: start date, harvest date, and notes on the plant
            LocalDate plantStartDate = startDate.getValue();
            LocalDate harvestDate = harvestEstimate.getValue();
            String notes = notesOnPlant.getText();

            Plant newPlant = new Plant(name, plantStartDate.toString(), harvestDate.toString(), notes);
            System.out.println(newPlant);

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
                i.writeToFile("C:\\Users\\zheng\\IdeaProjects\\ProjectTwo\\Plants\\" + newPlant + ".txt", false);
                i.writeToGrowList();
                i.writeToHarvestList();
            }
        }

    }

    //Requires: String
    //Modifies: File friendLists.txt
    //Effects: Checks if the newListName is a duplicate in the File
    public boolean compareLists(String newListName, String fileName) throws IOException{
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String line;

        while((line = br.readLine()) != null) {
            if(line.equals(newListName)){
                System.out.println("List name is duplicate");
                br.close();
                return true;
            }
        }
        br.close();
        return false;

    }


    //Requires: Nothing
    //Modifies: Nothing
    //Effects: read each file and write out to the list
    public void viewGrowList(MouseEvent mouseEvent) throws IOException{

        //Enable the remove button for the grow page
        removePlantGrowButton.setVisible(true);
        permanentlyDeleteButtonGrow.setVisible(true);

        //Clear the labels so it starts off on a blank slate
        clearGrow();
        String selected = growScheduleList.getSelectionModel().getSelectedItem();
        FileReader fr = new FileReader("C:\\Users\\zheng\\IdeaProjects\\ProjectTwo\\Plants\\" + selected + ".txt");
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
            //Created an approach that finds the first 3 spaces, and creates a new line if there is less than 3 spaces.
            //Ensures better separation for each plant
            int countTotal = 0;
            int spaceFinding = 0;
            if(counter == 4) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.substring(i, i + 1).equals(" ")) {
                        spaceFinding++;
                        if (spaceFinding == 3) {
                            notesShow.setText(line.substring(0, countTotal));
                            notesShowExtra.setText(line.substring(countTotal, line.length() - 1));
                            break;
                        }
                        else{
                            notesShow.setText(line.substring(0,line.length()-1));
                        }
                    }
                    countTotal++;
                }
            }
        }
        br.close();
    }

    //Requires: Nothing
    //Modifies: Nothing
    //Effects: read each file and write out to the list
    public void viewHarvestList(MouseEvent mouseEvent) throws IOException{
        //Enable the remove buttons on the harvest page
        removePlantHarvestButton.setVisible(true);
        permanentlyDeleteButtonHarvest.setVisible(true);

        //Clear the labels so it starts off on a blank slate
        clearHarvest();
        String selected = harvestScheduleList.getSelectionModel().getSelectedItem();
        FileReader fr = new FileReader("C:\\Users\\zheng\\IdeaProjects\\ProjectTwo\\Plants\\"+ selected + ".txt");
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
            //The notes lines
            int countTotal = 0;
            int spaceFinding = 0;
            if(counter == 4) {
                for (int i = 0; i < line.length(); i++) {
                    if(line.substring(i,i+1).equals(" ")){
                        spaceFinding ++;
                        if(spaceFinding == 3){
                            notesShowHarvest.setText(line.substring(0,countTotal));
                            notesShowHarvestExtra.setText(line.substring(countTotal, line.length()-1));
                            break;
                        }
                        else{
                            notesShowHarvest.setText(line.substring(0,line.length()-1));
                        }
                    }
                    countTotal ++;
                }
            }
        }
        br.close();

    }
    //Requires: Nothing
    //Modifies: Nothing
    //Effects: loads the harvestList and growList
    public void loadFromFile(MouseEvent mouseEvent) throws IOException {
        System.out.println("Loading list");

        //Enable the V Box that enables user to create new plants
        createVBox.setDisable(false);
        //Disable the load from file button
        loadFromFileButton.setDisable(true);
        //Clear the createPlantLabel
        createPlantLabel.setText("");
        //plantsList.getItems().clear();

        //Read the grow list and update the schedule and alerts
        System.out.println("updating new load");
        FileReader fr = new FileReader("growList.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("line is: " + line);
            growScheduleList.getItems().add(line);

            //Googled a way to find a line from a file.
            String growDate = Files.readAllLines(Paths.get("C:\\Users\\zheng\\IdeaProjects\\ProjectTwo\\Plants\\"+ line + ".txt")).get(1);
            //Display only the name, with 2 dashes, and the date. Date is found with the length - the comma at the end
            growAlerts.getItems().add(line + " -- " + growDate.substring(0, growDate.length()-1));
        }
        br.close();

        //Read the harvest list and update the schedule and alerts
        FileReader harvestfr = new FileReader("harvestList.txt");
        BufferedReader harvestbr = new BufferedReader(harvestfr);
        String harvestLine;
        while ((harvestLine = harvestbr.readLine()) != null) {
            System.out.println("harvestList line is: " + harvestLine);
            harvestScheduleList.getItems().add(harvestLine);

            //Googled a way to find a line from a file.
            String harvestDate = Files.readAllLines(Paths.get("C:\\Users\\zheng\\IdeaProjects\\ProjectTwo\\Plants\\"+ harvestLine + ".txt")).get(2);
            //Display only the name, with 2 dashes, and the date. Date is found with the length - the comma at the end
            harvestAlerts.getItems().add(harvestLine + " -- " + harvestDate.substring(0, harvestDate.length()-1));
        }
        harvestbr.close();


        //Load all the possibilities out onto quick create
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
                System.out.println("line not written to temp file is: " + line);
                //growScheduleList.getItems().remove(line);

                /* Not going to remove as this does not remove the harvest list too
                //Remove file from folder
                File file = new File ("C:\\Users\\zheng\\IdeaProjects\\ProjectTwo\\Plants\\" + line + ".txt");
                System.out.println(file.delete());

                 */
            }
            else{
                //Else, we write the file back into the list
                System.out.println("wrote: " + line);
                bufferWrite.write(line + "\r");
            }
        }
        br.close();
        bufferWrite.close();
        copyFile("growList.txt");
        //deleteFile(locateName.toString());
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
                System.out.println("line not written to temp file is: " + line);
                //harvestScheduleList.getItems().remove(line);

                /* Not going to remove as this does not delete the grow list at the same time
                //Remove file from folder
                File file = new File ("C:\\Users\\zheng\\IdeaProjects\\ProjectTwo\\Plants\\" + line + ".txt");
                System.out.println(file.delete());

                 */
            }
            else{
                //Else, we write the file back into the list
                System.out.println("wrote: " + line);
                bufferWrite.write(line + "\r");
            }
        }
        br.close();
        bufferWrite.close();
        copyFile("harvestList.txt");
        //deleteFile(locateName.toString());
        clearHarvest();
    }

    //Requires: nothing
    //Modifies: File input file and File tempFile
    //Effects: reads tempFile.txt and copies it to the inputFile.txt.
    public void copyFile(String inputFile) throws IOException {
        FileWriter fileWrite = new FileWriter(inputFile);
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


    //Requires: Nothing
    //Modifies: Nothing
    //Effects: Plays the video and audio.
    public void playVideo(MouseEvent mouseEvent) {
        System.out.println("Playing demonstration video");
        howToUseLabel.setText("Video has opened in another stage");
        Video.playVideo();

    }

    //Requires: Nothing
    //Modifies: Nothing
    //Effects: Inserts the predefined plants for easy usage
    public void quickCreate(MouseEvent mouseEvent) throws IOException{
        System.out.println("Quick Create Running");
        clearCreate();
        String name = quickCreateListView.getSelectionModel().getSelectedItem().toString();

        if(compareLists(name,"harvestList.txt") || compareLists(name, "growList.txt")){
            System.out.println("Plant already in a list");
            createPlantLabel.setText("Plant already in a list");
        }

        else{
            createPlantLabel.setText("");
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
                if(counter == 2){
                    String insert = line.substring(0,line.length()-1);
                    //LocalDate date = LocalDate.now();
                    LocalDate localDate = LocalDate.parse(insert);

                    startDate.setValue(localDate);
                }
                if(counter == 3){
                    //LocalDate date = LocalDate.of(2020,9,13);
                    String insert = line.substring(0,line.length()-1);

                    LocalDate localDate = LocalDate.parse(insert);
                    harvestEstimate.setValue(localDate);
                }
                if(counter == 4){
                    String insert = line.substring(0,line.length()-1);
                    notesOnPlant.setText(insert);
                }
            }
            //quickCreateListView.setDisable(true);
        }
    }

    //Requires: nothing
    //Modifies: nothing
    //Effects: Deletes selected file from grow listView
    public void permanentlyDeleteViaGrow(MouseEvent mouseEvent) throws IOException{
        //Delete from both the grow and harvest lists
        Object locateName = growScheduleList.getSelectionModel().getSelectedItem();
        harvestScheduleList.getItems().remove(locateName);
        growScheduleList.getItems().remove(locateName);


        String fileName = locateName.toString();

        File newFile = new File("C:\\Users\\zheng\\IdeaProjects\\ProjectTwo\\Plants\\", fileName + ".txt");
        System.out.println(newFile.getAbsolutePath());
        System.out.println(newFile.delete());

    }

    //Requires: nothing
    //Modifies: nothing
    //Effects: Deletes selected file from harvest listView
    public void permanentlyDeleteViaHarvest(MouseEvent mouseEvent) throws IOException{
        //Delete from both the grow and harvest lists
        Object locateName = harvestScheduleList.getSelectionModel().getSelectedItem();
        harvestScheduleList.getItems().remove(locateName);
        growScheduleList.getItems().remove(locateName);

        String fileName = locateName.toString();
        File newFile = new File("C:\\Users\\zheng\\IdeaProjects\\ProjectTwo\\Plants\\", fileName + ".txt");
        System.out.println(newFile.getAbsolutePath());
        System.out.println(newFile.delete());
    }
}
