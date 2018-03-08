package core.application.services;

import core.application.services.emulator.DBEmulator;
import core.domain.model.entities.Category;
import core.domain.model.entities.Sale;

import java.util.List;

class SaleCRUD extends BaseCRUD<Sale> {
    @Override
    public boolean create(Sale entity) {
        return DBEmulator.addSale(entity);
    }

    @Override
    public Sale read(int id) {
        return DBEmulator.getSale(id);
    }

    @Override
    public boolean update(int id, Sale entity) {
        return DBEmulator.updateSale(id,entity);
    }

    @Override
    public boolean delete(int id) {
        return DBEmulator.deleteSale(id);
    }

    @Override
    public List<Sale> getAll(){
        return DBEmulator.getAllSales();
    }

}
