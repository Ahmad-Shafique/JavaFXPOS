package core.domain.model.interfaces;

import java.util.Collection;
import java.util.function.Function;

public interface IDomainModel<TEntity extends Object> {
    boolean add(TEntity entity, String token);
    TEntity get(int id, String token);
    Collection<TEntity> getAll(String token);
    boolean update(int id, TEntity entity, String token);
    boolean delete(int id, String token);
}
