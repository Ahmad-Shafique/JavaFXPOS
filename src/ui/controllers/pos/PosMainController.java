package ui.controllers.pos;

import core.application.services.Activator;
import core.domain.model.entities.Item;
import core.domain.model.entities.SaleItem;
import core.domain.model.entities._utilities.console;
import core.domain.services.interfaces.ICRUD;
import core.domain.services.interfaces.dataload.IDataLoad;
import infrastructure.dataaccess.NetworkAccessActivator;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ui._utilities.AlertDisplay;
import ui._utilities.TransferService;
import ui._utilities.WindowChange;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PosMainController implements Initializable, IDataLoad {

    private ICRUD<Item> service;
    private ObservableList<Item> PRODUCTLIST = FXCollections.observableArrayList();
    private AlertDisplay alert;


    @FXML
    private TableView<Item> productTableView;
    @FXML
    private TableView<SaleItem> listTableView;
    @FXML
    private TableColumn<Item, String> productColumn;
    @FXML
    private TableColumn<SaleItem, String> itemColumn;
    @FXML
    private TableColumn<SaleItem, Double> priceColumn, quantityColumn, totalColumn;
    @FXML
    private TextField searchField, productField, priceField, quantityField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private TextField subTotalField, discountField, vatField, netPayableField;
    @FXML
    private Button addButton, removeButton, paymentButton;
    @FXML
    private Label quantityLabel;
    @FXML
    private ObservableList<SaleItem> ITEMLIST;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ITEMLIST = FXCollections.observableArrayList();
        service = (ICRUD<Item>) new Activator().activate("Item", this, NetworkAccessActivator.Activate("Item"));
        alert = new AlertDisplay();

        service.getAll();
//        loadData();

        productColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));
        productTableView.setItems(PRODUCTLIST);

        filterData();

        itemColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        listTableView.setItems(ITEMLIST);

        addButton
                .disableProperty()
                .bind(Bindings.isEmpty(productTableView.getSelectionModel().getSelectedItems()));
        removeButton
                .disableProperty()
                .bind(Bindings.isEmpty(listTableView.getSelectionModel().getSelectedItems()));
    }


    @Override
    public void pushData(Object obj) {
        List<Item> itemList = (List<Item>) obj;
        loadData(itemList);
    }

    private void filterData() {
        FilteredList<Item> searchedData = new FilteredList<>(PRODUCTLIST, e -> true);

        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                searchedData.setPredicate(product -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (product.getName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (product.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });

            SortedList<Item> sortedData = new SortedList<>(searchedData);
            sortedData.comparatorProperty().bind(productTableView.comparatorProperty());
            productTableView.setItems(sortedData);
        });
    }

    private void loadData(List<Item> itemList) {
        if (!PRODUCTLIST.isEmpty()) {
            PRODUCTLIST.clear();
        }
        PRODUCTLIST.addAll(itemList);
    }

    private void showDetails(Item product) {
        if (product != null) {
            quantityField.setDisable(false);
            productField.setText(product.getName());
            priceField.setText(String.valueOf(product.getPrice()));

            double quantity = product.getQuantityAvailable();

            if (quantity > 0) {
                quantityField.setEditable(true);
                quantityField.setStyle(null);
            } else {
                quantityField.setEditable(false);
                quantityField.setStyle("-fx-background-color: red;");
            }
            quantityLabel.setText("Stock: " + String.valueOf(quantity));
            descriptionArea.setText(product.getDescription());
        } else {
            productField.setText("");
            priceField.setText("");
            quantityLabel.setText("");
            descriptionArea.setText("");
        }
    }

    private void resetProductTableSelection() {
        productTableView.getSelectionModel().clearSelection();
    }

    private void resetItemTable() {
        ITEMLIST.clear();
    }

    private void resetAdd() {
        productField.setText("");
        priceField.setText("");
        quantityField.setText("");
        resetQuantityField();
        quantityLabel.setText("Available: ");
        descriptionArea.setText("");
    }

    private void resetInvoice() {
        subTotalField.setText("0.00");
        vatField.setText("0.00");
        netPayableField.setText("0.00");
    }

    private void resetQuantityField() {
        quantityField.setDisable(true);
    }

    private void resetPaymentButton() {
        paymentButton.setDisable(true);
    }

    private void resetInterface() {
        // loadData();
        service.getAll();
        resetPaymentButton();
        resetProductTableSelection();
        resetItemTable();
        resetQuantityField();
        resetAdd();
        resetInvoice();
    }

    @FXML
    public void resetAction(ActionEvent event) {
        resetInterface();
    }

    @FXML
    public void addAction(ActionEvent event) {

        if (validateInput()) {
            String productName = productField.getText();
            double unitPrice = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            double total = unitPrice * quantity;
            ITEMLIST.add(new SaleItem(productName, unitPrice, quantity));
            calculation();

            resetAdd();
            productTableView.getSelectionModel().clearSelection();
        }
    }

    private void calculation() {

        double subTotalPrice = 0.0;
        subTotalPrice = listTableView.getItems().stream().map(
                (item) -> item.getTotal()).reduce(subTotalPrice, (accumulator, _item) -> accumulator + _item);

        if (subTotalPrice > 0) {
            paymentButton.setDisable(false);
            double vat = (double) subTotalPrice * 0.025;
            double netPayablePrice = (double) (Math.abs((subTotalPrice + vat) - 5));

            subTotalField.setText(String.valueOf(subTotalPrice));
            vatField.setText(String.valueOf(vat));
            netPayableField.setText(String.valueOf(netPayablePrice));
        }
    }

    @FXML
    public void paymentAction(ActionEvent event) throws Exception {
        console.log("Payment button pressed");

//        List<SaleItem> saleItems = Arrays.asList(new SaleItem[1]);
//
//        for (SaleItem item : ITEMLIST
//             ) {
//            saleItems.add(item);
//        }
//
//        TransferService.storeForTransfer(saleItems);

        TransferService.storeForTransfer(ITEMLIST);

        WindowChange.Activate(event,"../display/fxml/checkout/information_validation.fxml", "Checkout Window");


        // WindowChange.Activate(event, "../../ui/display/fxml/checkout/information_validation.fxml", "JFXPOS:: Version 1.0", "/images/logo.png", "internal");

        /*Payment payment = new Payment(
                Double.parseDouble(subTotalField.getText().trim()),
                Double.parseDouble(vatField.getText().trim()),
                Double.parseDouble(discountField.getText().trim()),
                Double.parseDouble(netPayableField.getText().trim())
        );

        ObservableList<SaleItem> sold = listTableView.getItems();

        FXMLLoader loader = new FXMLLoader((getClass().getResource("/fxml/Invoice.fxml")));
        InvoiceController controller = new InvoiceController();
        loader.setController(controller);
        controller.setData(Double.parseDouble(netPayableField.getText().trim()), sold, payment);
        Parent root = loader.load();
        Stage stage = new Stage();
        root.setOnMousePressed((MouseEvent e) -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent e) -> {
            stage.setX(e.getScreenX() - xOffset);
            stage.setY(e.getScreenY() - yOffset);
        });
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Payment");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image("/images/logo.png"));
        stage.setScene(scene);
        stage.showAndWait();

        resetInterface();*/
    }

    @FXML
    public void removeAction(ActionEvent event) {

        int index = listTableView.getSelectionModel().getSelectedIndex();

        if (index > 0) {
            listTableView.getItems().remove(index);
            calculation();
        } else if (index == 0) {
            listTableView.getItems().remove(index);
            resetInvoice();
        }
    }

    private boolean validateInput() {

        String errorMessage = "";

        if (quantityField.getText() == null || quantityField.getText().length() == 0) {
            errorMessage += "Quantity not supplied!\n";
        } else {
            double quantity = Double.parseDouble(quantityField.getText());
            String available = quantityLabel.getText();
            double availableQuantity = Double.parseDouble(available.substring(7));

            if (quantity > availableQuantity) {
                errorMessage += "Out of Stock!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            alert.warningDisplay("Warning", "Please input the valid number of products", errorMessage);
            quantityField.setText("");

            return false;
        }
    }

    @FXML
    public void logoutAction(ActionEvent event) throws Exception {
        WindowChange.Activate(event, "/fxml/Login.fxml", "Inventory:: Version 1.0", "/images/logo.png", "logout");
    }

}
