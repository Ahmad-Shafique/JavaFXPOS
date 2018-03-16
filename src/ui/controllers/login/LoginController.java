package ui.controllers.login;

import core.application.services.Activator;
import core.domain.model.entities.User;
import core.domain.services.interfaces.dataload.IDataLoad;
import core.domain.services.interfaces.IUserCRUD;
import infrastructure.dataaccess.NetworkAccessActivator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import ui._utilities.WindowChange;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable, IDataLoad{

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
        service = (IUserCRUD) new Activator().activate("User", this, NetworkAccessActivator.Activate("User"));
    }

    @Override
    public void pushData(Object obj) {

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
