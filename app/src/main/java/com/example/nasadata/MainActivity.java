package com.example.nasadata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    Button weather;
    Button astroPic;
    Button neo;



   Intent intent;
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

        intent = new Intent(MainActivity.this, BackgroundSoundService.class);
        startService(intent);


        ImageView imageView = (ImageView) findViewById(R.id.nasaLogo);
        Glide.with(this).load("https://www.nasa.gov/sites/default/files/thumbnails/image/pia23408.jpg").into(imageView);



    }



    public void goWeatherActivity(View view) {
        Intent intent = new Intent(this,WeatherActivity.class);
        startActivity(intent);
    }

    public void PictureOfTheDay(View view) {
        Intent intent = new Intent(this,PictureOfTheDay.class);
        startActivity(intent);
    }

    public void PlayBackgroundSound(View view) {
        Intent intent = new Intent(MainActivity.this, BackgroundSoundService.class);
        startService(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(intent);
    }
}
