package core.application.services;

import core.application.services.data.access.interfaces.modelwise.sale.ISaleDataAccess;
import core.application.services.emulator.DBEmulator;
import core.domain.model.entities.Sale;
import core.domain.services.classes.SalesReport;
import core.domain.services.interfaces.ISaleCRUD;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

class SaleCRUD extends BaseCRUD<Sale> implements ISaleCRUD {

    private ISaleDataAccess dataAccess;

    SaleCRUD(ISaleDataAccess dataAccess){
        this.dataAccess = dataAccess;
    }

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


    @Override
    public List<Sale> getSaleByProductId(int productId) {
        return this.getAll();
    }

    @Override
    public List<SalesReport> getSalesReports(){
      return Arrays.asList(
              new SalesReport(1,1000,new Date(), "Kachchi Biriyani (4X - 250)")
      );
    };
}
