package infrastructure.dataaccess.modelwise.user;

import core.application.services.data.access.interfaces.modelwise.user.IUserDataAccess;
import infrastructure.dataaccess.base.NetworkBase;

public class UserDataAccess extends NetworkBase implements IUserDataAccess {
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
