package ui.controllers.category;

import core.application.services.Activator;
import core.domain.model.entities.Category;
import core.domain.services.interfaces.ICRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {

    private ObservableList<Category> CATEGORYLIST = FXCollections.observableArrayList();

    @FXML
    private TextField typeField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private Button saveButton;
    private ICRUD<Category> service;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        service = (ICRUD<Category>) new Activator().activate("Category");
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        typeField.setText("");
        descriptionArea.setText("");
    }

    @FXML
    public void handleSave(ActionEvent event) {

        if (validateInput()) {

            Category category = new Category(
                    -1,
                    typeField.getText(),
                    descriptionArea.getText()
            );

            service.create(category);
            CATEGORYLIST.clear();
            CATEGORYLIST.addAll(service.getAll());

            ((Stage) saveButton.getScene().getWindow()).close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setHeaderText("Category Created!");
            alert.setContentText("Category is created successfully");
            alert.showAndWait();
        }
    }

    private boolean validateInput() {

        String errorMessage = "";

        if (typeField.getText() == null || typeField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }

        if (descriptionArea.getText() == null || descriptionArea.getText().length() == 0) {
            errorMessage += "No email description!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }

    @FXML
    public void closeAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
