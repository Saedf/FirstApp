package com.example.saedf.app7learn.ApiService;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.saedf.app7learn.dataModel.Post;
import com.example.saedf.app7learn.dataModel.WeatherInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiService {
    private Context context;
    private static final String TAG = "ApiService";
    private static final String URL_ADDRESS = "http://192.168.1.102:8080/7learn/getNewsPost.php";

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
                Log.e(TAG, "onErrorResponse: " + error.toString());
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(800, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
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

            JSONObject jsonObjectMain = response.getJSONObject("main");
            weatherInfo.setWeatherTemprature((float) jsonObjectMain.getDouble("temp"));
            weatherInfo.setHumidity((float) jsonObjectMain.getDouble("humidity"));
            weatherInfo.setPressure((int) jsonObjectMain.getDouble("pressure"));
            weatherInfo.setMinTemprature((float) jsonObjectMain.getDouble("temp_min"));
            weatherInfo.setMaxTemprature((float) jsonObjectMain.getDouble("temp_max"));

            JSONObject jsonObjectWind = response.getJSONObject("wind");
            weatherInfo.setWindSpeed((float) jsonObjectWind.getDouble("speed"));
            weatherInfo.setWindDegree((float) jsonObjectWind.getDouble("deg"));

            weatherInfo.setName(" وضعیت آب و هوای استان:  " + response.getString("name"));
            return weatherInfo;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    public interface OnWeatherInfoRecived {
        void onrecived(WeatherInfo weatherInfo);
    }

    public void getPosts(final OnRecivedNews onRecivedNews) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "http://172.20.200.45:8012/7learn/getposts.php", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        onRecivedNews.onrecived(parseResponseToNews(response));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: "+error.toString() );

            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(800, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(jsonArrayRequest);

    }

    private List<Post> parseResponseToNews(JSONArray response) {
        List<Post> postList = new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            Post post = new Post();
            try {
                JSONObject jsonObject = response.getJSONObject(i);
                post.setId(jsonObject.getInt("id"));
                post.setTitle(jsonObject.getString("title"));
                post.setContent(jsonObject.getString("content"));
                post.setDate(jsonObject.getString("dateNews"));
                post.setImagenewsUrl(jsonObject.getString("image_url"));
                postList.add(post);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return postList;

    }

    public interface OnRecivedNews {
        void onrecived(List<Post> postList);
    }
}
