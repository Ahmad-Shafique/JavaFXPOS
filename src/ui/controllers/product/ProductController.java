package ui.controllers.product;

import core.application.services.Activator;
import core.domain.model.entities.Item;
import core.domain.model.entities.Sale;
import core.domain.model.entities._utilities.DateBreakdown;
import core.domain.model.entities._utilities.console;
import core.domain.services.interfaces.ICRUD;
import core.domain.services.interfaces.ISaleCRUD;
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
import ui._utilities.WindowChange;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProductController implements Initializable{
    private ICRUD<Item> itemService;
    private ISaleCRUD saleService;

    private ObservableList<Item> PRODUCTLIST = FXCollections.observableArrayList();

    @FXML
    private TableView<Item> productTable;
    @FXML
    private TableColumn<Item, Integer> idColumn;
    @FXML
    private TableColumn<Item, String> categoryColumn,nameColumn, descriptionColumn;
    @FXML
    private TableColumn<Item, Double> priceColumn, quantityColumn;
    @FXML
    private TextField searchField;
    @FXML
    private Button editButton, deleteButton;
    @FXML
    private LineChart<String, Number> productChart;
    @FXML
    CategoryAxis pxAxis;
    @FXML
    private Button menu;
    @FXML
    private VBox drawer;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemService = (ICRUD<Item>) new Activator().activate("Item");
        saleService = (ISaleCRUD) new Activator().activate("Sale");
        drawerAction();
        loadData();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantityAvailable"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        productTable.setItems(PRODUCTLIST);

        filterData();

        productTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> loadProductSalesChart(newValue));

        editButton
                .disableProperty()
                .bind(Bindings.isEmpty(productTable.getSelectionModel().getSelectedItems()));
        deleteButton
                .disableProperty()
                .bind(Bindings.isEmpty(productTable.getSelectionModel().getSelectedItems()));
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
            sortedData.comparatorProperty().bind(productTable.comparatorProperty());
            productTable.setItems(sortedData);
        });
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

    private void loadData() {

        if (!PRODUCTLIST.isEmpty()) {
            PRODUCTLIST.clear();
        }
        PRODUCTLIST.addAll(itemService.getAll());
    }

    private void loadProductSalesChart(Item p) {

        if (p != null) {

            String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
            ObservableList lists = FXCollections.observableArrayList(months);
            pxAxis.setCategories(lists);

            productChart.getData().clear();

            List<Sale> sales = saleService.getSaleByProductId(p.getId());


            XYChart.Series series = new XYChart.Series();
            series.setName(p.getName());

            for (Sale s : sales) {

//                String month = convertDate(s.getDate());
                String month = DateBreakdown.getInstance().getMonth(s.getTransactionTime());
                series.getData().add(new XYChart.Data(month, s.getTotalPrice()));
            }

            productChart.getData().addAll(series);
        }

    }


    @FXML
    public void adminAction(ActionEvent event) throws Exception {
        WindowChange.Activate(event, "../display/fxml/admin/dashboard.fxml", "Admin" );
    }

    @FXML
    public void categoryAction(ActionEvent event) throws Exception {

        WindowChange.Activate(event, "../display/fxml/category/category.fxml", "Category");
    }

    @FXML
    public void salesAction(ActionEvent event) throws Exception {

        WindowChange.Activate(event, "../display/fxml/salesreport/sales.fxml", "Sales");
    }

    @FXML
    public void posAction(ActionEvent event) throws Exception {
        WindowChange.Activate(event, "../display/fxml/pos/main.fxml", "POS Window");
    }

    @FXML
    public void logoutAction(ActionEvent event) throws Exception {
        WindowChange.Activate(event, "../display/fxml/login/login.fxml", "JFXPOS :: Version 1.0", "ui/display/resources/images/logo.png", "logout");
    }


    @FXML
    public void addAction(ActionEvent event) throws Exception {

        WindowChange.Activate(event, "../display/fxml/product/Add.fxml", "New Product", "ui/display/resources/images/logo.png", "internal");

    }


    public void editAction(ActionEvent event) throws Exception {
        console.log("Edit action");

        Item selectedProduct = productTable.getSelectionModel().getSelectedItem();
        TransferService.storeForTransfer(selectedProduct);

        WindowChange.Activate(event, "../display/fxml/product/edit.fxml", "Edit Product", "ui/display/resources/images/logo.png", "internal");

    }
/*    @FXML
    public void editAction(ActionEvent event) throws Exception {

        Item selectedProduct = productTable.getSelectionModel().getSelectedItem();
        int selectedProductId = productTable.getSelectionModel().getSelectedIndex();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("/fxml/product/Edit.fxml")));
        EditController controller = new EditController();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Edit Product");
        stage.getIcons().add(new Image("/images/logo.png"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        controller.setProduct(selectedProduct, selectedProductId);
        productTable.getSelectionModel().clearSelection();
    }

    @FXML
    public void deleteAction(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Product");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();

            model.deleteProduct(selectedProduct);
            PRODUCTLIST.remove(selectedProduct);
        }

        productTable.getSelectionModel().clearSelection();
    }*/
    public void deleteAction(ActionEvent event) throws Exception {

//        console.log("delete action");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Product");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Item selectedProduct = productTable.getSelectionModel().getSelectedItem();
            itemService.delete(selectedProduct.getId());
            WindowChange.Activate(event, "../display/fxml/product/product.fxml", "New Product", "ui/display/resources/images/logo.png", "internal");
        }
    }

}
