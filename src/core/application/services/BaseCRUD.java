package core.application.services;

import core.domain.services.interfaces.ICRUD;

import java.util.List;

class BaseCRUD<Entity> implements ICRUD<Entity>{

    BaseCRUD(){

    }

    @Override
    public boolean create(Entity entity) {
        return false;
    }

    @Override
    public Entity read(int id) {
        return null;
    }

    @Override
    public boolean update(int id, Entity entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Entity> getAll() {
        return null;
    }
}
