package com.example.chrysus.payment.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chrysus.BaseController;
import com.example.chrysus.R;

public class NFCPayActivity extends AppCompatActivity {

    BaseController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcpay);
        controller = new NFCPayController(this, findViewById(R.id.nfc_pay).getRootView());
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((NFCPayController) controller).registerSensor(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ((NFCPayController) controller).unregisterSensor();
    }
}
