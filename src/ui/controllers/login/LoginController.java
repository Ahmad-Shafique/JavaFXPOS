package ui.controllers.login;

import core.application.services.Activator;
import core.domain.model.entities.User;
import core.domain.services.interfaces.ICRUD;
import core.domain.services.interfaces.IUserCRUD;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private Label errorLabel;
    private User model;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new User();
        enterPressed();
        service = (IUserCRUD) new Activator().activate("User");
    }

    private void enterPressed() {

        usernameField.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                try {
                    authenticate(ke);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        passwordField.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                try {
                    authenticate(ke);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }

    @FXML
    public void loginAction(ActionEvent event) throws Exception {

        authenticate(event);
    }

    private void authenticate(Event event) throws Exception {
        if (validateInput()) {

            String username = usernameField.getText().trim().toString();
            String password = passwordField.getText().trim().toString();

            // System.out.println("<<<<<    Username: " + username + " || Password: " + password + "     >>>>>>>");

            if(service.authenticate(username,password))
                WindowChange.Activate(event,"../display/fxml/admin/dashboard.fxml", "User - Dashboard");
        }
    }

    private void resetFields() {
        usernameField.setText("");
        passwordField.setText("");
    }

    @FXML
    public void cancelAction(ActionEvent event) {
        resetFields();
    }

    @FXML
    public void closeAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void minusAction(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    private boolean validateInput() {

        String errorMessage = "";

        if (usernameField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "Please enter credentials!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            errorLabel.setText(errorMessage);
            return false;
        }
    }


}
