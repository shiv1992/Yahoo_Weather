package shivang.yahooweather.services;

import okhttp3.OkHttpClient;

/**
 * Created by SHIVVVV on 10/10/2017.
 */

public class NetworkService {

    private static OkHttpClient client = null;

    public static OkHttpClient getClient(){

        if(client == null){
            client = new OkHttpClient();
        }

        return client;
    }
}
