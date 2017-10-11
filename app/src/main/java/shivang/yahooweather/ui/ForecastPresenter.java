package shivang.yahooweather.ui;

import android.content.Context;

import shivang.yahooweather.data.DataStore;

import static shivang.yahooweather.ui.ForecastView.recyclerView;

/**
 * Created by SHIVVVV on 10/10/2017.
 */

public class ForecastPresenter {

    private static Context context;

    public ForecastPresenter(Context context){
        this.context = context;
    }

    public void setData(){

        ForecastView.textTitle.setText(DataStore.getDescription());
        ViewAdapter recyclerViewAdapter = new ViewAdapter(context, DataStore.getDataList());
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
