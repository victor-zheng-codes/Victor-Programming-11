package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.*;
// Used java.nio.file.Files and java.nio.file.Paths as an alternative to fileReader and bufferReader
import java.nio.file.Files;
import java.nio.file.Paths;
// Need to get LocalDate to use date picker
import java.time.LocalDate;

public class Controller {
    public TextField notesOnPlant;
    public TextField plantName;
    public DatePicker harvestEstimate;
    public DatePicker startDate;

    public Label plantNameShow;
    public Label growDateShow;
    public Label notesShow;
    public Label plantNameShowHarvest;
    public Label harvestDateShow;
    public Label notesShowHarvest;
    public HBox harvestScheduleView;
    public HBox growScheduleView;
    public ListView<String> harvestAlerts = new ListView<>();
    public ListView<String> growAlerts = new ListView<>();
    public Button createPlantButton;
    public ListView<String> growScheduleList = new ListView<>();
    public ListView<String> harvestScheduleList = new ListView<>();
    public Button removePlantGrowButton;
    public Button removePlantHarvestButton;
    public Button loadFromFileButton;
    public Button mediaView;
    public ListView quickCreateListView;
    public Label createPlantLabel;
    public VBox createVBox;
    public Label notesShowExtra;
    public Label notesShowHarvestExtra;
    public Button permanentlyDeleteButtonHarvest;
    public Button permanentlyDeleteButtonGrow;

