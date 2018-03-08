package ui.controllers.login;

import core.application.services.Activator;
import core.domain.model.entities.User;
import core.domain.services.interfaces.ICRUD;
import core.domain.services.interfaces.IUserCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ui._utilities.WindowChange;

import java.io.Console;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    IUserCRUD service ;

    @FXML
    private TextField usernameField, passwordField;

    @FXML
    public void loginButtonClick(ActionEvent event) throws  Exception{

        System.out.println("<<<<<<<<<<<     Login button pressed         >>>>>>>>>>>>");

        String username = usernameField.getText().trim().toString();
        String password = passwordField.getText().trim().toString();

        System.out.println("<<<<<    Username: " + username + " || Password: " + password + "     >>>>>>>");

        //if(service.authenticate(username,password))
        WindowChange.Activate(event,"../display/fxml/user/dashboard.fxml", "User - Dashboard");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        service = (IUserCRUD) new Activator().activate("User");
    }
}
