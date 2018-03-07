package ui.controllers.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui._utilities.WindowChange;

public class LoginController {

    @FXML
    public void loginButtonClick(ActionEvent event) throws  Exception{
        // System.out.println("<<<<<<<<<<<<<<<<<<<<<<<   Login button clicked  >>>>>>>>>>>>>>>>>>>>>>");
        WindowChange.Activate(event,"../display/fxml/user/dashboard.fxml", "User - Dashboard");
    }

}
