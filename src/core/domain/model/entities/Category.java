package core.domain.model.entities;

public class Category extends BaseEntity{

    private String name;

    public Category(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
