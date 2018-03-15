package infrastructure.dataaccess.modelwise.category;

import core.application.services.data.access.interfaces.modelwise.category.ICategoryDataAccess;
import core.domain.model.entities.Category;
import infrastructure.dataaccess._utilities.NewCategory;
import core.domain.model.entities._utilities.console;
import infrastructure.dataaccess.base.NetworkBase;
import okhttp3.Response;

public class CategoryDataAccess extends NetworkBase implements ICategoryDataAccess {

    @Override
    public Object getById(int id) {
        String url = server + categoryApi + "/" + id;
        try {
            Response response = client.newCall(requestBuilder(url)).execute();
            if(response.isSuccessful() && response.code() == 200 ){
                String received = response.body().string();
                Category result = gson.fromJson(received, Category.class);
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
            Category item = (Category) obj;
            String json = gson.toJson(item);
            String url = server + categoryApi + "/" + item.getId();
            // console.log("item is: " + json);

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
            String url = server + categoryApi + "/" + id;
            Response response = client.newCall(deleteRequestBuilder(url)).execute();
            return true;
        }catch (Exception e){
            console.log("Error in category delete request");
        }
        return false;
    }

    @Override
    public boolean addToDatabase(Object obj) {

        try{
            Category rcv = (Category) obj;
            NewCategory item = new NewCategory(rcv.getName(),rcv.getDescription());
            String json = gson.toJson(item);
            String url = server + categoryApi;
            // console.log("item is: " + json);

            Response response = client.newCall(postRequestBuilder(url,json)).execute();
            return true;
        }catch (Exception e){
            console.log("Error in post request");
        }
        return false;
    }

    @Override
    public Object[] getAll() {
        String url = server + categoryApi;
        try {
            Response response = client.newCall(requestBuilder(url)).execute();
            if(response.isSuccessful() && response.code() == 200 ){
                String received = response.body().string();
                Category[] result = gson.fromJson(received, Category[].class);
                return result;
            }
        }catch (Exception e){
            console.log("Error in network call");
        }
        return null;
    }

    @Override
    public String[] getAllCategoryNames() {
        Category[] categories = (Category[]) this.getAll();
        int length = categories.length;
        String[] names = new String[length];

        for(int i=0; i<length ; i++){
            names[i] = categories[i].getName();
        }

        return names;
    }
}
