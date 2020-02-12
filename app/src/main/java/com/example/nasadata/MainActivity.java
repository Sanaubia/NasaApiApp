package com.example.nasadata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button weather;
    Button astroPic;
    Button neo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weather = (Button) findViewById(R.id.btn_weather);
        astroPic = (Button) findViewById(R.id.btn_astroPictureOfTheDay);
        neo = (Button) findViewById(R.id.btn_NEO);

    }

    public void goWeatherActivity(View view) {
        Intent intent = new Intent(this,WeatherActivity.class);
        startActivity(intent);
    }
}
