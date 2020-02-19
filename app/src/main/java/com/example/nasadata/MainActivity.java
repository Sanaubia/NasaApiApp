package com.example.nasadata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }

    public void goWeatherActivity(View view) {
        Intent intent = new Intent(this,WeatherActivity.class);
        startActivity(intent);
    }

    public void PictureOfTheDay(View view) {
        Intent intent = new Intent(this,PictureOfTheDay.class);
        startActivity(intent);
    }


}
