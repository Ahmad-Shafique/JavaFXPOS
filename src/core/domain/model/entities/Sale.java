package core.domain.model.entities;

import java.util.Collection;
import java.util.Date;

public class Sale extends BaseEntity{
    private int userId;
    private double totalPrice;
    private Date transactionTime;
    private Collection<SaleItem> saleItems;

    public Sale(int id, int userId, double totalPrice, Date transactionTime, Collection<SaleItem> saleItems){
        this.id = id;
        this.userId = userId;
        this.totalPrice  =totalPrice;
        this.transactionTime = transactionTime;
        this.saleItems = saleItems;
    }

    public int getUserId() {
        return userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public Collection<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(Collection<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }
}
