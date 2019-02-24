package com.example.chrysus.home.task;

import android.os.AsyncTask;
import android.util.Log;

import com.example.chrysus.home.model.News;
import com.example.chrysus.util.ConfigReader;
import com.example.chrysus.util.request.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

public class NewsAsyncTask extends AsyncTask<String,Void, ArrayList<News>> {
    @Override
    protected ArrayList<News> doInBackground(String... strings) {
        ArrayList<News> news = new ArrayList<>();
        Log.d("Noer", strings[0]);
        try {
            String result;
            result = Request.get(strings[0]);
            news = createNewsList(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return news ;
    }

    private ArrayList<News> createNewsList(String jsonResult) throws JSONException {
        JSONObject json = new JSONObject(jsonResult);
        ArrayList<News> newsList = new ArrayList<>();
        if (json.getBoolean("success")){
            JSONArray data = json.getJSONArray("data");
            for (int i =0; i<data.length(); i++){
                JSONObject newsJson = (JSONObject) data.get(i);
                News news = new News(newsJson);
                newsList.add(news);
            }
        }
        return newsList;
    }
}
