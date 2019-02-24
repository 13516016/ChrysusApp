package com.example.chrysus;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.chrysus.util.FirebaseAuthWrapper;
import com.example.chrysus.util.MiddlewareActivity;
import com.example.chrysus.util.pager.MainPagerAdapter;
import com.example.chrysus.util.pager.NavigationViewPagerListener;

public class MainController extends BaseController {

    private ViewPager viewPager;
    private Toolbar toolbar;

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

    public void logout(){
        FirebaseAuthWrapper.logoutFirebase();
        Intent middlewareActivity = new Intent(context, MiddlewareActivity.class);
        context.startActivity(middlewareActivity);
    }

    public void startSettingActivity(){
        Intent settingActivity = new Intent(context, SettingsActivity.class);
        context.startActivity(settingActivity);
    }

    public void scheduleAlarm() {
        // Construct an intent that will execute the AlarmReceiver
        Intent intent = new Intent(context, AlarmReceiver.class);
        // Create a PendingIntent to be triggered when the alarm goes off
        final PendingIntent pIntent = PendingIntent.getBroadcast(context, AlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Setup periodic alarm every every half hour from this point onwards
        long firstMillis = System.currentTimeMillis(); // alarm is set right away
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        /*
        First parameter is the type: ELAPSED_REALTIME, ELAPSED_REALTIME_WAKEUP, RTC_WAKEUP
        Interval can be INTERVAL_FIFTEEN_MINUTES, INTERVAL_HALF_HOUR, INTERVAL_HOUR, INTERVAL_DAY
        */
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                1000, pIntent);
    }

}
