package core.application.services;

import core.application.services.emulator.DBEmulator;
import core.domain.model.entities.Category;
import core.domain.model.entities.Item;

import java.util.List;

class ItemCRUD extends BaseCRUD<Item> {

    @Override
    public boolean create(Item entity) {
        return DBEmulator.addItem(entity);
    }

    @Override
    public Item read(int id) {
        return DBEmulator.getItem(id);
    }

    @Override
    public boolean update(int id, Item entity) {
        return DBEmulator.updateItem(id,entity);
    }

    @Override
    public boolean delete(int id) {
        return DBEmulator.deleteItem(id);
    }

    @Override
    public List<Item> getAll(){
        return DBEmulator.getAllItems();
    }
}
