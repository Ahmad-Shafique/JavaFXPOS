package core.application.services;

import core.application.services.data.access.interfaces.base.IBaseDataAccess;
import core.application.services.data.access.interfaces.extended.IExtendedDataAccess;
import core.application.services.emulator.DBEmulator;
import core.domain.model.entities.Category;
import core.domain.model.entities.Invoice;

import java.util.List;

public class InvoiceCRUD extends BaseCRUD<Invoice>{

    InvoiceCRUD(){}

    InvoiceCRUD(IBaseDataAccess dataAccess){
        super(dataAccess);
    }

    @Override
    public boolean create(Invoice entity) {
        return DBEmulator.addInvoice(entity);
    }

    @Override
    public Invoice read(int id) {
        return DBEmulator.getInvoice(id);
    }

    @Override
    public boolean update(int id, Invoice entity) {
        return DBEmulator.updateInvoice(id,entity);
    }

    @Override
    public boolean delete(int id) {
        return DBEmulator.deleteInvoice(id);
    }

    @Override
    public List<Invoice> getAll(){
        return DBEmulator.getAllInvoices();
    }
}
