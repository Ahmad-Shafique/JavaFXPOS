package core.application.services;

import core.application.services.data.access.interfaces.modelwise.item.IItemDataAccess;
import core.domain.model.entities.Item;
import core.domain.model.entities._utilities.console;
import core.domain.services.interfaces.dataload.IDataLoad;

import java.util.Arrays;
import java.util.List;

class ItemCRUD extends BaseCRUD<Item> {

    private IItemDataAccess dataAccess;
    private IDataLoad dataLoader;

    ItemCRUD(IItemDataAccess dataAccess, IDataLoad dataLoader){

        this.dataAccess=dataAccess;
        this.dataLoader = dataLoader;
    }

    @Override
    public boolean create(Item entity) {

        // return DBEmulator.addItem(entity);
        return dataAccess.addToDatabase(entity);
    }

    @Override
    public Item read(int id) {
        // return DBEmulator.getItem(id);
        dataLoader.pushData( dataAccess.getById(id) );
        return null;
    }

    @Override
    public boolean update(int id, Item entity) {

        // return DBEmulator.updateItem(id,entity);

        return dataAccess.update(entity);
    }

    @Override
    public boolean delete(int id) {

        // return DBEmulator.deleteItem(id);
        return dataAccess.deleteById(id);
    }

    @Override
    public List<Item> getAll(){

        // return DBEmulator.getAllItems();

        dataLoader.pushData( Arrays.asList(dataAccess.getAll()) );
        // console.log("results received from data access");
        return null;
    }
}
