package com.example.saedf.app7learn.dataModel;

import android.graphics.drawable.Drawable;

public class Clothe {
    private int id;
    private Drawable imageClothe;
    private String titleClothe;
    private int viewCountclothe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Drawable getImageClothe() {
        return imageClothe;
    }

    public void setImageClothe(Drawable imageClothe) {
        this.imageClothe = imageClothe;
    }

    public String getTitleClothe() {
        return titleClothe;
    }

    public void setTitleClothe(String titleClothe) {
        this.titleClothe = titleClothe;
    }

    public int getViewCountclothe() {
        return viewCountclothe;
    }

    public void setViewCountclothe(int viewCountclothe) {
        this.viewCountclothe = viewCountclothe;
    }
}