    //Requires: Nothing
    //Modifies: labels in the GUI
    //Effects: clears the text fields that are for creating a new plant
    public void clearCreate(){
        plantName.clear();
        startDate.getEditor().clear();
        harvestEstimate.getEditor().clear();
        notesOnPlant.clear();
    }
    //Requires: nothing
    //Modifies: labels in the GUI
    //Effects: clears the labels for grow
    public void clearGrow(){
        plantNameShow.setText("");
        growDateShow.setText("");
        notesShow.setText("");
        notesShowExtra.setText("");
    }
    //Requires: nothing
    //Modifies: labels in the GUI
    //Effects: clears the labels for grow
    public void clearHarvest(){
        plantNameShowHarvest.setText("");
        harvestDateShow.setText("");
        notesShowHarvest.setText("");
        notesShowHarvestExtra.setText("");
    }
    //Requires: nothing
    //Modifies: labels in GUI, text files growList.txt, harvestList.txt, and the file for each plant
    //Effects: creates a new plant and saves it to file, also runs the InitiatePlant class
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
        //If statement to determine if user has not entered the name, either dates, or notes.
        else if(plantName.getText().isEmpty() || startDate.getValue() == null || harvestEstimate.getValue() == null || notesOnPlant.getText().isEmpty()){
            System.out.println("Missing an input");
            // Set the createPlantLabel to tell user that they are missing a label
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
            LocalDate plantStartDate = startDate.getValue(); // Local date is given with a datePicker
            LocalDate harvestDate = harvestEstimate.getValue();
            String notes = notesOnPlant.getText();
            // Create a new plant by running InitiatePlant class
            InitiatePlant newPlant = new InitiatePlant(name, plantStartDate.toString(), harvestDate.toString(), notes);
            System.out.println(newPlant);

            // Add the new plant to the grow list and harvest list ListViews
            growScheduleList.getItems().add(newPlant.getName());
            harvestScheduleList.getItems().add(newPlant.getName());

            growAlerts.getItems().add(newPlant.getName() + " -- " + newPlant.getStartDate());
            harvestAlerts.getItems().add(newPlant.getName() + " -- " + newPlant.getHarvestDate());

            // Write this new plant to file
            System.out.println("writing this: " + locateName);
            // First, write to a new file named after the plant with the name, grow date, harvest date, and notes.
            newPlant.writeToFile(newPlant + ".txt");
            // Second, write the new plant to the grow list
            newPlant.writeToGrowList();
            // Thirdly, write the new plant to the harvest list
            newPlant.writeToHarvestList();
            //Clear the create fields for the next plant
            clearCreate();
        }
    }
    //Requires: String
    //Modifies: File friendLists.txt
    //Effects: Checks if the newListName is a duplicate in the File
    public boolean compareLists(String newListName, String fileName) throws IOException{
        // Start a new fileReader to read the specified fileName
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null) {
            // Determine if any line is a duplicate, then return true.
            if(line.equals(newListName)){
                System.out.println("List name is duplicate");
                br.close();
                return true;
            }
        }
        br.close();
        // Else, return false
        return false;
    }
    //Requires: Nothing
    //Modifies: Nothing
    //Effects: read each file and write out to the list
    public void viewGrowList(MouseEvent mouseEvent) throws IOException{
        //Clear the labels so it starts off on a blank slate for the next user
        clearGrow();
        //Enable the remove button for the grow page
        removePlantGrowButton.setVisible(true);
        permanentlyDeleteButtonGrow.setVisible(true);

        // Get the name of the selected item
        String selected = growScheduleList.getSelectionModel().getSelectedItem();
        // Start a fileReader for the selected file
        FileReader fr = new FileReader("Plants\\" + selected + ".txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        int counter = 0;
        int spaceFinding = 0;

        // Read through each line in the file
        while ((line = br.readLine()) != null) {
            counter ++; // Add one to the counter for each iteration
            System.out.println(counter);
            System.out.println("line is: " + line);
            // If the counter equals 1, then display the plant name in the text label
            if(counter == 1){
                // Create a string that does not look at the comma at the end of the line
                String insert = line.substring(0,line.length()-1);
                plantNameShow.setText(insert);
            }
            //Find the grow date when/if the counter equals 2
            if(counter == 2){
                // Create a string that does not look at the comma at the end of the line
                String insert = line.substring(0,line.length()-1);
                growDateShow.setText(insert);
            }
            /*Determine the notes on the plant. This section may be long, so I
            created an approach that finds the first 3 spaces, and creates a new line if there is less than 3 spaces.
            This ensures better separation for each plant, and prevents a line from being cut out.*/
            if(counter == 4) { // Determine when the counter == 4, then we have arrived at the beginning of the notes section
                // Determine the length of the line, and add 1 each time a space is found
                for (int i = 0; i < line.length(); i++) {
                    if (line.substring(i, i + 1).equals(" ")) {
                        spaceFinding++;
                        // If space finding == 3, then the notes will probably not be able to fit on one line, so we go to the next line.
                        if (spaceFinding == 3) {
                            // Determine when there are 3 spaces. Then, set the label to the beginning of the line to i
                            notesShow.setText(line.substring(0, i));
                            // The next label would be set from i to the length - 1 for the comma
                            notesShowExtra.setText(line.substring(i, line.length() - 1));
                            break;
                        }
                        // if there are not 3 spaces yet, then write to the first line
                        else {
                            // Else, we set only one label. -1 because we do not want to include the comma
                            notesShow.setText(line.substring(0, line.length() - 1));
                        }
                    }
                    //Happens only if there are no spaces at all in the notes
                    else{
                        // Else, we set only one label. -1 because we do not want to include the commanotesShow.setText(line.substring(0, line.length() - 1));
                        notesShow.setText(line.substring(0, line.length() - 1));
                    }
                }
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
        //Enable the remove buttons on the harvest page
        removePlantHarvestButton.setVisible(true);
        permanentlyDeleteButtonHarvest.setVisible(true);
        // Determine the name of the selected line in the ListView
        String selected = harvestScheduleList.getSelectionModel().getSelectedItem();
        // Start a file reader for the selected text
        FileReader fr = new FileReader("Plants\\" + selected + ".txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        int counter = 0;
        int spaceFinding = 0;
        // Read through each line in the file
        while ((line = br.readLine()) != null) {
            // Add one to the counter to determine which line we are at
            counter ++;
            System.out.println(counter);
            System.out.println("line is: " + line);
            // If line equals 1, then this is the plant Name, and we can simply set the text as the line
            if(counter == 1){
                String insert = line.substring(0,line.length()-1);
                plantNameShowHarvest.setText(insert);
            }
            //We have skipped the grow list, and gone to the third line, which should be the harvest date
            if(counter == 3){
                // insert the current line into the harvestDateShow label
                String insert = line.substring(0,line.length()-1);
                harvestDateShow.setText(insert);
            }
            /*Determine the notes on the plant. This section may be long, so I
            created an approach that finds the first 3 spaces, and creates a new line if there is less than 3 spaces.
            This ensures better separation for each plant, and prevents a line from being cut out.*/
            if(counter == 4) { // Determine when the counter == 4, then we have arrived at the beginning of the notes section
                // Determine the length of the line, and add 1 each time a space is found
                for (int i = 0; i < line.length(); i++) {
                    if (line.substring(i, i + 1).equals(" ")) {
                        spaceFinding++;
                        // If space finding == 3, then the notes will probably not be able to fit on one line, so we go to the next line.
                        if (spaceFinding == 3) {
                            // Determine when there are 3 spaces. Then, set the label to the beginning of the line to i
                            notesShowHarvest.setText(line.substring(0, i));
                            // The next label would be set from i to the length - 1 for the comma
                            notesShowHarvestExtra.setText(line.substring(i, line.length() - 1));
                            break;
                        }
                        // if there are not 3 spaces yet, then write to the first line
                        else {
                            // Else, we set only one label. -1 because we do not want to include the comma
                            notesShowHarvest.setText(line.substring(0, line.length() - 1));
                        }
                    }
                    // Happens only if there are no spaces at all in the notes
                    else{
                        //Else, we set only one label. -1 because we do not want to include the comma
                        notesShowHarvest.setText(line.substring(0, line.length() - 1));
                    }
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

        /* --------------- FOR LOADING GROW LIST --------------------- */
        //Read the grow list and update the schedule and alerts
        System.out.println("updating new load");
        FileReader fr = new FileReader("growList.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        // Read through each line in the growList.txt
        while ((line = br.readLine()) != null) {
            System.out.println("line is: " + line);
            // Add each plant name in the grow list to the growSchedule list.
            growScheduleList.getItems().add(line);

            /* Also need to add the name and date to the alerts. Googled a way to find a line from a file.
            I know that I should probably use fileReader because we had learned about it in class.
            But I felt that this was an interesting method of doing a similar thing
             */
            String growDate = Files.readAllLines(Paths.get("Plants\\"+ line + ".txt")).get(1);
            //Display only the name, with 2 dashes, and the date. Date is found with the length - the comma at the end
            growAlerts.getItems().add(line + " -- " + growDate.substring(0, growDate.length()-1));
        }
        br.close();

        /* --------------- FOR LOADING HARVEST LIST --------------------- */
        //Read the harvest list and update the schedule and alerts
        FileReader harvestfr = new FileReader("harvestList.txt");
        BufferedReader harvestbr = new BufferedReader(harvestfr);
        String harvestLine;
        while ((harvestLine = harvestbr.readLine()) != null) {
            System.out.println("harvestList line is: " + harvestLine);
            harvestScheduleList.getItems().add(harvestLine);

            /* Also need to add the name and date to the alerts. Googled a way to find a line from a file.
            I know that I should probably use fileReader because we had learned about it in class.
            But I felt that this was an interesting method of doing a similar thing
             */
            String harvestDate = Files.readAllLines(Paths.get("Plants\\"+ harvestLine + ".txt")).get(2);
            //Display only the name, with 2 dashes, and the date. Date is found with the length - the comma at the end
            harvestAlerts.getItems().add(harvestLine + " -- " + harvestDate.substring(0, harvestDate.length()-1));
        }
        harvestbr.close();

        /* --------------- FOR LOADING POSSIBILITIES FOR QUICK CREATE --------------------- */
        FileReader possibilitiesFr = new FileReader("OtherFiles\\possibilitiesList.txt");
        BufferedReader possibilitiesBr = new BufferedReader(possibilitiesFr);
        String possibilities;
        while ((possibilities = possibilitiesBr.readLine()) != null) {
            System.out.println("possibilities line is: " + possibilities);
            quickCreateListView.getItems().add(possibilities);
        }
        possibilitiesBr.close();
    }
    //Requires: Use of the Video class
    //Modifies: Nothing
    //Effects: Plays the demonstration video and audio.
    public void playDemonstrationVideo(MouseEvent mouseEvent) {
        System.out.println("Playing demonstration video");
        Video.playDemonstrationVideo();
    }
    //Requires: Use of the Video class
    //Modifies: Nothing
    //Effects: Plays the explanation video and audio.
    public void playCodeExplanationVideo(MouseEvent mouseEvent) {
        System.out.println("Playing Explanation video");
        Video.playCodeExplanationVideo();
    }
    //Requires: Nothing
    //Modifies: Nothing
    //Effects: Inserts the predefined plants for easy usage
    public void quickCreate(MouseEvent mouseEvent) throws IOException, InterruptedException {
        System.out.println("Quick Create Running");

        // Get the name of the selected
        String name = quickCreateListView.getSelectionModel().getSelectedItem().toString();

        // Determine if this plant is already in either the harvest or grow lists by running the compareLists method
        if(compareLists(name,"harvestList.txt") || compareLists(name, "growList.txt")){
            // Clear the creation fields for a new plant creation
            System.out.println("Plant already in a list");
            // Tell the user that the plant is already in a list
            createPlantLabel.setText("Plant already in a list");
        }
        // Prevent user from selecting the plant twice in a row. Found that there are weird errors if the dates are the same.
        else if(name.equals(plantName.getText())){
            createPlantLabel.setText("Already Selected Plant");
        }
        else{
            // Clear the creation fields for the next plant creation
            clearCreate();
            // Else, this creation should be valid, and we can clear the label by setting it to a space
            createPlantLabel.setText("");
            // Create a fileReader that reads the plant from veggie and fruit possibilities folder
            FileReader frTemp = new FileReader("veggieFruitPossibilities\\" + name + ".txt");
            BufferedReader brTemp = new BufferedReader(frTemp);
            int counter = 0;
            String line;
            // Go through each line, setting the text fields, datePickers, and notes
            while ((line = brTemp.readLine()) != null) {
                System.out.println(line);
                counter ++;
                System.out.println("counter" + counter);
                // The first line would be the name
                if(counter == 1){
                    // Create a string that does not look at the comma at the end of the line
                    String insert = line.substring(0,line.length()-1);
                    // Set the label to the plant name
                    plantName.setText(insert);
                }
                // Second line is the grow date
                if(counter == 2){
                    // Create a string that does not look at the comma at the end of the line
                    String insert = line.substring(0,line.length()-1);
                    // Convert the string to a local date
                    LocalDate localDate = LocalDate.parse(insert);
                    // set the label to the grow date
                    startDate.setValue(localDate);
                }
                // Third line is the harvest date
                if(counter == 3){
                    // Create a string that does not look at the comma at the end of the line
                    String insert = line.substring(0,line.length()-1);
                    // Convert the string to a local date
                    LocalDate localDate = LocalDate.parse(insert);
                    // set the label to the harvest date
                    harvestEstimate.setValue(localDate);
                }
                // Fourth line is the notes line
                if(counter == 4){
                    // Create a string that does not look at the comma at the end of the line
                    String insert = line.substring(0,line.length()-1);
                    // set the notes label to the notes line minus the comma
                    notesOnPlant.setText(insert);
                }
            }
        }
    }
    //Requires: use of the RemovePlant class
    //Modifies: File growList and File tempFile
    //Effects: removes a  plant from the growList, by writing to a tempFile and then copying the file over to the growList
    public void removePlantGrow(MouseEvent mouseEvent) throws IOException{
        String plantName = growScheduleList.getSelectionModel().getSelectedItem();
        growScheduleList.getItems().remove(plantName);
        // Remove plant by using class:
        RemovePlant.removePlant("growList.txt", plantName);
        // Clear the grow labels
        clearGrow();
        // Run the remove alert method, making sure to only write growDelete as true
        removeAlert(plantName, true, false);
        // Run the removePermanentlyCheck method from the RemovePlant class to determine if file should be permanently deleted.
        RemovePlant.removePermanentlyCheck(plantName);

    }
    //Requires: use of the RemovePlant class
    //Modifies: File harvestList and File tempFile
    //Effects: removes a  plant from the harvestList, by writing to a tempFile and then copying the file over to the harvestList
    public void removePlantHarvest(MouseEvent mouseEvent) throws IOException{
        String plantName = harvestScheduleList.getSelectionModel().getSelectedItem();
        harvestScheduleList.getItems().remove(plantName);
        RemovePlant.removePlant("harvestList.txt", plantName);
        // clear the harvest labels
        clearHarvest();
        // Run the remove alert method, making sure to only write harvestDelete as true
        removeAlert(plantName, false, true);

        // Run the removePermanentlyCheck method from the RemovePlant class to determine if file should be permanently deleted.
        RemovePlant.removePermanentlyCheck(plantName);

    }
    //Requires: use of RemovePlant class
    //Modifies: nothing
    //Effects: Deletes selected file from grow listView, harvest listview, removes from alerts, and from file
    public void permanentlyDeleteViaGrow(MouseEvent mouseEvent) throws IOException{
        //Delete from both the grow and harvest ListViews
        String plantName = growScheduleList.getSelectionModel().getSelectedItem();
        harvestScheduleList.getItems().remove(plantName);
        growScheduleList.getItems().remove(plantName);

        // run the remove alert method, making sure to delete both the grow and harvest alerts
        removeAlert(plantName, true, true);
        // Delete the file from the project/from file
        File newFile = new File("Plants\\", plantName + ".txt");

        if(newFile.delete()){
            System.out.println("removed file: " + plantName);
        }else{
            System.out.println("ERROR NOT REMOVED");
        }
        // Remove the plant from the growList
        RemovePlant.removePlant("growList.txt", plantName);

        //Try to remove the name from the harvest list too, we do not know if it is there
        try {
            RemovePlant.removePlant("harvestList.txt", plantName);
            System.out.println("Removed from harvestList.txt");
        } catch (Exception e) { // This will occur when the plant has already been removed from the harvestList
            System.out.println("Did not remove from harvestList.txt, likely already removed");
        }
    }
    //Requires: nothing
    //Modifies: nothing
    //Effects: Deletes selected file from harvest listView
    public void permanentlyDeleteViaHarvest(MouseEvent mouseEvent) throws IOException{
        //Delete from both the grow and harvest ListViews
        String plantName = harvestScheduleList.getSelectionModel().getSelectedItem();
        harvestScheduleList.getItems().remove(plantName);
        growScheduleList.getItems().remove(plantName);

        // run the remove alert method, making sure to delete both the grow and harvest alerts
        removeAlert(plantName, true, true);
        // Delete the file from the project/from file
        File newFile = new File("Plants\\", plantName + ".txt");
        if(newFile.delete()) {
            System.out.println("removed file: " + plantName);
        }else{
            System.out.println("ERROR NOT REMOVED");
        }
        // Remove the plant from the harvestList
        RemovePlant.removePlant("harvestList.txt", plantName);

        //Try to remove the name from the grow list too, we do not know if it is there
        try {
            RemovePlant.removePlant("OtherFiles\\growList.txt", plantName);
            System.out.println("Removed from growList.txt");
        }catch (Exception e){ // This will occur when the plant has already been removed from the growList
            System.out.println("Did not remove from growList.txt, likely already removed");
        }
    }
    //Requires: String of the plant, Boolean for both grow and harvest to determine if should delete
    //Modifies: ListView of either grow or harvest alerts
    //Effects: Deletes selected alert from either grow or delete
    public void removeAlert(String plantName, Boolean growDelete, Boolean harvestDelete) throws IOException {
        System.out.println("Grow, Harvest remove: " + growDelete + harvestDelete);
        // Start a new fileReader for the plant that needs to be removed.
        FileReader fr = new FileReader("Plants\\" + plantName + ".txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        int counter = 0;

        // Since alerts have both the name, and either the grow or harvest date, we need to loop through each line.
        while ((line = br.readLine()) != null) {
            counter++;
            // When counter == 2, this line is the grow date.
            if (counter == 2 && growDelete){ // Run this only if growDelete Boolean is true
                // Try removing from grow list, as we are not certain if it has already been removed
                try {
                    line = line.substring(0, line.length() - 1);
                    growAlerts.getItems().remove(plantName + " -- " + line);
                    System.out.println("removed grow alert");
                } catch (Exception e) { // occurs when this plant has already been removed when deleting only the grow list of the plant.
                    System.out.println("Did not remove from grow alert because already removed");
                }
            }
            // When counter == 3, this line is the harvest date.
            if (counter == 3 && harvestDelete) { // Run this only if harvestDelete Boolean is true
                // Try removing from grow list, as we are not certain if it has already been removed
                try {
                    line = line.substring(0, line.length() - 1);
                    harvestAlerts.getItems().remove(plantName + " -- " + line);
                    System.out.println("removed harvest alert");
                } catch (Exception e) { // occurs when this plant has already been removed when deleting only the harvest list of the plant.
                    System.out.println("Did not remove from grow alert because already removed");
                }
            }
        }
        br.close();
    }
}
