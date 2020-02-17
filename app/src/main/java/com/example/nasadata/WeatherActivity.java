package com.example.nasadata;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;


public class WeatherActivity extends AppCompatActivity {

    TextView degrees, wind;
    String jsonUrl = "https://api.nasa.gov/insight_weather/?api_key=eDKy0MMzXpklGv8pdPedrQiluatiCgjPOpKax8Yu&feedtype=json&ver=1.0";
    //JSONArray sol = null;
    String celciusSign = "°C";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();

        degrees = (TextView) findViewById(R.id.degrees);
        wind = (TextView) findViewById(R.id.wind);

        JsonRequest();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
    public void JsonRequest(){
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, jsonUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("sol_keys");
                            //Log.d("RESPONSE",response.toString());

                            //Log.d("temp", response.getString("435"));
                            String temp = response.getJSONObject(jsonArray.getString(6)).getJSONObject("AT").getString("av");
                            degrees.setText("\nAvg Temp: "+temp + celciusSign +"\n");

                            String windSpeed = response.getJSONObject(jsonArray.getString(6)).getJSONObject("HWS").getString("av");
                            Log.d("WIND",response.getJSONObject(jsonArray.getString(6)).getJSONObject("HWS").getString("av"));
                            wind.setText("Avg Wind: "+windSpeed + "m/s");
                            Log.d("TEMPERATURE",temp);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WeatherActivity.this, "Something went wrong",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });
        WeatherSingleton.getInstance(WeatherActivity.this).addToRequestqueue(jsonObjectRequest);
    }
}