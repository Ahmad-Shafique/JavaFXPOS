package ui.controllers.product;

import core.application.services.Activator;
import core.domain.model.entities.Category;
import core.domain.model.entities.Item;
import core.domain.model.entities._utilities.console;
import core.domain.services.interfaces.ICRUD;
import core.domain.services.interfaces.ICategoryCRUD;
import core.domain.services.interfaces.dataload.IDataLoad;
import infrastructure.dataaccess.NetworkAccessActivator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import ui._utilities.AlertDisplay;
import ui._utilities.TransferService;
import ui._utilities.WindowChange;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditController implements Initializable, IDataLoad{

    private ICRUD<Item> itemService;
    private ICategoryCRUD categoryService;
    private ObservableList<Item> PRODUCTLIST = FXCollections.observableArrayList();
    private Item product;
    private int selectedProductId;
    AlertDisplay alert;
    ObservableList<String> categoryList ;

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
        itemService = (ICRUD<Item>) new Activator().activate("Item", this, NetworkAccessActivator.Activate("Item"));
        categoryService = (ICategoryCRUD) new Activator().activate("Category", this, NetworkAccessActivator.Activate("Category"));
        alert = new AlertDisplay();
        //        ObservableList<String> categoryList = FXCollections.observableArrayList(categoryModel.getTypes());
        categoryList = FXCollections.observableArrayList();
        categoryService.getAllCategoryNames();
        categoryBox.setItems(categoryList);
        resetValues();
        setProduct();
    }

    @Override
    public void pushData(Object obj) {
        List<String> categoryNameList = (List<String>) obj;
        categoryList.addAll(categoryNameList);
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
    public void handleSave(ActionEvent event) throws Exception {

        if (validateInput()) {
            int categoryId = categoryBox.getSelectionModel().getSelectedIndex() + 1;
            // Category category = categoryService.read(categoryBox.getSelectionModel().getSelectedIndex() + 1);
            Item editedProduct = new Item(
                    product.getId(),
                    categoryId,
                    nameField.getText(),
                    Double.parseDouble(priceField.getText()),
                    Integer.parseInt(quantityField.getText()),
                    descriptionArea.getText()
            );

            itemService.update(editedProduct.getId(),editedProduct);
            // console.log("returned form emulator");
            // PRODUCTLIST.set((int) selectedProductId, editedProduct);

            // ((Stage) saveButton.getScene().getWindow()).close();

            alert.informationDisplay("Successful", "Product Updated!", "Product is updated successfully");

            WindowChange.Activate(event,"../display/fxml/product/product.fxml","Product list", "ui/display/resources/images/product.png","internal");

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
            alert.errorDisplay("Invalid Fields", "Please correct invalid fields", errorMessage);

            return false;
        }
    }

    @FXML
    public void closeAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }


}
