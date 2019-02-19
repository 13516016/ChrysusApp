package com.example.chrysus.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.chrysus.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView newsRecyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        newsRecyclerView = (RecyclerView) view.findViewById(R.id.news_rv);

        newsRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.HORIZONTAL, false));
        NewsDataAdapter newsDataAdapter = new NewsDataAdapter(getContext(), getNewsData());

        newsRecyclerView.setAdapter(newsDataAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private ArrayList<News> getNewsData(){
        ArrayList<News> newsList = new ArrayList<>();
        newsList.add(new News("Hehe", "https://loremflickr.com/250/150"));
        newsList.add(new News("Hehe2", "https://loremflickr.com/250/150"));
        newsList.add(new News("Hehe3", "https://loremflickr.com/250/150"));

        return newsList;
    }
}
