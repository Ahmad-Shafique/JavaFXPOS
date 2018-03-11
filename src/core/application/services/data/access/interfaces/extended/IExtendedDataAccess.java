package core.application.services.data.access.interfaces.extended;

import core.application.services.data.access.interfaces.base.IBaseDataAccess;

public interface IExtendedDataAccess extends IBaseDataAccess {
    Object getById(int id);
    boolean update(Object obj);
    boolean deleteById(int id);
    boolean addToDatabase(Object obj);
}
