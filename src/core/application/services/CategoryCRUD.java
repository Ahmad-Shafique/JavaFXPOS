package core.application.services;

import core.application.services.data.access.interfaces.modelwise.category.ICategoryDataAccess;
import core.application.services.emulator.DBEmulator;
import core.domain.model.entities.*;
import core.domain.services.interfaces.ICategoryCRUD;

import java.util.List;

class CategoryCRUD extends BaseCRUD<Category> implements ICategoryCRUD {

    private ICategoryDataAccess dataAccess;

    CategoryCRUD(ICategoryDataAccess dataAccess){
        this.dataAccess = dataAccess;
    }

    @Override
    public boolean create(Category entity) {
        return DBEmulator.addCategory(entity);
    }

    @Override
    public Category read(int id) {
        return DBEmulator.getCategory(id);
    }

    @Override
    public boolean update(int id, Category entity) {
        return DBEmulator.updateCategory(id,entity);
    }

    @Override
    public boolean delete(int id) {
        return DBEmulator.deleteCategory(id);
    }

    @Override
    public List<Category> getAll(){
        return DBEmulator.getAllCategories();
    }

    @Override
    public List<String> getAllCategoryNames() {
        return DBEmulator.getAllCategoryName();
    }
}
