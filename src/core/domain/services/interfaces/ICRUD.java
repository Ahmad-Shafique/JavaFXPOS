package core.domain.services.interfaces;

import java.util.List;

public interface ICRUD<Entity> {
    boolean create(Entity entity);
    Entity read(int id);
    boolean update(int id, Entity entity);
    boolean delete(int id);
    List<Entity> getAll();
}
