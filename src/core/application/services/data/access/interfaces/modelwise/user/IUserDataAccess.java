package core.application.services.data.access.interfaces.modelwise.user;

import core.application.services.data.access.interfaces.extended.IExtendedDataAccess;
import core.domain.model.entities.User;

public interface IUserDataAccess  extends IExtendedDataAccess{
    public User getUserForAuthentication(String userName);
}
