package com.example.chrysus.home.model;

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

    public News(String title, String imagePath) {
        this.title = title;
        this.imagePath = imagePath;
    }
}
