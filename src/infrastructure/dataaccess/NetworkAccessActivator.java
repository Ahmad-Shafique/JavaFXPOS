package infrastructure.dataaccess;

import core.application.services.data.access.interfaces.base.IBaseDataAccess;
import infrastructure.dataaccess.modelwise.category.CategoryDataAccess;
import infrastructure.dataaccess.modelwise.invoice.InvoiceDataAccess;
import infrastructure.dataaccess.modelwise.item.ItemDataAccess;
import infrastructure.dataaccess.modelwise.sale.SaleDataAccess;
import infrastructure.dataaccess.modelwise.user.UserDataAccess;

public class NetworkAccessActivator {
    public static IBaseDataAccess Activate(String entityName){
        if(entityName == "Item") return new ItemDataAccess();
        else if(entityName == "Category") return new CategoryDataAccess();
        else if(entityName == "Sale") return new SaleDataAccess();
        else if(entityName == "User") return new UserDataAccess();
        else if(entityName == "Invoice") return new InvoiceDataAccess();
        return null;
    }
}
