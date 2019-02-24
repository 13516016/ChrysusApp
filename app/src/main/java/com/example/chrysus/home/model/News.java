package com.example.chrysus.home.model;

import org.json.JSONException;
import org.json.JSONObject;

public class News {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    String title;
    String imagePath;

    public String getNewsLink() {
        return newsLink;
    }

    public void setNewsLink(String newsLink) {
        this.newsLink = newsLink;
    }

    String newsLink;

    public News(String title, String imagePath, String newsLink) {
        this.title = title;
        this.imagePath = imagePath;
        this.newsLink = newsLink;
    }

    public News(JSONObject newsJson) throws JSONException {
        this.title = newsJson.getString("title");
        this.imagePath = newsJson.getString("image_path");
        this.newsLink = newsJson.getString("news_link");
    }
}
