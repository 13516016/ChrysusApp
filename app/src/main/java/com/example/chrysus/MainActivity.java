package com.example.chrysus;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.example.chrysus.util.pager.MainPagerAdapter;
import com.example.chrysus.util.pager.NavigationViewPagerListener;
import com.example.chrysus.util.pager.NonSwipeableViewPager;

public class MainActivity extends AppCompatActivity {

    NonSwipeableViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViewPager();
        registerNavigationListener();
    }

    private void setupViewPager() {
        viewPager = findViewById(R.id.viewpager);
        FragmentPagerAdapter adapterViewPager = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);
    }

    private void registerNavigationListener(){
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new NavigationViewPagerListener(viewPager));
    }

}
