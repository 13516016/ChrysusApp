package com.example.chrysus.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class SharedPrefWrapper {
    public static String getSettingsString(Context context, String key){
        SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreference.getString(key, "");
    }

    public static boolean getSettingsBoolean(Context context, String key){
        SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(context);
        Log.d("SharedPref", String.valueOf(sharedPreference.contains("news")));
        return sharedPreference.getBoolean(key, true);
    }

    public static int getSettingsInt(Context context, String key){
        SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreference.getInt(key, 0);
    }
}
