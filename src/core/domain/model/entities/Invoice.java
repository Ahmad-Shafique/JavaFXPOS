package core.domain.model.entities;

import core.domain.model.entities._utilities.OptionalParamsFactory;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

public class Invoice extends BaseSerializableEntity {
    private int userId;
    private int saleId;
    private String userName;
    private double totalPrice;
    private double vat;
    private double discount;
    private double payable;
    private double paid;
    private double returned;
    private Date transactionTime;

    public Invoice(){
        this.id = -1;
        this.userId = -1;
        this.userName = "";
        this.totalPrice = -1;
        this.payable = -1;
        this.paid = -1;
        this.returned = -1;
        this.transactionTime = new Date();
        this.vat = -1;
        this.discount = -1;

    }

    public Invoice(int id, int userId, String userName, double totalPrice, double payable, double paid, double returned, Date transactionTime,
                   Double vat, Double discount){
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.totalPrice = totalPrice;
        this.payable = payable;
        this.paid = paid;
        this.returned = returned;
        this.transactionTime = transactionTime;
//        this.vat = OptionalParamsFactory.getInstance().ProcessDouble(vat);
//        this.discount = OptionalParamsFactory.getInstance().ProcessDouble(discount);
        this.vat = vat;
        this.discount = discount;
    }

    public int getUserId() {
        return userId;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public String getUserName() {
        return userName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getVat() {
        return vat;
    }

    public double getDiscount() {
        return discount;
    }

    public double getPayable() {
        return payable;
    }

    public double getPaid() {
        return paid;
    }

    public double getReturned() {
        return returned;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }
}
