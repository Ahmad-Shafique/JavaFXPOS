package ui.controllers.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ui._utilities.WindowChange;

public class AdminDashboardController {
    @FXML
    public void dailyReportButtonClick(ActionEvent event) throws  Exception{
        WindowChange.Activate(event,"../display/fxml/admin/report.fxml", "Admin - Daily Report");
    }

    @FXML
    public void monthlyReportButtonClick(ActionEvent event) throws  Exception{
        WindowChange.Activate(event,"../display/fxml/admin/report.fxml", "Admin - Monthly Report");
    }

    @FXML
    public void yearlyReportButtonClick(ActionEvent event) throws  Exception{
        WindowChange.Activate(event,"../display/fxml/admin/report.fxml", "Admin - Yearly Report");
    }

    @FXML
    public void saleButtonClick(ActionEvent event) throws  Exception{
        WindowChange.Activate(event,"../display/fxml/pos/main.fxml", "Sale - Admin");
    }

    @FXML
    public void changePasswordButtonClick(ActionEvent event) throws  Exception{
        WindowChange.Activate(event,"../display/fxml/pos/main.fxml", "Admin - Change Password");
    }
}
