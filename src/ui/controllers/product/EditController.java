package ui.controllers.product;

import core.application.services.Activator;
import core.domain.model.entities.Category;
import core.domain.model.entities.Item;
import core.domain.services.interfaces.ICRUD;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import ui._utilities.TransferService;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditController implements Initializable {

    private ICRUD<Item> itemService;
    private ICRUD<Category> categoryService;
    private ObservableList<Item> PRODUCTLIST = FXCollections.observableArrayList();
    private Item product;
    private int selectedProductId;

    @FXML
    private TextField nameField, priceField, quantityField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private ComboBox categoryBox, supplierBox;
    @FXML
    private Button saveButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemService = (ICRUD<Item>) new Activator().activate("Item");
        categoryService = (ICRUD<Category>) new Activator().activate("Category");
//        ObservableList<String> categoryList = FXCollections.observableArrayList(categoryModel.getTypes());
        ObservableList<String> categoryList = FXCollections.observableArrayList("Category1", "Category2");
        categoryBox.setItems(categoryList);
        resetValues();
        setProduct();
    }

    public void setProduct() {
        this.product = (Item) TransferService.fetchStoredObject();
        this.selectedProductId = product.getId();
        setData();
    }

    private void setData() {
        nameField.setText(product.getName());
        priceField.setText(String.valueOf(product.getPrice()));
        quantityField.setText(String.valueOf(product.getQuantityAvailable()));
        descriptionArea.setText(String.valueOf(product.getDescription()));

        categoryBox.getSelectionModel().select(((int) product.getCategoryId()) - 1);
    }

    @FXML
    public void handleSave(ActionEvent event) {

        if (validateInput()) {
            Category category = categoryService.read(categoryBox.getSelectionModel().getSelectedIndex() + 1);
            Item editedProduct = new Item(
                    product.getId(),
                    category.getId(),
                    nameField.getText(),
                    Double.parseDouble(priceField.getText()),
                    Integer.parseInt(quantityField.getText()),
                    descriptionArea.getText()

            );

            itemService.update(editedProduct.getId(),editedProduct);
            PRODUCTLIST.set((int) selectedProductId, editedProduct);

            ((Stage) saveButton.getScene().getWindow()).close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setHeaderText("Product Updated!");
            alert.setContentText("Product is updated successfully");
            alert.showAndWait();
        }
    }

    private void resetValues() {
        nameField.setText("");
        priceField.setText("");
        quantityField.setText("");
        descriptionArea.setText("");
        categoryBox.valueProperty().setValue(null);
        supplierBox.valueProperty().setValue(null);
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        resetValues();
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
