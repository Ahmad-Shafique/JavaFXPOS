package core.domain.model.entities;

public class Item extends BaseEntity{
    private int categoryId;
    private String name;
    private double price;
    private int quantityAvailable;

    public Item(int id, int categoryId, String name, double price, int quantityAvailable){
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }
}


