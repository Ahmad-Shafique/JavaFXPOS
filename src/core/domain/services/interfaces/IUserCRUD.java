package core.domain.services.interfaces;

import core.domain.model.entities.User;

public interface IUserCRUD extends ICRUD<User>{
    public boolean authenticate(String name, String Password);
}
