package br.com.impacta.curso.exercicios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class Ex04Activity extends AppCompatActivity {

    private TextView tv_time, tv_date, tv_temperature, tv_locationAndWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex04);

        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_temperature = (TextView) findViewById(R.id.tv_temperature);
        tv_locationAndWeather = (TextView) findViewById(R.id.tv_locationAndWeather);

        WeatherService service = new WeatherService();


        tv_time.setText(service.getTime());
        tv_date.setText(service.getDate());
        tv_temperature.setText(service.getTemperature());
        tv_locationAndWeather.setText(service.getLocation() + " " + service.getWeatherDescription());
    }


    public void previousActivity(View view) {
        Intent intent = new Intent(Ex04Activity.this, Ex03Activity.class);
        startActivity(intent);
    }
}
