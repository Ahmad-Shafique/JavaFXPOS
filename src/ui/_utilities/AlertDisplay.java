package ui._utilities;

import javafx.scene.control.Alert;

public class AlertDisplay {
    public AlertDisplay(){

    }
    public void display(String title, String headerText, String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}
