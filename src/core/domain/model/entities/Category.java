package core.domain.model.entities;

public class Category extends BaseEntity{

    private String name;
    private String description;

    public Category(int id, String name){
        this.id = id;
        this.name = name;
        this.description = "";
    }

    public Category(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getDescription(){return description;}
}
