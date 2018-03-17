package infrastructure.dataaccess.base;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class NetworkBase {
    protected String server = "http://localhost:3000/";
    protected String userAutheticationApi = "User/1";
    protected String itemApi = "Item";
    protected String categoryApi = "Category";
    protected String invoiceApi = "Invoice";

    protected MediaType JSON ;

    protected OkHttpClient client;
    protected Gson gson;

    public NetworkBase(){
        client = new OkHttpClient();
        gson = new Gson();
        JSON = MediaType.parse("application/json; charset=utf-8");
    }

    protected Request requestBuilder(String url){
        return new Request.Builder()
                            .url(url)
                            .build();
    }

    protected Request postRequestBuilder(String url,String json){
        RequestBody body = RequestBody.create(JSON, json);
        return new Request.Builder()
                .url(url)
                .post(body)
                .build();
    }

    protected Request putRequestBuilder(String url,String json){
        RequestBody body = RequestBody.create(JSON, json);
        return new Request.Builder()
                .url(url)
                .put(body)
                .build();
    }

    protected Request deleteRequestBuilder(String url){
        return new Request.Builder()
                .url(url)
                .delete()
                .build();
    }
}
