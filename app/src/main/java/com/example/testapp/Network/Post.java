package com.example.testapp.Network;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int userId;

    private Integer id;

    private String title;

    public Post(int userId, Integer id, String title, String text) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.text = text;
    }

    @SerializedName("body")
    private String text;


    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
