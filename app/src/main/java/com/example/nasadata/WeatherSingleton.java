package com.example.nasadata;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class WeatherSingleton {


    private static  WeatherSingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mContext;

    private WeatherSingleton(Context context)
    {
        mContext = context;
        requestQueue = getRequestQueue();
    }


    public RequestQueue getRequestQueue()
    {
        if(requestQueue== null)
        {
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized WeatherSingleton getInstance(Context context)
    {
        if(mInstance == null)
        {
            mInstance = new WeatherSingleton(context);
        }
        return  mInstance;
    }

    public<T> void addToRequestqueue(Request<T> request)
    {
        requestQueue.add(request);
    }
}
