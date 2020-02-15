package com.example.nasadata;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;


public class PictureOfTheDay extends AppCompatActivity implements  PictureEngine.PictureOftheDayUrlAvailableInterface{


    PictureEngine engine = new PictureEngine(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictureoftheday);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        engine.getPictureUrl();

    }


    protected void updateUI()
    {

       // TextView temperatureTextView = (TextView) findViewById(R.id.textView);
       // String formatted = String.format(getString(R.string.temp), engine.getTemperature());

        //temperatureTextView.setText(formatted);
        ImageView img = (ImageView) findViewById(R.id.imageView);
        String url = engine.getUrl();
        Picasso.with(this).load(url).into(img);
    }
    @Override
    public void urlAvailable() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateUI();
            }
        });
    }


}
