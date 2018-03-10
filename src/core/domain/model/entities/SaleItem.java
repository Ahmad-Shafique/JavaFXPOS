package core.domain.model.entities;

public class SaleItem extends BaseEntity{
    private int saleId;
    private int itemId;
    private String name;
    private int quantity;
    private double price;
    private double total;

    public SaleItem(int id, int saleId, int itemId, int quantity, double price){
        this.id = id;
        this.saleId = saleId;
        this.itemId = itemId;
        this.name = "";
        this.quantity = quantity;
        this.price = price;
        this.total = this.price*this.quantity;
    }

    public SaleItem(String name, double price, int quantity){
        this.id = -1;
        this.saleId = -1;
        this.itemId = -1;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.total = this.price*this.quantity;
    }


    public int getSaleId() {
        return saleId;
    }

    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotal(){ return total; }
}
