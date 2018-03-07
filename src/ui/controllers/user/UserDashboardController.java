package ui.controllers.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ui._utilities.WindowChange;

public class UserDashboardController {

    @FXML
    public void saleButtonClick(ActionEvent event) throws  Exception{
        WindowChange.Activate(event,"../display/fxml/pos/main.fxml", "Sale");
    }

    @FXML
    public void changePasswordButtonClick(ActionEvent event) throws  Exception{
        WindowChange.Activate(event,"../display/fxml/pos/main.fxml", "Change Password");
    }
}
