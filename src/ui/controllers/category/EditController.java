package ui.controllers.category;

import core.application.services.Activator;
import core.domain.model.entities.Category;
import core.domain.services.interfaces.ICRUD;
import infrastructure.dataaccess.NetworkAccessActivator;
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
import ui._utilities.TransferService;
import ui._utilities.WindowChange;

import java.net.URL;
import java.util.ResourceBundle;

public class EditController implements Initializable{

    private ICRUD<Category> service;
    private Category category;
    private long selectedCategoryId;
    private ObservableList<Category> CATEGORYLIST = FXCollections.observableArrayList();

    @FXML
    private TextField typeField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private Button saveButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        service = (ICRUD<Category>) new Activator().activate("Category", NetworkAccessActivator.Activate("Category"));
        resetValues();
        setCategory();
    }

    public void setCategory() {
        this.category = (Category) TransferService.fetchStoredObject();
        this.selectedCategoryId = selectedCategoryId;
        setData();
    }

    @FXML
    public void handleSave(ActionEvent event) throws Exception {

        if (validateInput()) {

            Category editedCategory = new Category(
                    category.getId(),
                    typeField.getText(),
                    descriptionArea.getText()
            );

            service.update(editedCategory.getId(),editedCategory);
            // CATEGORYLIST.set((int) selectedCategoryId, editedCategory);

            // ((Stage) saveButton.getScene().getWindow()).close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setHeaderText("Category Updated!");
            alert.setContentText("Category is updated successfully");
            alert.showAndWait();

            WindowChange.Activate(event,"../display/fxml/category/category.fxml","Category list", "ui/display/resources/images/category.png","internal");

        }
    }

    private void setData() {
        typeField.setText(category.getName());
        descriptionArea.setText(category.getDescription());
    }

    private void resetValues() {
        typeField.setText("");
        descriptionArea.setText("");
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        resetValues();
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
