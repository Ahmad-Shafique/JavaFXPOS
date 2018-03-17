package infrastructure.dataaccess._utilities;

import java.util.Date;

public class NewInvoice {

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

    public NewInvoice(int userId, int saleId, String userName, double totalPrice, double payable, double paid, double returned, Date transactionTime,
                   Double vat, Double discount){
        this.userId = userId;
        this.saleId = saleId;
        this.userName = userName;
        this.totalPrice = totalPrice;
        this.payable = payable;
        this.paid = paid;
        this.returned = returned;
        this.transactionTime = transactionTime;
        this.vat = vat;
        this.discount = discount;
    }
}
