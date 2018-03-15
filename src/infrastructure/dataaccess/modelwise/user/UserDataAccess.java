package infrastructure.dataaccess.modelwise.user;

import core.application.services.data.access.interfaces.modelwise.user.IUserDataAccess;
import core.domain.model.entities.User;
import core.domain.model.entities._utilities.console;
import infrastructure.dataaccess.base.NetworkBase;
import okhttp3.Response;

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

    @Override
    public User getUserForAuthentication(String username) {
        String url = server + userAutheticationApi;
        try {
            Response response = client.newCall(requestBuilder(url)).execute();
            if(response.code() == 200  && response.body() != null){
                String received = response.body().string();
                User result = gson.fromJson(received, User.class);
                return result;
            }
        }catch (Exception e){
            console.log("Error in network call");
        }
        return null;
    }
}
