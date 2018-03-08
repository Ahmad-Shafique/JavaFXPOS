package core.application.services;

public class Activator {
    public Activator(){}

    public Object activate(String repositoryName){
        if (repositoryName == "Category") return new CategoryCRUD();
        else if(repositoryName == "Item") return new ItemCRUD();
        else if(repositoryName == "Sale") return new SaleCRUD();
        else if(repositoryName == "User") return new UserCRUD();
        return new Object();
    }
}
