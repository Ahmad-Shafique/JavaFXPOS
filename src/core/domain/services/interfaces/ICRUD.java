package core.domain.services.interfaces;

public interface ICRUD<Entity> {
    boolean create(Entity entity);
    Entity read(int id);
    boolean update(int id, Entity entity);
    boolean delete(Entity entity);
}
