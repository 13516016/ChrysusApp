package com.example.chrysus.util.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.chrysus.home.HomeFragment;
import com.example.chrysus.history.HistoryFragment;
import com.example.chrysus.profile.ProfileFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {
    public static int NUM_ITEMS = 3;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment displayedFragment = null;
        switch (position){
            case 0:
                displayedFragment = new HomeFragment();
                break;
            case 1:
                displayedFragment = new HistoryFragment();
                break;
            case 2:
                displayedFragment = new ProfileFragment();
                break;
            default:
        }
        return displayedFragment;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
