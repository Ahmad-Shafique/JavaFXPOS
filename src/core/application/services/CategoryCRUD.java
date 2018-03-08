package core.application.services;

import core.application.services.emulator.DBEmulator;
import core.domain.model.entities.*;

import java.util.List;

class CategoryCRUD extends BaseCRUD<Category>  {


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
}
