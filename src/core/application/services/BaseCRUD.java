package core.application.services;

import core.domain.services.interfaces.ICRUD;

class BaseCRUD<Entity> implements ICRUD<Entity>{

    protected BaseCRUD(){

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
    public boolean delete(Entity entity) {
        return false;
    }
}
