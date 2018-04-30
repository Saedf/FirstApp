package com.example.saedf.app7learn.dataModel;

public class Post {
    private int id;
    private String imagenewsUrl;
    private String title;
    private String content;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagenewsUrl() {
        return imagenewsUrl;
    }

    public void setImagenewsUrl(String imagenewsUrl) {
        this.imagenewsUrl = imagenewsUrl;
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
