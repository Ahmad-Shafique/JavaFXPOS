package ui.controllers.admin;

import core.application.services.Activator;
import core.domain.model.entities.*;
import core.domain.model.entities._utilities.DateBreakdown;
import core.domain.model.entities._utilities.console;
import core.domain.services.interfaces.ICRUD;
import infrastructure.dataaccess.NetworkAccessActivator;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import ui._utilities.WindowChange;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Button menu;
    @FXML
    private VBox drawer;

    @FXML
    private LineChart<String, Number> invoiceChart;
    @FXML
    CategoryAxis ixAxis;
    @FXML
    private BarChart<String, Double> productsChart;
    @FXML
    CategoryAxis pxAxis;

    @FXML
    private PieChart stockChart;

    private ICRUD<Item> itemService;
    private ICRUD<Invoice> invoiceService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        itemService = (ICRUD<Item>) new Activator().activate("Item", NetworkAccessActivator.Activate("Item"));
        invoiceService = (ICRUD<Invoice>) new Activator().activate("Invoice", NetworkAccessActivator.Activate("Invoice"));

        drawerAction();
        loadInvoiceChart();
        loadProductsChart();
        loadStockChart();
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

//        console.log("Completed drawerAction method");
    }

    private void loadInvoiceChart() {


//        console.log("Entered loadInvoiceChart method");

        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        ObservableList lists = FXCollections.observableArrayList(months);
        XYChart.Series series = new XYChart.Series();

//        console.log("Initiated chartXY series");

        List<Invoice> invoiceList = invoiceService.getAll();

        for (Invoice i : invoiceList) {
            String month = DateBreakdown.getInstance().getMonth(i.getTransactionTime());
//                    convertDate(i.getTransactionTime().toString());

//            console.log("month separated");

            series.getData().add(new XYChart.Data(month, i.getPayable()));

//            console.log("Added: " + i.getPayable() + " || To the chart");
        }

//        console.log("Added data to chart");

        series.setName("Sales");
        ixAxis.setCategories(lists);
        invoiceChart.getData().add(series);

//        console.log("Completed loadInvoiceChart method");
    }

    private void loadProductsChart() {

//        console.log("Entered method");

        ObservableList lists = FXCollections.observableArrayList();
        XYChart.Series<String, Double> series = new XYChart.Series<>();

//        console.log("Initiated objects");

        List<Item> itemList = itemService.getAll();

        for (Item p : itemList) {
            series.getData().add(new XYChart.Data(p.getName(), p.getQuantityAvailable()));

//            console.log("Added data to chart");

            lists.add(p.getName());

//            console.log("added item to observable list");
        }

        series.setName("Products");
        pxAxis.setCategories(lists);
        productsChart.getData().add(series);

//        console.log("completed loadProductsChart method");
    }

    private String convertDate(String date) {

        int d = Integer.parseInt(date.substring(5, 7));
        return new DateFormatSymbols().getMonths()[d - 1];
    }

    private void loadStockChart(){

        ObservableList<PieChart.Data> lists = FXCollections.observableArrayList();

        for(Item p : itemService.getAll()){

            lists.add(new PieChart.Data(p.getName(), p.getQuantityAvailable()));
        }

        stockChart.getData().addAll(lists);

//        console.log("Completed loadStockChart method");
    }

    @FXML
    public void productAction(ActionEvent event) throws Exception {

//        windows("/fxml/Product.fxml", "Product", event);
        WindowChange.Activate(event, "../display/fxml/product/product.fxml", "Product", "ui/display/resources/images/product.png", "internal");
    }

    @FXML
    public void categoryAction(ActionEvent event) throws Exception {

//        windows("/fxml/Category.fxml", "Category", event);
        WindowChange.Activate(event, "../display/fxml/category/category.fxml", "Category", "ui/display/resources/images/logo.png", "internal");
    }


    @FXML
    public void salesAction(ActionEvent event) throws Exception {

//        windows("/fxml/Sales.fxml", "Sales", event);
        WindowChange.Activate(event, "../display/fxml/salesreport/sales.fxml", "Sales", "ui/display/resources/images/logo.png", "internal");
    }

    @FXML
    public void posAction(ActionEvent event) throws Exception {
//        windows("/fxml/Employee.fxml", "Employee", event);
        WindowChange.Activate(event, "../display/fxml/pos/main.fxml", "POS Window", "ui/display/resources/images/logo.png", "internal");
    }

    @FXML
    public void logoutAction(ActionEvent event) throws Exception {
        WindowChange.Activate(event, "../display/fxml/login/login.fxml", "JFXPOS:: Version 1.0", "ui/display/resources/images/logo.png", "logout");
        /*((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
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
        stage.setTitle("Inventory:: Version 1.0");
        stage.getIcons().add(new Image("/images/logo.png"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();*/
    }

  /*  private void windows(String path, String title, ActionEvent event) throws Exception {

        double width = ((Node) event.getSource()).getScene().getWidth();
        double height = ((Node) event.getSource()).getScene().getHeight();

        Parent root = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(root, width, height);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.getIcons().add(new Image("/images/logo.png"));
        stage.setScene(scene);
        stage.show();
    }*/
}
