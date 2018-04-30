package com.example.saedf.app7learn.ApiService;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.saedf.app7learn.dataModel.WeatherInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiService {
    private Context context;
    private static final String TAG = "ApiService";
    public ApiService(Context context) {
        this.context = context;
    }

    public void getCurrentWeather(final OnWeatherInfoRecived onWeatherInfoRecived, String cityname) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "http://api.openweathermap.org/data/2.5/weather?q=" + cityname + "&apikey=fe6621c52d7eb66a160574fbccbe36a2",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                onWeatherInfoRecived.onrecived(parseResponseToWeatherInfo(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: "+error.toString() );
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(800,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(jsonObjectRequest);

    }

    private WeatherInfo parseResponseToWeatherInfo(JSONObject response) {
        WeatherInfo weatherInfo = new WeatherInfo();
        try {
            JSONArray jsonArrayWeather = response.getJSONArray("weather");
            JSONObject jsonObjectweather = jsonArrayWeather.getJSONObject(0);
            weatherInfo.setWeatherMain(jsonObjectweather.getString("main"));
            weatherInfo.setWeatherDescription(jsonObjectweather.getString("description"));
            weatherInfo.setWeatherTemprature((float) jsonObjectweather.getDouble("temp"));
            weatherInfo.setHumidity((float) jsonObjectweather.getDouble("humidity"));
            weatherInfo.setPressure((int) jsonObjectweather.getDouble("pressure"));
            weatherInfo.setMinTemprature((float) jsonObjectweather.getDouble("temp_min"));
            weatherInfo.setMaxTemprature((float) jsonObjectweather.getDouble("temp_max"));
            weatherInfo.setWindSpeed((float) jsonObjectweather.getDouble("speed"));
            weatherInfo.setWindDegree((float) jsonObjectweather.getDouble("deg"));
            weatherInfo.setName(jsonObjectweather.getString("name"));
            return weatherInfo;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    public interface OnWeatherInfoRecived {
        void onrecived(WeatherInfo weatherInfo);
    }
}
