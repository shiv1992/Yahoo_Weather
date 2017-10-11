package shivang.yahooweather.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import shivang.yahooweather.R;
import shivang.yahooweather.data.ForecastObject;

/**
 * Created by SHIVVVV on 10/10/2017.
 */

public class ForecastView extends AppCompatActivity{

    public static RecyclerView recyclerView;
    public static LinearLayoutManager linearLayoutManager;
    public static TextView textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forcast);

        recyclerView = (RecyclerView) findViewById(R.id.recView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        textTitle = (TextView) findViewById(R.id.textTitle);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ForecastPresenter forecastPresenter = new ForecastPresenter(getApplicationContext());

        forecastPresenter.setData();
    }
}
