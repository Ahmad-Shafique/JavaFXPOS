package core.domain.services.interfaces;

import core.domain.model.entities.Sale;
import core.domain.services.classes.SalesReport;

import java.util.List;

public interface ISaleCRUD extends ICRUD<Sale> {
    public List<Sale> getSaleByProductId(int productId);
    public List<SalesReport> getSalesReports();
}
