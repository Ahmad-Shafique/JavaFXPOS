package ui.controllers.product;

import core.application.services.Activator;
import core.domain.model.entities.Category;
import core.domain.model.entities.Item;
import core.domain.services.interfaces.ICRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {

    private ICRUD<Item> itemService;
    private ICRUD<Category> categoryService;
    private ObservableList<Item> PRODUCTLIST = FXCollections.observableArrayList();

    @FXML
    private TextField nameField, priceField, quantityField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private ComboBox categoryBox;
    @FXML
    private Button saveButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemService = (ICRUD<Item>) new Activator().activate("Item");
        categoryService = (ICRUD<Category>) new Activator().activate("Category");
//        ObservableList<String> categoryList = FXCollections.observableArrayList(categoryService.getAll());
        ObservableList<String> categoryList = FXCollections.observableArrayList("Category1","Category2");
        categoryBox.setItems(categoryList);
    }

    @FXML
    public void handleSave(ActionEvent event) {

        if (validateInput()) {

            Category category = categoryService.read(categoryBox.getSelectionModel().getSelectedIndex() + 1);
            Item product = new Item(
                    -1,
                    -1,
                    nameField.getText(),
                    Double.parseDouble(priceField.getText()),
                    Integer.parseInt(quantityField.getText().toString()),
                    descriptionArea.getText()
            );

            itemService.create(product);
            PRODUCTLIST.clear();
            PRODUCTLIST.addAll(itemService.getAll());

            ((Stage) saveButton.getScene().getWindow()).close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setHeaderText("Product is added");
            alert.setContentText("Product is added successfully");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        nameField.setText("");
        priceField.setText("");
        quantityField.setText("");
        descriptionArea.setText("");
        categoryBox.valueProperty().setValue(null);
    }

    private boolean validateInput() {

        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }

        if (priceField.getText() == null || priceField.getText().length() == 0) {
            errorMessage += "No valid price!\n";
        }

        if (quantityField.getText() == null || quantityField.getText().length() == 0) {
            errorMessage += "No valid quantity!\n";
        }

        if (descriptionArea.getText() == null || descriptionArea.getText().length() == 0) {
            errorMessage += "No email description!\n";
        }

        if (categoryBox.getSelectionModel().isEmpty()) {
            errorMessage += "Please select the category!\n";
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
