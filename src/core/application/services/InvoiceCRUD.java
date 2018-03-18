package core.application.services;

import core.application.services.data.access.interfaces.base.IBaseDataAccess;
import core.application.services.data.access.interfaces.modelwise.invoice.IInvoiceDataAccess;
import core.application.services.emulator.DBEmulator;
import core.domain.model.entities.Invoice;
import core.domain.model.entities._utilities.console;
import core.domain.services.interfaces.dataload.IDataLoad;

import java.util.Arrays;
import java.util.List;

public class InvoiceCRUD extends BaseCRUD<Invoice>{

    private IInvoiceDataAccess dataAccess;
    private IDataLoad dataLoader;

    InvoiceCRUD(IBaseDataAccess dataAccess, IDataLoad dataLoader){
        this.dataAccess = (IInvoiceDataAccess) dataAccess;
//        console.log("dataAccess set in invoiceCRUD");
        this.dataLoader = dataLoader;
//        console.log("dataLoader set in invoiceCRUD");
    }

    @Override
    public boolean create(Invoice entity) {

        return dataAccess.addToDatabase(entity);
    }

    @Override
    public Invoice read(int id) {

        dataLoader.pushData(dataAccess.getById(id));
        return null;
    }

    @Override
    public boolean update(int id, Invoice entity) {

//        return DBEmulator.updateInvoice(id,entity);
        return false;
    }

    @Override
    public boolean delete(int id) {

//        return DBEmulator.deleteInvoice(id);
        return false;
    }

    @Override
    public List<Invoice> getAll(){

        dataLoader.pushData(Arrays.asList((Invoice[]) dataAccess.getAll()));
        return null;
    }
}
