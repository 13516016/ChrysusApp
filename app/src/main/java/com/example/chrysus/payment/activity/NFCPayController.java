package com.example.chrysus.payment.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.Toast;

import com.example.chrysus.BaseController;

public class NFCPayController extends BaseController {


    //For proximity sensor
    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private SensorEventListener proximitySensorListener;

    @Override
    protected void registerView() {
        super.registerView();
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    public NFCPayController(Context context, View view) {
        super(context, view);
    }


    public void registerSensor(final Activity activity){
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (proximitySensor == null) {
            Toast.makeText(context, "Proximity sensor isn't available!", Toast.LENGTH_LONG).show();
        }

        proximitySensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0] < proximitySensor.getMaximumRange()) {
                    activity.getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                } else {
                    activity.getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(proximitySensorListener, proximitySensor, 2 * 1000 * 1000);
    }

    public void unregisterSensor(){
        sensorManager.unregisterListener(proximitySensorListener);
    }
}
