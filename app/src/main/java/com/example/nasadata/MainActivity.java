package com.example.nasadata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    Button weather;
    Button astroPic;
    Button neo;
    //Intent intent;

    Intent svc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        astroPic = (Button) findViewById(R.id.btn_astroPictureOfTheDay);
        neo = (Button) findViewById(R.id.btn_NEO);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        PlayBackgroundSound();


        /*
        intent = new Intent(MainActivity.this, BackgroundSoundService.class);
        startService(intent);
        */
        //PlayBackgroundSound();

        ImageView imageView = (ImageView) findViewById(R.id.nasaLogo);
        Glide.with(this).load("https://www.nasa.gov/sites/default/files/thumbnails/image/pia23408.jpg").into(imageView);



    }


    public void PictureOfTheDay(View view) {
        Intent intent = new Intent(this,PictureOfTheDay.class);
        startActivity(intent);
    }

    public void NasaPictures(View view) {
        Intent intent = new Intent(this,NasaPictures.class);
        startActivity(intent);
    }

    public void PlayBackgroundSound() {
       // Intent intent = new Intent(this, BackgroundSoundService.class);
       // startService(intent);

        svc=new Intent(this, BackgroundSoundService.class);
        startService(svc);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //stopService(svc);
        //stopService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(svc);

    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(svc);
    }

    @Override
    protected void onStop() {
        super.onStop();
       // stopService(svc);
    }

    /*
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(!hasFocus) {
            //do anything you want here
            Toast.makeText(MainActivity.this,"Activity changed",Toast.LENGTH_SHORT).show();
            stopService(svc);
        }
    }
    */
}
