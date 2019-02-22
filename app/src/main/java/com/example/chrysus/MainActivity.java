package com.example.chrysus;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.Toast;

import com.example.chrysus.util.pager.MainPagerAdapter;
import com.example.chrysus.util.pager.NavigationViewPagerListener;
import com.example.chrysus.util.pager.NonSwipeableViewPager;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    BaseController mainController;

    private SensorManager sensorManager;
    private float temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainController = new MainController(getApplicationContext(), findViewById(R.id.viewpager).getRootView());
        ((MainController) mainController).setupViewPager(getSupportFragmentManager());
        ((MainController) mainController).registerNavigationListener();
        setSupportActionBar(((MainController) mainController).getToolbar());
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return true;
    }

    //Ambient Temperature
    @Override
    protected void onResume(){
        super.onResume();
        loadAmbientTemperature();
    }

    @Override
    protected void onPause(){
        super.onPause();
        unregisterAll();
    }

    private void loadAmbientTemperature(){
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        if (sensor != null){
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        } else {
            Toast.makeText(this, "There is no Ambient Temperature Sensor!", Toast.LENGTH_LONG).show();
        }
    }

    private void unregisterAll(){
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        if (sensorEvent.values.length > 0){
            temperature = sensorEvent.values[0];
            getSupportActionBar().setTitle(getString(R.string.app_name) + " : " + temperature);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i){

    }
}
