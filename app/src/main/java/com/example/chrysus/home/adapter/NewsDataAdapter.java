package com.example.chrysus.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.chrysus.R;
import com.example.chrysus.home.model.News;

import java.util.ArrayList;

public class NewsDataAdapter extends RecyclerView.Adapter<NewsDataAdapter.NewsViewHolder> {
    private final RecyclerView rv;
    private ArrayList<News> newsList;
    Context context;

    private View.OnClickListener newsOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int itemPosition = rv.getChildLayoutPosition(v);
            String newsUrl = newsList.get(itemPosition).getNewsLink();
            Intent internetIntent = new Intent(Intent.ACTION_VIEW);
            internetIntent.setData(Uri.parse(newsUrl));
            context.startActivity(internetIntent);
        }
    };

    public NewsDataAdapter(Context context, RecyclerView rv, ArrayList<News> newsList) {
        this.newsList = newsList;
        this.rv = rv;
        this.context = context;
    }

    public void setNewsList(ArrayList<News> newsList) {
        this.newsList = newsList;
    }

    public ArrayList<News> getNewsList() {
        return newsList;
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle;
        ImageView newsImage;
        public NewsViewHolder(View view) {
            super(view);
            newsTitle = (TextView) view.findViewById(R.id.news_list_title);
            newsImage = (ImageView) view.findViewById(R.id.news_list_image);
        }
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View newsListView = inflater.inflate(R.layout.news_view, parent, false);
        newsListView.setOnClickListener(newsOnClickListener);
        return new NewsViewHolder(newsListView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
        News news = newsList.get(i);

        newsViewHolder.newsTitle.setText(news.getTitle());
        Glide.with(context)
                .load(news.getImagePath())
                .apply(new RequestOptions().centerCrop())
                .into(newsViewHolder.newsImage);

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
