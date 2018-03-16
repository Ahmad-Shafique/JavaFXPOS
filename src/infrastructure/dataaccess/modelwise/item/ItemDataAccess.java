package infrastructure.dataaccess.modelwise.item;

import core.application.services.data.access.interfaces.modelwise.item.IItemDataAccess;
import core.domain.model.entities.Item;
import infrastructure.dataaccess._utilities.NewItem;
import core.domain.model.entities._utilities.console;
import infrastructure.dataaccess.base.NetworkBase;
import okhttp3.Response;

public class ItemDataAccess extends NetworkBase implements IItemDataAccess{
    @Override
    public Object getById(int id) {
        String url = server + itemApi + "/" + id;
        try {
            Response response = client.newCall(requestBuilder(url)).execute();
            if(response.isSuccessful() && response.code() == 200 ){
                String received = response.body().string();
                Item result = gson.fromJson(received, Item.class);
                return result;
            }
        }catch (Exception e){
            console.log("Error in network call");
        }
        return null;
    }

    @Override
    public boolean update(Object obj) {
        try{
            Item item = (Item) obj;
            String json = gson.toJson(item);
            String url = server + itemApi + "/" + item.getId();
            console.log("item is: " + json);

            Response response = client.newCall(putRequestBuilder(url,json)).execute();
            return true;
        }catch (Exception e){
            console.log("Error in put request");
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try{
            String url = server + itemApi + "/" + id;

            Response response = client.newCall(deleteRequestBuilder(url)).execute();
            return true;
        }catch (Exception e){
            console.log("Error in delete request");
        }
        return false;
    }

    @Override
    public boolean addToDatabase(Object obj) {
        try{
            Item rcv = (Item) obj;
            NewItem item = new NewItem(rcv.getCategoryId(),rcv.getName(),rcv.getPrice(),rcv.getQuantityAvailable(),rcv.getDescription());
            String json = gson.toJson(item);
            String url = server + itemApi;
            console.log("item is: " + json);

            Response response = client.newCall(postRequestBuilder(url,json)).execute();
            return true;
        }catch (Exception e){
            console.log("Error in post request");
        }
        return false;
    }

    @Override
    public Object[] getAll() {
        String url = server + itemApi;
        try {
            Response response = client.newCall(requestBuilder(url)).execute();
            if(response.isSuccessful() && response.code() == 200 ){
                String received = response.body().string();
                Item[] result = gson.fromJson(received, Item[].class);
                console.log("Items fetched from server");
                return result;
            }
        }catch (Exception e){
            console.log("Error in network call");
        }
        return null;
    }
}
