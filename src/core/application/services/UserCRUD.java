package core.application.services;

import core.application.services.data.access.interfaces.modelwise.user.IUserDataAccess;
import core.application.services.emulator.DBEmulator;
import core.domain.model.entities.User;
import core.domain.model.entities._utilities.console;
import core.domain.services.interfaces.IUserCRUD;

import java.util.List;

class UserCRUD extends BaseCRUD<User> implements IUserCRUD {

    private IUserDataAccess dataAccess;

    UserCRUD(IUserDataAccess dataAccess){
        this.dataAccess = dataAccess;
    }

    @Override
    public boolean create(User entity) {
        return DBEmulator.addUser(entity);
    }

    @Override
    public User read(int id) {
        return DBEmulator.getUser(id);
    }

    @Override
    public boolean update(int id, User entity) {
        return DBEmulator.updateUser(id,entity);
    }

    @Override
    public boolean delete(int id) {
        return DBEmulator.deleteUser(id);
    }

    @Override
    public List<User> getAll(){
        return DBEmulator.getAllUsers();
    }

    @Override
    public boolean authenticate(String name, String Password) {
        // User user = DBEmulator.authenticateUser(name);
        User user = dataAccess.getUserForAuthentication(name);
//        System.out.println("returned from db: "+ user.getUserName() + " || " + user.getPassword());
        if(user.getPassword().equals(Password.trim())){
            console.log("user authenticated !!!");
            console.log("User is of the type: " + user.getType());
            return true;
        }else {
            return false;
        }
    }
}
