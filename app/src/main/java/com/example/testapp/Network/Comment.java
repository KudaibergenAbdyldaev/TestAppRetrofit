package com.example.testapp.Network;

import com.google.gson.annotations.SerializedName;

public class Comment {
    private int postId;

    private int id;

    private String name;

    private String email;

    @SerializedName("body")
    private String comment;

    public Comment(int postId, String name, String email, String comment) {
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.comment = comment;
    }

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getComment() {
        return comment;
    }
}
