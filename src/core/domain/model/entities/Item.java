package core.domain.model.entities;

import java.util.Optional;

public class Item extends BaseEntity{
    private int categoryId;
    private String name;
    private double price;
    private int quantityAvailable;
    private String description;

    public Item(){
        this.id = -1;
        this.categoryId = -1;
        this.name = "";
        this.price = -1;
        this.quantityAvailable = -1;
        this.description = "";
    }

    public Item(int id, int categoryId, String name, double price, int quantityAvailable, String description){
        this.id = id;
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


