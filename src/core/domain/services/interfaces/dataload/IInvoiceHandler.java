package core.domain.services.interfaces.dataload;

import core.domain.model.entities.Invoice;

import java.util.List;

public interface IInvoiceHandler extends IDataLoad{
    void pushInvoice(List<Invoice> invoiceList);
}
