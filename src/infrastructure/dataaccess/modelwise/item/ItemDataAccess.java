package infrastructure.dataaccess.modelwise.item;

import core.application.services.data.access.interfaces.modelwise.item.IItemDataAccess;
import infrastructure.dataaccess.base.NetworkBase;

public class ItemDataAccess extends NetworkBase implements IItemDataAccess{
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
