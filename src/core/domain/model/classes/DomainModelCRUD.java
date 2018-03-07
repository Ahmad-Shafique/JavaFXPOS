package core.domain.model.classes;

import core.domain.model.interfaces.IDomainModel;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public abstract class DomainModelCRUD<TEntity extends Object> implements IDomainModel<TEntity> {

    List<TEntity> entityList;

    public boolean add(TEntity entity, String token) {

        System.out.println("Add method in domain model");
        return entityList.add(entity);
    }

    public TEntity get(int id, String token) {
        System.out.println("get method in domain model");
        return null;
    }

    public Collection<TEntity> getAll(String token) {
        System.out.println("getall method in domain model");
        return null;
    }

    public boolean update(int id, TEntity entity, String token) {
        System.out.println("Update method in domain model");
        return false;
    }

    public boolean delete(int id, String token) {
        System.out.println("delete mehtod in domain model");
        return false;
    }
}
