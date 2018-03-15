package infrastructure.dataaccess._utilities;

public class NewItem {
    private int categoryId;
    private String name;
    private double price;
    private int quantityAvailable;
    private String description;

    public NewItem( int categoryId, String name, double price, int quantityAvailable, String description){
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
        this.description = description;
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

    public String getDescription() {
        return description;
    }
}
