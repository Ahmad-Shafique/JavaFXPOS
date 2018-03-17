package ui.controllers.checkout;

import core.domain.model.entities.Item;
import core.domain.model.entities.SaleItem;
import core.domain.model.entities._utilities.console;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui._utilities.TransferService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InformationValidationController implements Initializable {

    @FXML
    private TableView<SaleItem> productTableView;
    @FXML
    private TableColumn<SaleItem, String> itemColumn;
    @FXML
    private TableColumn<SaleItem, Double> priceColumn, quantityColumn, totalColumn;

    private ObservableList<SaleItem> itemList ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        itemList = (ObservableList<SaleItem>) TransferService.fetchStoredObject();
//        checkoutItems = FXCollections.observableArrayList();

        loadDataToProductList();

        // productTableView.setItems(itemList);

        itemColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        // productTableView.setItems(itemList);


/*        ObservableList<SaleItem> nItemList = FXCollections.observableArrayList(
                new SaleItem("item 1", 10.0 , 3),
                new SaleItem("item 2", 20.0 , 1)

        );

        productTableView.setItems(nItemList);*/
    }

    public void loadDataToProductList(){
//        productsListView.setItems(itemList);
/*        for (SaleItem item : itemList
             ) {
            console.log(item.getName() + "  - " + item.getQuantity() + "X - $" + item.getTotal() );
//            checkoutItems.add(item.getName() + "  - " + item.getQuantity() + "X - $" + item.getTotal());
        }*/

        productTableView.setItems(itemList);

    }
}
