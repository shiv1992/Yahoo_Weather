package shivang.yahooweather.ui;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import shivang.yahooweather.data.DataService;

/**
 * Created by SHIVVVV on 10/10/2017.
 */

public class FrontPresenter {

    private static Context context;

    public FrontPresenter(Context context){
        this.context = context;
    }

    public void getForecast(String zipcode){
        DataService.getDataService().getForecast(zipcode);
    }

    public static void throwToast(){
        Toast.makeText(context,"Invalid Zip-Code",Toast.LENGTH_SHORT).show();
    }

}
