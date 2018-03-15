package infrastructure.dataaccess._utilities;

public class NewCategory {
    private String name;
    private String description;


    public NewCategory( String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getDescription(){return description;}
}
