package ui.controllers.category;

import core.application.services.Activator;
import core.domain.model.entities.Category;
import core.domain.services.interfaces.ICategoryCRUD;
import core.domain.services.interfaces.dataload.IDataLoad;
import core.domain.services.interfaces.dataload.category.ICategoryListLoad;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ui._utilities.TransferService;
import ui._utilities.WindowChange;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CategoryController implements Initializable,IDataLoad {

    private ICategoryCRUD service;
    private ObservableList<Category> CATEGORYLIST = FXCollections.observableArrayList();

    @FXML
    private TableView<Category> categoryTable;
    @FXML
    private TableColumn<Category, Long> idColumn;
    @FXML
    private TableColumn<Category, String> typeColumn, descriptionColumn;
    @FXML
    private TextField searchField;
    @FXML
    private Button editButton, deleteButton;
    @FXML
    private Button menu;
    @FXML
    private VBox drawer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        service = (ICategoryCRUD) new Activator().activate("Category", this, NetworkAccessActivator.Activate("Category"));

        drawerAction();
        loadData();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        categoryTable.setItems(CATEGORYLIST);

        filterData();

        editButton
                .disableProperty()
                .bind(Bindings.isEmpty(categoryTable.getSelectionModel().getSelectedItems()));
        deleteButton
                .disableProperty()
                .bind(Bindings.isEmpty(categoryTable.getSelectionModel().getSelectedItems()));
    }

    @Override
    public void pushData(Object catList) {
        if (!CATEGORYLIST.isEmpty()) {
            CATEGORYLIST.clear();
        }
        CATEGORYLIST.addAll( (List<Category>) catList );
    }




    private void filterData() {
        FilteredList<Category> searchedData = new FilteredList<>(CATEGORYLIST, e -> true);
        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                searchedData.setPredicate(category -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (category.getName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (category.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });

            SortedList<Category> sortedData = new SortedList<>(searchedData);
            sortedData.comparatorProperty().bind(categoryTable.comparatorProperty());
            categoryTable.setItems(sortedData);
        });
    }

    private void loadData() {
        service.getAll();
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
    public void salesAction(ActionEvent event) throws Exception {
        WindowChange.Activate(event, "../display/fxml/salesreport/sales.fxml", "Sales");
    }

    @FXML
    public void posAction(ActionEvent event) throws Exception {
        WindowChange.Activate(event,"../display/fxml/pos/main.fxml", "POS Window");
    }

    @FXML
    public void logoutAction(ActionEvent event) throws Exception {
        WindowChange.Activate(event, "../display/fxml/login/login.fxml", "JFXPOS :: Version 1.0", "ui/display/resources/images/logo.png", "logout");

    }

    @FXML
    public void addAction(ActionEvent event) throws Exception {

        WindowChange.Activate(event, "../display/fxml/category/add.fxml", "New Category", "ui/display/resources/images/logo.png", "internal");
    }

    @FXML
    public void editAction(ActionEvent event) throws Exception {

//        console.log("Edit action");

        Category selectedCategory = categoryTable.getSelectionModel().getSelectedItem();
        TransferService.storeForTransfer(selectedCategory);
        WindowChange.Activate(event, "../display/fxml/category/edit.fxml", "Edit Category", "ui/display/resources/images/logo.png", "internal");


        /*Category selectedCategory = categoryTable.getSelectionModel().getSelectedItem();
        int selectedCategoryId = categoryTable.getSelectionModel().getSelectedIndex();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("/fxml/category/Edit.fxml")));
        EditController controller = new EditController();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        root.setOnMousePressed((MouseEvent e) -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent e) -> {
            stage.setX(e.getScreenX() - xOffset);
            stage.setY(e.getScreenY() - yOffset);
        });
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Edit Category");
        stage.getIcons().add(new Image("/images/logo.png"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        controller.setCategory(selectedCategory, selectedCategoryId);
        categoryTable.getSelectionModel().clearSelection();*/
    }

    @FXML
    public void deleteAction(ActionEvent event) throws Exception{

//        console.log("delete action");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Product");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Category selectedCategory = categoryTable.getSelectionModel().getSelectedItem();

            service.delete(selectedCategory.getId());
            WindowChange.Activate(event, "../display/fxml/category/category.fxml", "Category List", "ui/display/resources/images/logo.png", "internal");

        }

        /*Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Product");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Category selectedCategory = categoryTable.getSelectionModel().getSelectedItem();

            model.deleteCategory(selectedCategory);
            CATEGORYLIST.remove(selectedCategory);
        }

        categoryTable.getSelectionModel().clearSelection();*/
    }



}
