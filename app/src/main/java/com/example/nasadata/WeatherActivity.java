package com.example.nasadata;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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

    TextView degrees, degreesMin, wind;
    Button yesterdayWeather, todayWeather;
    String jsonUrl = "https://api.nasa.gov/insight_weather/?api_key=eDKy0MMzXpklGv8pdPedrQiluatiCgjPOpKax8Yu&feedtype=json&ver=1.0";
    //JSONArray sol = null;
    String celciusSign = "°C";
    private ProgressBar progressBar;

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
        //Calling JsonRequest function
        JsonRequest();

        degrees = (TextView) findViewById(R.id.degrees);
        degreesMin = (TextView) findViewById(R.id.degreesMin);
        wind = (TextView) findViewById(R.id.wind);
        yesterdayWeather = (Button) findViewById(R.id.btn_yesterdayWeather);
        todayWeather = (Button) findViewById(R.id.btn_todayWeather);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        //Set onClick Listeners and action for that
        yesterdayWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonRequestYesterday();
            }
        });

        todayWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonRequest();
            }
        });

    }
    public void JsonRequest(){
        //Make a new JsonObjectRequest and that get's inside jsonUrl where we get the Json Data from Nasa
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, jsonUrl, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.GONE);

                        try {
                            //Using JsonArray. We put sol_keys inside the array. There is 7 keys (7days data)
                            JSONArray jsonArray = response.getJSONArray("sol_keys");

                            //Reading the data from response-data to String and using index 6 to get newest temperature data. Showing the data in TextView.
                            String temp = response.getJSONObject(jsonArray.getString(6)).getJSONObject("AT").getString("av");
                            degrees.setText("\nTemp (avg): "+temp + celciusSign +"\n");
                            //Get and show the minimum temperature
                            String tempMin = response.getJSONObject(jsonArray.getString(6)).getJSONObject("AT").getString("mn");
                            degreesMin.setText("Temp (min): "+tempMin + celciusSign +"\n");
                            //Get and show windSpeed
                            String windSpeed = response.getJSONObject(jsonArray.getString(6)).getJSONObject("HWS").getString("av");
                            wind.setText("Wind (avg): "+windSpeed + "m/s");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            //Error handling
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(WeatherActivity.this, "Something went wrong",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });
        WeatherSingleton.getInstance(WeatherActivity.this).addToRequestqueue(jsonObjectRequest);
    }

    public void JsonRequestYesterday(){
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, jsonUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("sol_keys");
                            //Reading the data from response-data to String and using index 5 to get yesterday temperature data. Showing the data in TextView.
                            String temp = response.getJSONObject(jsonArray.getString(5)).getJSONObject("AT").getString("av");
                            degrees.setText("\nTemp (avg): "+temp + celciusSign +"\n");

                            String tempMin = response.getJSONObject(jsonArray.getString(5)).getJSONObject("AT").getString("mn");
                            degreesMin.setText("Temp (min): "+tempMin + celciusSign +"\n");

                            String windSpeed = response.getJSONObject(jsonArray.getString(5)).getJSONObject("HWS").getString("av");
                            wind.setText("Wind (avg): "+windSpeed + "m/s");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(WeatherActivity.this, "Something went wrong",Toast.LENGTH_LONG).show();

            }
        });
        WeatherSingleton.getInstance(WeatherActivity.this).addToRequestqueue(jsonObjectRequest);

    }
}