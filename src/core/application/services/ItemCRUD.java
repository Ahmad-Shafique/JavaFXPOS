package core.application.services;

import core.application.services.data.access.interfaces.modelwise.item.IItemDataAccess;
import core.domain.model.entities.Item;

import java.util.Arrays;
import java.util.List;

class ItemCRUD extends BaseCRUD<Item> {

    private IItemDataAccess dataAccess;

    ItemCRUD(IItemDataAccess dataAccess){
        this.dataAccess=dataAccess;
    }

    @Override
    public boolean create(Item entity) {

        // return DBEmulator.addItem(entity);
        return dataAccess.addToDatabase(entity);
    }

    @Override
    public Item read(int id) {
        // return DBEmulator.getItem(id);
        return (Item) dataAccess.getById(id);
    }

    @Override
    public boolean update(int id, Item entity) {

        // return DBEmulator.updateItem(id,entity);
        return dataAccess.addToDatabase(entity);
    }

    @Override
    public boolean delete(int id) {

        // return DBEmulator.deleteItem(id);
        return dataAccess.deleteById(id);
    }

    @Override
    public List<Item> getAll(){

        // return DBEmulator.getAllItems();
        Item[] result = (Item[]) dataAccess.getAll();
        return Arrays.asList(result);
    }
}
