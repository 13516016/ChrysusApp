package com.example.chrysus.home;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.chrysus.BaseController;
import com.example.chrysus.R;
import com.example.chrysus.home.adapter.NewsDataAdapter;
import com.example.chrysus.home.model.News;

import java.util.ArrayList;

public class HomeController extends BaseController {

    private RecyclerView newsRecyclerView;

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
    }

    private ArrayList<News> getNewsData(){
        ArrayList<News> newsList = new ArrayList<>();
        newsList.add(new News("Hehe", "https://loremflickr.com/250/150"));
        newsList.add(new News("Hehe2", "https://loremflickr.com/250/150"));
        newsList.add(new News("Hehe3", "https://loremflickr.com/250/150"));
        return newsList;
    }
}
