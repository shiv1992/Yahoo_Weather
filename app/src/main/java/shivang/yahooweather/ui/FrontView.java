package shivang.yahooweather.ui;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import shivang.yahooweather.R;

public class FrontView extends AppCompatActivity {

    FrontPresenter frontPresenter;
    public static Application application;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

        context = getApplicationContext();
        application = getApplication();

        final EditText zipcodeText = (EditText)findViewById(R.id.zipcodeText);
        Button buttonGet = (Button)findViewById(R.id.weatherButton);

        frontPresenter = new FrontPresenter(getApplicationContext());

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String zipText = zipcodeText.getText().toString();

                if(zipText.equals("") || !android.text.TextUtils.isDigitsOnly(zipText))
                {
                    Toast.makeText(getApplicationContext(),"Enter Zip-Code",Toast.LENGTH_SHORT).show();
                } else{
                    frontPresenter.getForecast(zipText);
                }
            }
        });

    }

}
