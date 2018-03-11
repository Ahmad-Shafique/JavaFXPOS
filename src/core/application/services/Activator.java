package core.application.services;

import core.application.services.data.access.interfaces.base.IBaseDataAccess;
import core.application.services.data.access.interfaces.modelwise.category.ICategoryDataAccess;
import core.application.services.data.access.interfaces.modelwise.item.IItemDataAccess;
import core.application.services.data.access.interfaces.modelwise.sale.ISaleDataAccess;
import core.application.services.data.access.interfaces.modelwise.user.IUserDataAccess;

public class Activator {
    public Activator(){}

    public Object activate(String repositoryName, IBaseDataAccess dataAccess){
        if (repositoryName == "Category") return new CategoryCRUD((ICategoryDataAccess) dataAccess);
        else if(repositoryName == "Item") return new ItemCRUD((IItemDataAccess) dataAccess);
        else if(repositoryName == "Sale") return new SaleCRUD((ISaleDataAccess) dataAccess);
        else if(repositoryName == "User") return new UserCRUD((IUserDataAccess) dataAccess);
        else if(repositoryName == "Invoice") return new InvoiceCRUD(dataAccess);
        return new Object();
    }
}
