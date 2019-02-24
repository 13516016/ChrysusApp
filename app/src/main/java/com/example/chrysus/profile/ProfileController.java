package com.example.chrysus.profile;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.chrysus.BaseController;
import com.example.chrysus.R;
import com.example.chrysus.home.HomeController;
import com.example.chrysus.home.model.User;
import com.example.chrysus.util.ConfigReader;
import com.example.chrysus.util.FirebaseAuthWrapper;

import java.io.IOException;

public class ProfileController extends BaseController {
    private TextView nameTV;
    private TextView phoneTV;
    private TextView addressTV;

    public ProfileController(Context context, View view) {
        super(context, view);
    }

    @Override
    protected void registerView() {
        super.registerView();
        nameTV = view.findViewById(R.id.profile_name_tv);
        phoneTV = view.findViewById(R.id.profile_phone_number);
        addressTV = view.findViewById(R.id.profile_address);
    }

    public void getUserData() {
        try {
            String firebaseUid = FirebaseAuthWrapper.getFirebaseAuthUid();
            new ProfileAsync().execute(new ConfigReader(context).getValue("search_by_uid_url")+ firebaseUid);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private class ProfileAsync extends ProfileAsyncTask{
        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            nameTV.setText(user.getName());
            phoneTV.setText(user.getPhone());
            addressTV.setText(user.getAddress());
        }
    }
}
