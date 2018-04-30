package com.example.saedf.app7learn.dataModel;

import android.graphics.drawable.Drawable;

public class News {
    private int id;
    private Drawable imagenews;
    private String title;
    private String content;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Drawable getImagenews() {
        return imagenews;
    }

    public void setImagenews(Drawable imagenews) {
        this.imagenews = imagenews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
