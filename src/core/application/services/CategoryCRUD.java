package core.application.services;

import core.application.services.data.access.interfaces.modelwise.category.ICategoryDataAccess;
import core.application.services.emulator.DBEmulator;
import core.domain.model.entities.*;
import core.domain.services.interfaces.ICategoryCRUD;

import java.util.Arrays;
import java.util.List;

class CategoryCRUD extends BaseCRUD<Category> implements ICategoryCRUD {

    private ICategoryDataAccess dataAccess;

    CategoryCRUD(ICategoryDataAccess dataAccess){
        this.dataAccess = dataAccess;
    }

    @Override
    public boolean create(Category entity) {
        return dataAccess.addToDatabase(entity);
    }

    @Override
    public Category read(int id) {
        return (Category) dataAccess.getById(id);
    }

    @Override
    public boolean update(int id, Category entity) {
        return dataAccess.update(entity);
    }

    @Override
    public boolean delete(int id) {
        return dataAccess.deleteById(id);
    }

    @Override
    public List<Category> getAll(){
        return Arrays.asList((Category[]) dataAccess.getAll());
    }

    @Override
    public List<String> getAllCategoryNames() {
        return Arrays.asList(dataAccess.getAllCategoryNames());
    }
}
