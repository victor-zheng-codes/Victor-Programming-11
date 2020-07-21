package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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

    public void buildFriend(ActionEvent actionEvent) {
        String name = nameInput.getText();
        int age = Integer.parseInt(ageInput.getText());
        int grade = Integer.parseInt(gradeInput.getText());
        String skills = skillsInput.getText();
        String traits = traitsInput.getText();

        Friend newFriend = new Friend(name,age,grade,skills,traits);
        viewFriend.getItems().add(newFriend);

        nameInput.clear();
        ageInput.clear();
        gradeInput.clear();
        skillsInput.clear();
        traitsInput.clear();
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

    public void removeFriend(ActionEvent actionEvent) {
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

    }
}
