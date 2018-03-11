package infrastructure.dataaccess.modelwise.category;

import core.application.services.data.access.interfaces.modelwise.category.ICategoryDataAccess;
import infrastructure.dataaccess.base.NetworkBase;

public class CategoryDataAccess extends NetworkBase implements ICategoryDataAccess {

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public boolean addToDatabase(Object obj) {
        return false;
    }

    @Override
    public Object[] getAll() {
        return new Object[0];
    }
}
