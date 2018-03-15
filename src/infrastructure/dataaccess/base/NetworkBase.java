package infrastructure.dataaccess.base;

import okhttp3.OkHttpClient;

public class NetworkBase {
    protected String server = "http://localhost:3000/";
    protected OkHttpClient client;

    public NetworkBase(){
        client = new OkHttpClient();
    }
}
