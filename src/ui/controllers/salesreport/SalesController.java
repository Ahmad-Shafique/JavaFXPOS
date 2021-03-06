package ui.controllers.salesreport;

import core.application.services.Activator;
import core.domain.services.classes.SalesReport;
import core.domain.services.interfaces.dataload.IDataLoad;
import core.domain.services.interfaces.ISaleCRUD;
import infrastructure.dataaccess.NetworkAccessActivator;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ui._utilities.WindowChange;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SalesController implements Initializable, IDataLoad{

    private ISaleCRUD service;
    private ObservableList<SalesReport> SALELIST = FXCollections.observableArrayList();

    @FXML
    private TableView<SalesReport> salesTable;
    @FXML
    private TableColumn<SalesReport, Integer> idColumn;
    @FXML
    private TableColumn<SalesReport, String> productColumn, dateColumn;
    @FXML
    private TableColumn<SalesReport, Double> priceColumn;
    @FXML
    private TextField searchField;
    @FXML
    private Button deleteButton;
    @FXML
    private Button menu;
    @FXML
    private VBox drawer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        service = (ISaleCRUD) new Activator().activate("Sale", this, NetworkAccessActivator.Activate("Sale"));

        drawerAction();
        loadData();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        productColumn.setCellValueFactory(new PropertyValueFactory<>("saleItems"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("transactionTime"));
        salesTable.setItems(SALELIST);

        filterData();

        deleteButton
                .disableProperty()
                .bind(Bindings.isEmpty(salesTable.getSelectionModel().getSelectedItems()));
    }

    @Override
    public void pushData(Object obj) {
    }

    private void filterData() {
        FilteredList<SalesReport> searchedData = new FilteredList<>(SALELIST, e -> true);
        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                searchedData.setPredicate(sale -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (sale.getSaleItems().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false;
                });
            });

            SortedList<SalesReport> sortedData = new SortedList<>(searchedData);
            sortedData.comparatorProperty().bind(salesTable.comparatorProperty());
            salesTable.setItems(sortedData);
        });
    }

    private void loadData(){

        if (!SALELIST.isEmpty()) {
            SALELIST.clear();
        }
        SALELIST.addAll(service.getSalesReports());
    }

    private void drawerAction() {

        TranslateTransition openNav = new TranslateTransition(new Duration(350), drawer);
        openNav.setToX(0);
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), drawer);
        menu.setOnAction((ActionEvent evt) -> {
            if (drawer.getTranslateX() != 0) {
                openNav.play();
                menu.getStyleClass().remove("hamburger-button");
                menu.getStyleClass().add("open-menu");
            } else {
                closeNav.setToX(-(drawer.getWidth()));
                closeNav.play();
                menu.getStyleClass().remove("open-menu");
                menu.getStyleClass().add("hamburger-button");
            }
        });
    }


    @FXML
    public void adminAction(ActionEvent event) throws Exception {
        WindowChange.Activate(event, "../display/fxml/admin/dashboard.fxml", "Admin");

    }

    @FXML
    public void productAction(ActionEvent event) throws Exception {

        WindowChange.Activate(event, "../display/fxml/product/product.fxml", "Product");
    }

    @FXML
    public void categoryAction(ActionEvent event) throws Exception {

        WindowChange.Activate(event, "../display/fxml/category/category.fxml", "Category");
    }

    @FXML
    public void posAction(ActionEvent event) throws Exception {
        WindowChange.Activate(event, "../display/fxml/pos/main.fxml", "POS Window");
    }

    @FXML
    public void logoutAction(ActionEvent event) throws Exception {
        WindowChange.Activate(event, "../display/fxml/login/login.fxml", "JFXPOS :: Version 1.0", "ui/display/images/logo.png", "logout");
    }

    @FXML
    public void deleteAction(ActionEvent event) throws Exception {
    }


}
