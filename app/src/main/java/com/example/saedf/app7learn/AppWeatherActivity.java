package com.example.saedf.app7learn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saedf.app7learn.ApiService.ApiService;
import com.example.saedf.app7learn.dataModel.WeatherInfo;

public class AppWeatherActivity extends AppCompatActivity implements ApiService.OnWeatherInfoRecived {
    private ApiService apiService;
    private TextView tvWeatherMain;
    private TextView tvWeatherDescription;
    private TextView tvTemp;
    private TextView tvHumidity;
    private TextView tvPressure;
    private TextView tvMinTemp;
    private TextView tvMaxTemp;
    private TextView tvWindSpeed;
    private TextView tvWindDegree;
    private TextView tvName;
    private ProgressBar progressBar;
    private static final String TAG = "AppWeatherActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_weather);
        apiService=new ApiService(this);
        initView();
        Button btnSendReqest=findViewById(R.id.btn_appWeatherActivity_sendRequset);
        btnSendReqest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiService.getCurrentWeather(AppWeatherActivity.this,"Tehran");
                progressBar.setVisibility(View.VISIBLE);
            }
        });

    }

    private void initView() {
        tvWeatherMain=findViewById(R.id.tv_appWeatherActivity_main);
        tvWeatherDescription=findViewById(R.id.tv_appWeatherActivity_description);
        progressBar=findViewById(R.id.progressbar_appweatherActivity);
        tvTemp=findViewById(R.id.tv_appWeatherActivity_temprature);
        tvHumidity=findViewById(R.id.tv_appWeatherActivity_humidity);
        tvPressure=findViewById(R.id.tv_appWeatherActivity_pressure);
        tvMinTemp=findViewById(R.id.tv_appWeatherActivity_MinTemp);
        tvMaxTemp=findViewById(R.id.tv_appWeatherActivity_MaxTemp);
        tvWindSpeed=findViewById(R.id.tv_appWeatherActivity_WindSpeed);
        tvWindDegree=findViewById(R.id.tv_appWeatherActivity_WindDegree);
        tvName=findViewById(R.id.tv_appWeatherActivity_name);
    }

    @Override
    public void onrecived(WeatherInfo weatherInfo) {
        if (weatherInfo!=null){
            tvWeatherMain.setText(weatherInfo.getWeatherMain());
            tvWeatherDescription.setText(weatherInfo.getWeatherDescription());
            tvTemp.setText((String.valueOf(weatherInfo.getWeatherTemprature())));
            tvHumidity.setText(String.valueOf(weatherInfo.getHumidity()));
            tvPressure.setText(String.valueOf(weatherInfo.getPressure()));
            tvMinTemp.setText((String.valueOf(weatherInfo.getMinTemprature())));
            tvMaxTemp.setText(String.valueOf(weatherInfo.getMaxTemprature()) );
            tvWindSpeed.setText(String.valueOf(weatherInfo.getMaxTemprature()));
            tvWindDegree.setText(String.valueOf(weatherInfo.getWindDegree()));
            tvName.setText(weatherInfo.getName());
            progressBar.setVisibility(View.INVISIBLE);
        }
        else {
            Toast.makeText(this, "خطا در دریافت اطلاعات", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
