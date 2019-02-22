package com.example.chrysus.authentication.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chrysus.BaseController;
import com.example.chrysus.R;

public class LoginActivity extends AppCompatActivity {

    private BaseController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        controller = new LoginController(this, findViewById(R.id.login_button).getRootView());
        ((LoginController) controller).registerView();
    }
}
