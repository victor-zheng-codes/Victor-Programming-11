package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.util.ArrayList;

public class Controller {
    public TextField nameInput;
    public TextField ageInput;
    public TextField gradeInput;
    public TextField skillsInput;
    public TextField traitsInput;
    public Label outputName;
    public Label outputAge;
    public Label outputGrade;
    public Label outputSkills;
    public Label outputTraits;
    public ListView<Friend> viewFriend = new ListView<>();
    public Button buttonRemoveFriend;
    public TextField newListName;
    public ListView availableLists;
    public Button buttonRemoveList;
    public Button buttonViewList;
    public Button buttonLoadLists;
    public Label errorLabel;
    //public Label saveLabel;
    public Button saveToFileButton;
    public Button buildFriendButton;
    public Label saveToFileLabel;


    public void buildFriend(ActionEvent actionEvent) {
        viewFriend.getItems().clear();
        String name = nameInput.getText();
        int age = Integer.parseInt(ageInput.getText());
        int grade = Integer.parseInt(gradeInput.getText());
        String skills = skillsInput.getText();
        String traits = traitsInput.getText();

        Friend newFriend = new Friend(name, age, grade, skills, traits);
        viewFriend.getItems().add(newFriend);

        nameInput.clear();
        ageInput.clear();
        gradeInput.clear();
        skillsInput.clear();
        traitsInput.clear();
        saveToFileButton.setVisible(true);
        buildFriendButton.setVisible(false);
        saveToFileLabel.setText("Please select a list and press save.");
        saveToFileButton.setDisable(true);

        nameInput.setDisable(true);
        ageInput.setDisable(true);
        gradeInput.setDisable(true);
        skillsInput.setDisable(true);
        traitsInput.setDisable(true);

    }

    public void displayFriend(MouseEvent mouseEvent) {
        Friend newFriend;
        newFriend = viewFriend.getSelectionModel().getSelectedItem();
        outputName.setText(newFriend.getName());
        outputAge.setText(Integer.toString(newFriend.getAge()));
        outputGrade.setText(Integer.toString(newFriend.getGrade()));
        outputSkills.setText(newFriend.getSkills());
        outputTraits.setText(newFriend.getTraits());
        buttonRemoveFriend.setDisable(false);
        buttonRemoveFriend.setVisible(true);


    }

    public void removeFriend(ActionEvent actionEvent) throws IOException{
        Friend newFriend;
        newFriend = viewFriend.getSelectionModel().getSelectedItem();
        viewFriend.getItems().remove(newFriend);
        outputName.setText("");
        outputAge.setText("");
        outputGrade.setText("");
        outputSkills.setText("");
        outputTraits.setText("");
        buttonRemoveFriend.setDisable(true);
        buttonRemoveFriend.setVisible(false);
        //saveToFileButton.setDisable(false);

        ObservableList<Friend> friendList = viewFriend.getItems();
        int size = friendList.size();
        Object listName = availableLists.getSelectionModel().getSelectedItem();

        //Clear the file first

        FileWriter fileWrite = new FileWriter(listName+".txt", false);
        BufferedWriter bufferWrite = new BufferedWriter(fileWrite);
        bufferWrite.write("");
        bufferWrite.close();

        System.out.println("saving to file: " + listName);
        //viewFriend.getItems().clear();
        for(Friend i : friendList) {
            System.out.println("writing this: " + i);
            //if(i.equals(newFriend)){
                //System.out.println("Removed: " + i);
                //viewFriend.getItems().remove(i);
            i.writeToFile(listName + ".txt");

        }

    }


    public void createNewList(ActionEvent actionEvent) throws IOException {
        viewFriend.getItems().clear();

        String newItem = (newListName.getText());
        if (newItem.equals("")) {
            System.out.println("Input new list is nothing");
            errorLabel.setText("Error: Input is nothing");
        }
        else if(compareLists(newItem)){
            System.out.println("CompareList New Item True");
            errorLabel.setText("Error: List already in set");
        }
        else {
            System.out.println("Compare List is false");
            availableLists.getItems().add(newItem);
            newListName.clear();
            //errorLabel.setText("");
            //saveLabel.setText("<--- Please save your \r new file");
            //saveToFileButton.setDisable(false);
            FileWriter newFile = new FileWriter(newItem+".txt");
            BufferedWriter bw = new BufferedWriter(newFile);
            bw.close();

            System.out.println("Writing to friendLists.txt");
            FileWriter fwFriends = new FileWriter("friendLists.txt", true);
            BufferedWriter bwFriends = new BufferedWriter(fwFriends);
            bwFriends.write((String) newItem + "\r");
            bwFriends.close();
            //errorLabel.setText("Saved!");
            errorLabel.setText("");
        }
    }

