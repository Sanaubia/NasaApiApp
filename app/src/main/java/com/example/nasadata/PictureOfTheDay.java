package com.example.nasadata;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;


public class PictureOfTheDay extends AppCompatActivity implements  PictureEngine.PictureOftheDayUrlAvailableInterface{


    PictureEngine engine = new PictureEngine(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictureoftheday);

        ProgressBar bar = (ProgressBar)findViewById(R.id.progressbar);
        bar.setVisibility(View.VISIBLE);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        engine.getPictureUrl();

    }


    protected void updateUI()
    {

        TextView text = (TextView) findViewById(R.id.ImageDescription);
        ImageView img = (ImageView) findViewById(R.id.imageView);
        String url = engine.getUrl();
        Picasso.with(this).load(url).into(img);

        String descpriotion = engine.getDescription();
        text.setText(descpriotion);

        ProgressBar bar = (ProgressBar)findViewById(R.id.progressbar);
        bar.setVisibility(View.INVISIBLE);
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
