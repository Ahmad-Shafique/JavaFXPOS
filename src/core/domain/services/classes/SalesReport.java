package core.domain.services.classes;

import core.domain.model.entities.BaseSerializableEntity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class SalesReport extends BaseSerializableEntity {
    private double totalPrice;
    private Date transactionTime;
    private String saleItems;

    public SalesReport(int id, double totalPrice, Date transactionTime, String saleItems){
        this.id = id;
        this.totalPrice  =totalPrice;
        this.transactionTime = transactionTime;
        this.saleItems = saleItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public String getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(String saleItems) {
        this.saleItems = saleItems;
    }
}
