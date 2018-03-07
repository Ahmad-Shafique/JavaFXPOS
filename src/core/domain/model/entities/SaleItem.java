package core.domain.model.entities;

public class SaleItem extends BaseEntity{
    private int saleId;
    private int itemId;
    private int quantity;
    private double price;

    public SaleItem(int id, int saleId, int itemId, int quantity, double price){
        this.id = id;
        this.saleId = saleId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getSaleId() {
        return saleId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
