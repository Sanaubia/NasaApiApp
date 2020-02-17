package com.example.nasadata;

import android.util.Log;

import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

public class PictureEngine implements HTTPGetThread.OnRequestDoneInterface {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDateTime now = LocalDateTime.now();



    // This interface is used to report data back to UI
    public interface PictureOftheDayUrlAvailableInterface
    {
        // This method is called back in background thread.
        public void urlAvailable();
    }


    protected String url;
    protected PictureOftheDayUrlAvailableInterface uiCallback;

    // Constructor
    public PictureEngine(PictureOftheDayUrlAvailableInterface callbackInterface)
    {
        this.uiCallback = callbackInterface;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public void getPictureUrl()
    {
        //String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=65dbec3aae5e5bf9000c7a956c8b76f6";
        //String url =  "https://api.nasa.gov/planetary/apod?api_key=eDKy0MMzXpklGv8pdPedrQiluatiCgjPOpKax8Yu";
        String url = "https://api.nasa.gov/neo/rest/v1/feed?start_date="+dtf.format(now)+"&api_key=eDKy0MMzXpklGv8pdPedrQiluatiCgjPOpKax8Yu";

        HTTPGetThread getter = new HTTPGetThread(url, this);
        getter.setListener(this);
        getter.start();
    }



    @Override
    public void onRequestDone(String data)
    {
        Log.d("LABRA dataa tulee: ", data);
        try
        {
            /*
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));
            */
            Map<String, Object> parsed = JsonUtils.jsonToMap(new JSONObject(data));
            String url = String.valueOf(parsed.get("url"));
            this.url = url;


            /*
            //
            // No proper error handling here:
            Map<String, Object> parsed = JsonUtils.jsonToMap(new JSONObject(data));
           Map<String, Object> mainElement = (Map) parsed.get("near_earth_objects");
           parsed = JsonUtils.jsonToMap(new JSONObject(mainElement));

            ArrayList<Map<String, Object>> array = (ArrayList<Map<String, Object>>)parsed.get(dtf.format(now));
            Map<String, Object> weatherElement = array.get(0);
            String name = String.valueOf(weatherElement.get("name"));
           // Map<String, Object> days = (Map) parsed.get(dtf.format(now));
           // parsed = JsonUtils.jsonToMap(new JSONObject(days));
           //double tempInC = temp - KELVIN_CONVERT;
           // this.temperature = String.format("%.1f", tempInC);
            //ArrayList<Map<String, Object>> array = (ArrayList<Map<String, Object>>)parsed.get("weather");
            //Map<String, Object> weatherElement = array.get(0);
            //iconId = (String)weatherElement.get("icon");


             */
            uiCallback.urlAvailable();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void onError(String error) {

    }


}
