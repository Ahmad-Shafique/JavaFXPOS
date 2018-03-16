package core.application.services;

import core.application.services.data.access.interfaces.modelwise.category.ICategoryDataAccess;
import core.domain.model.entities.*;
import core.domain.services.interfaces.ICategoryCRUD;
import core.domain.services.interfaces.dataload.IDataLoad;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

class CategoryCRUD extends BaseCRUD<Category> implements ICategoryCRUD {

    private ICategoryDataAccess dataAccess;
    private IDataLoad dataLoader;

    CategoryCRUD(ICategoryDataAccess dataAccess, IDataLoad dataLoader){

        this.dataAccess = dataAccess;
        this.dataLoader = dataLoader;
    }

    @Override
    public boolean create(Category entity) {
        return dataAccess.addToDatabase(entity);
    }

    @Override
    public Category read(int id) {

        dataLoader.pushData( dataAccess.getById(id));
        return null;
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
        try{
            TimeUnit.SECONDS.sleep(10);
        }catch (Exception e){

        }
        dataLoader.pushData(Arrays.asList((Category[]) dataAccess.getAll()));
        return null;
    }

    @Override
    public List<String> getAllCategoryNames() {
        dataLoader.pushData( Arrays.asList(dataAccess.getAllCategoryNames()));
        return null;
    }
}
