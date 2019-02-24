package com.example.chrysus.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;

import com.example.chrysus.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private Bundle bundle;

    public ConfigReader(Context context) throws IOException, PackageManager.NameNotFoundException {
        ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        this.bundle = ai.metaData;
    }

    public String getValue(String key){
        return this.bundle.getString(key);
    }

}
