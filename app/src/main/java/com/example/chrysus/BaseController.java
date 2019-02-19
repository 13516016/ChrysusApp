package com.example.chrysus;

import android.content.Context;
import android.view.View;

public class BaseController {
    protected View view;
    protected Context context;

    public BaseController(Context context, View view) {
        this.view = view;
        this.context = context;
        registerView();
    }

    public View initializeView() {
        return view;
    }

    protected void registerView(){

    }
}
