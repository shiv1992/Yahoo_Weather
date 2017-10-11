package shivang.yahooweather.data;

import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import shivang.yahooweather.services.NetworkService;
import shivang.yahooweather.ui.ForecastView;
import shivang.yahooweather.ui.FrontPresenter;
import shivang.yahooweather.ui.FrontView;

/**
 * Created by SHIVVVV on 10/10/2017.
 */

public class DataService {

    private static Observer dataObserver;
    private static DataService dataService = null;

    public static DataService getDataService(){

        if(dataService == null)
        {
            dataService = new DataService();
            initializeObservers();
        }

        return dataService;
    }

    public static void initializeObservers(){

        dataObserver = new Observer() {

            public void fun(String input) throws JSONException {

                JSONObject jsonObject = new JSONObject(input);

                JSONObject obj1 = (JSONObject) jsonObject.get("query");
                int count = (Integer)obj1.get("count");
                Log.v("Response Count : ",String.valueOf(count));

                if(count == 0)
                {
                    FrontPresenter.throwToast();
                } else{

                    JSONObject results = (JSONObject) obj1.get("results");
                    JSONObject channel = (JSONObject) results.get("channel");
                    JSONObject item = (JSONObject) channel.get("item");
                    String description = (String) channel.get("description");
                    JSONArray forecast = item.getJSONArray("forecast");
                    DataStore.setDescription(description);
                    Log.v("Response Length : ",String.valueOf(forecast.length()));

                    List<ForecastObject> list = new ArrayList<>();

                    for(int i=0;i<3;i++)
                    {
                        JSONObject tmp = (JSONObject) forecast.get(i);
                        ForecastObject object = new ForecastObject();
                        object.setDate((String)tmp.get("date"));
                        object.setDay((String)tmp.get("day"));
                        object.setHigh((String)tmp.get("high"));
                        object.setLow((String)tmp.get("low"));
                        object.setText((String)tmp.get("text"));

                        Log.v("Response ANS: ",object.getDate());
                        Log.v("Response ANS: ",object.getDay());
                        Log.v("Response ANS: ",object.getHigh());
                        Log.v("Response ANS: ",object.getLow());
                        Log.v("Response ANS: ",object.getText());
                        list.add(object);
                    }

                    DataStore.setDataList(list);
                    Intent intent = new Intent(FrontView.context, ForecastView.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    FrontView.context.startActivity(intent);
                }
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull Object o) {
                try {
                    fun((String) o);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };
    }

    public void getForecast(final String zipcode){

        io.reactivex.Observable.just(zipcode).
                subscribeOn(Schedulers.io()).
                map(new Function<String, String>() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {

                        Log.v("Response BB: ","LL");
                        URL url = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+zipcode+"%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=");
                        OkHttpClient client = NetworkService.getClient();
                        Request request = new Request.Builder()
                                .url(url)
                                .build();

                        Response response = client.newCall(request).execute();
                        Log.v("Response AA: ","LL");
                        String resp = new String(response.body().bytes());
                        Log.v("Response: ",resp);
                        return resp;
                    }
                }).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(DataService.dataObserver);
    }
}
