package com.example.chrysus;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.chrysus.util.pager.MainPagerAdapter;
import com.example.chrysus.util.pager.NavigationViewPagerListener;
import com.example.chrysus.util.pager.NonSwipeableViewPager;

public class MainActivity extends AppCompatActivity {

    NonSwipeableViewPager viewPager;
    BaseController mainController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainController = new MainController(getApplicationContext(), findViewById(R.id.viewpager).getRootView());
        ((MainController) mainController).setupViewPager(getSupportFragmentManager());
        ((MainController) mainController).registerNavigationListener();
        setSupportActionBar(((MainController) mainController).getToolbar());
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
}
