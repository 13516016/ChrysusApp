package com.example.chrysus.profile;

import android.os.AsyncTask;

import com.example.chrysus.home.model.User;
import com.example.chrysus.util.request.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ProfileAsyncTask extends AsyncTask<String, Void, User> {
    @Override
    protected User doInBackground(String... strings) {
        User user = null;
        try {
            String result;
            result = Request.get(strings[0]);
            user  = createUser(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }

    private User createUser(String jsonResult) throws JSONException {
        JSONObject json = new JSONObject(jsonResult);
        User user = new User();
        if (json.getBoolean("success")){
            user = new User(json.getJSONObject("data"));
        }
        return user;
    }
}
