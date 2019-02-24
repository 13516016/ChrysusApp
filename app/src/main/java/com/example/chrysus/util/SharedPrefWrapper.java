package com.example.chrysus.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefWrapper {
    public static String getSettingsString(Context context, String key){
        SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreference.getString(key, "");
    }

    public static boolean getSettingsBoolean(Context context, String key){
        SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreference.getBoolean(key, false);
    }

    public static int getSettingsInt(Context context, String key){
        SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreference.getInt(key, 0);
    }
}