    public void saveToFile(ActionEvent actionEvent) throws IOException {
        saveToFileLabel.setText("");

        System.out.println("Running saving to file");
        ObservableList<Friend> friendList = viewFriend.getItems();
        int size = friendList.size();
        Object listName = availableLists.getSelectionModel().getSelectedItem();
        System.out.println("saving to file: " + listName);

        for(Friend i : friendList) {
            System.out.println("writing this: " + i);
            i.writeToFile(listName + ".txt");
        }
        viewFriend.getItems().clear();

        saveToFileButton.setVisible(false);
        buildFriendButton.setDisable(true);
        buildFriendButton.setVisible(true);
        nameInput.setDisable(false);
        ageInput.setDisable(false);
        gradeInput.setDisable(false);
        skillsInput.setDisable(false);
        traitsInput.setDisable(false);

    }

    public void loadList(ActionEvent actionEvent) throws IOException {
        System.out.println("Loading list");
        viewFriend.getItems().clear();

        System.out.println("updating new load");
        FileReader fr = new FileReader("friendLists.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("line is: " + line);
            availableLists.getItems().add(line);
        }
        br.close();
        buttonLoadLists.setVisible(false);
        buttonViewList.setVisible(true);
        buttonViewList.setDisable(true);
    }

    public void selectedFriendList(MouseEvent mouseEvent) {
        buttonViewList.setDisable(false);
        buttonRemoveList.setDisable(false);
        //saveLabel.setText("Press Save");
        saveToFileButton.setDisable(false);
    }
    public void removeList(ActionEvent actionEvent) throws IOException {
        File inputFile = new File ("friendLists.txt");
        File tempFile = new File ("tempFile.txt");

        Object locate = availableLists.getSelectionModel().getSelectedItem();
        availableLists.getItems().remove(locate);
        System.out.println("Removing this: " + locate);

        FileReader fr = new FileReader(inputFile);
        BufferedReader br = new BufferedReader(fr);
        String line;

        //Write to a temporary file that we can copy back to later
        FileWriter fileWrite = new FileWriter(tempFile);
        BufferedWriter bufferWrite = new BufferedWriter(fileWrite);

        while ((line = br.readLine()) != null) {
            System.out.println("line located: " + line + " located: " + locate);
            //When we locate the line, we can delete this from the list
            if(line.equals(locate)){
                System.out.println("line located: " + line + " located: " + locate);
                System.out.println("line is: " + line);
                availableLists.getItems().remove(line);

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

        outputName.setText("");
        outputAge.setText("");
        outputGrade.setText("");
        outputSkills.setText("");
        outputTraits.setText("");
        buttonRemoveFriend.setDisable(true);
        buttonRemoveFriend.setVisible(false);
    }

    //This somehow doesn't work
    public void renameFile(){
        File destFile = new File("stupid.txt");
        File origFile = new File("friendsLists.txt");
        boolean delete = destFile.delete();
        //boolean delete1 = origFile.delete();
        boolean success1 = origFile.renameTo(destFile);

        System.out.println(success1);
    }

    //Requires: String
    //Modifies: File friendLists.txt
    //Effects: Checks if the newListName is a duplicate in the File
    public boolean compareLists(String newListName) throws IOException{
        FileReader fr = new FileReader("friendLists.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;

        while((line = br.readLine()) != null) {
            if(line.equals(newListName)){
                System.out.println("List name is duplicate");
                return true;
            }
        }
        return false;

    }
    //Requires: nothing
    //Modifies: File friendLists and File tempFile
    //Effects: reads tempFile.txt and copies it to the friendLists.txt.
    public void copyFile() throws IOException {
        FileWriter fileWrite = new FileWriter("friendLists.txt");
        BufferedWriter bufferWrite = new BufferedWriter(fileWrite);

        FileReader frtemp = new FileReader("tempFile.txt");
        BufferedReader brtemp = new BufferedReader(frtemp);

        String line;

        while ((line = brtemp.readLine()) != null) {
            bufferWrite.write(line + "\r");
        }
        bufferWrite.close();
    }

    public void viewList(ActionEvent actionEvent) throws IOException {
        viewFriend.getItems().clear();
        outputName.setText("");
        outputAge.setText("");
        outputGrade.setText("");
        outputSkills.setText("");
        outputTraits.setText("");
        buttonRemoveFriend.setDisable(true);
        buttonRemoveFriend.setVisible(false);

        System.out.println("Viewing friends running");
        System.out.println("Searching through: " + availableLists.getSelectionModel().getSelectedItem() + ".txt");
        ArrayList<Friend> friends = LoadFriends.viewFriends(availableLists.getSelectionModel().getSelectedItem() + ".txt");
        viewFriend.getItems().clear();

        for (Friend f : friends) {
            System.out.println(f);
            viewFriend.getItems().add(f);
        }
        //viewFriend.getItems().clear();
    }
}
