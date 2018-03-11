package infrastructure.dataaccess.modelwise.invoice;

import core.application.services.data.access.interfaces.base.IBaseDataAccess;
import infrastructure.dataaccess.base.NetworkBase;

public class InvoiceDataAccess extends NetworkBase implements IBaseDataAccess {
    @Override
    public Object[] getAll() {
        return new Object[0];
    }
}
