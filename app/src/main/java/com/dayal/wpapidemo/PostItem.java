package com.dayal.wpapidemo;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.annotations.SerializedName;

public class PostItem {

    @SerializedName("id")
    public String date;
    public String description;
    public String imageUrl;

    public PostItem() {
    }

    public PostItem(String date, String description, String image) {
        this.date = date;
        this.description = description;
        this.imageUrl = image;
    }
    @Nullable
    public String  getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}


