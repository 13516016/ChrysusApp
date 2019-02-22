package com.example.chrysus;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.chrysus.util.pager.MainPagerAdapter;
import com.example.chrysus.util.pager.NavigationViewPagerListener;

public class MainController extends BaseController {

    private ViewPager viewPager;
    private Toolbar toolbar;
    private float temperature;



    public MainController(Context context, View view) {
        super(context, view);
    }

    @Override
    public void registerView() {
        super.registerView();
        viewPager = view.findViewById(R.id.viewpager);
        toolbar = view.findViewById(R.id.toolbar);
    }

    public void setupViewPager(FragmentManager fm) {
        FragmentPagerAdapter adapterViewPager = new MainPagerAdapter(fm);
        viewPager.setAdapter(adapterViewPager);
    }

    public void registerNavigationListener(){
        BottomNavigationView navigation = (BottomNavigationView) view.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new NavigationViewPagerListener(viewPager));
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

}
