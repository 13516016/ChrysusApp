package com.example.chrysus.home;

//For light sensor
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chrysus.BaseController;
import com.example.chrysus.R;
import com.example.chrysus.home.adapter.NewsDataAdapter;
import com.example.chrysus.home.model.News;
import com.example.chrysus.payment.NFCPayActivity;
import com.example.chrysus.payment.QRPayActivity;
import com.example.chrysus.payment.ReceiveMoneyActivity;
import com.example.chrysus.payment.SendMoneyActivity;

import java.util.ArrayList;

public class HomeController extends BaseController  {

    private RecyclerView newsRecyclerView;
    private LinearLayout nfcPayLayout;
    private LinearLayout qrPayLayout;
    private LinearLayout sendMoneyLayout;
    private LinearLayout receiveMoneyLayout;
    private SensorManager sensorManager;
    private TextView temperatureTV;

    //For light sensor
    private Sensor lightSensor;
    private SensorEventListener lightEventListener;
    private View root;

    private float maxValue;


    public HomeController(Context context, View view) {
        super(context, view);
    }

    @Override
    public View initializeView() {
        super.initializeView();
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false));
        NewsDataAdapter newsDataAdapter = new NewsDataAdapter(this.context, getNewsData());
        newsRecyclerView.setAdapter(newsDataAdapter);
        return this.view;
    }

    @Override
    public void registerView() {
        super.registerView();
        newsRecyclerView = view.findViewById(R.id.news_rv);
        nfcPayLayout = view.findViewById(R.id.nfc_pay);
        qrPayLayout = view.findViewById(R.id.qr_pay);
        sendMoneyLayout = view.findViewById(R.id.send_money);
        receiveMoneyLayout = view.findViewById(R.id.receive_money);
        temperatureTV = view.findViewById(R.id.temperature);
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);


        registerPaymentOnClickListener(new View[]{nfcPayLayout,qrPayLayout, sendMoneyLayout, receiveMoneyLayout});

    }

    private void registerPaymentOnClickListener(View[] paymentViews){
        for (View view: paymentViews){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    routePayment(v);
                }
            });
        }
    }

    private void routePayment(View view) {
        switch (view.getId()){
            case R.id.nfc_pay:
                navigateToNFCPayActivity();
                break;
            case R.id.qr_pay:
                navigateToQRPayActivity();
                break;
            case R.id.send_money:
                navigateToSendMoneyActivity();
                break;
            case R.id.receive_money:
                navigateToReceiveMoneyActivity();
                break;
        }
    }

    private ArrayList<News> getNewsData(){
        ArrayList<News> newsList = new ArrayList<>();
        newsList.add(new News("Hehe", "https://loremflickr.com/250/150"));
        newsList.add(new News("Hehe2", "https://loremflickr.com/250/150"));
        newsList.add(new News("Hehe3", "https://loremflickr.com/250/150"));
        return newsList;
    }

    private void navigateToQRPayActivity(){
        Intent intent = new Intent(context, QRPayActivity.class);
        context.startActivity(intent);
    }

    private void navigateToNFCPayActivity(){
        Intent intent = new Intent(context, NFCPayActivity.class);
        context.startActivity(intent);
    }

    private void navigateToSendMoneyActivity(){
        Intent intent = new Intent(context, SendMoneyActivity.class);
        context.startActivity(intent);
    }

    private void navigateToReceiveMoneyActivity(){
        Intent intent = new Intent(context, ReceiveMoneyActivity.class);
        context.startActivity(intent);
    }

    public void registerSensor(){
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (lightSensor == null) {
            Toast.makeText(context, "The device has no light sensor !", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("TEST", "TEST");
        }

        // max value for light sensor
        maxValue = lightSensor.getMaximumRange();

        lightEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float value = event.values[0];

                int newValue = (int) (255f * value / maxValue);
                temperatureTV.setText(String.valueOf(value));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(lightEventListener, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void unregisterSensor(){
        sensorManager.unregisterListener(lightEventListener);
    }
}
