package core.domain.model.entities;

public class User extends BaseEntity {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String type;

    public User(){
        this.id = -1;
        this.userName = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.type = "";
    }

    public User(int id, String userName, String password, String firstName, String lastName, String type){
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }
    public String getFullName(){
        return firstName + " " + lastName;
    }

    public String getType() {
        return type;
    }

    public String getPassword(){
        return password;
    }
}
