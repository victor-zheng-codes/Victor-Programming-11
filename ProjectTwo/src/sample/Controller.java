package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class Controller {
    public ListView plantsList;
    public TextArea notesOnPlant;
    public TextField plantName;
    public DatePicker harvestEstimate;
    public DatePicker dateStarted;
    public Label plantNameShow;
    public Label growDateShow;
    public Label notesShow;
    public Label plantNameShowHarvest;
    public Label harvestDateShow;
    public Label notesShowHarvest;
    public HBox harvestScheduleView;
    public HBox growScheduleView;
    public DatePicker startDate;
    public ListView harvestAlerts;
    public ListView growAlerts;
    public Button createPlantButton;
    public ListView growScheduleList;
    public ListView harvestScheduleList;

    public void createPlant(ActionEvent actionEvent) {
    }

    public void viewYourPlants(MouseEvent mouseEvent) {
    }

    public void viewGrowList(MouseEvent mouseEvent) {
    }

    public void viewHarvestList(MouseEvent mouseEvent) {
    }
}
