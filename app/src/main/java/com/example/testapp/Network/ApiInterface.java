package com.example.testapp.Network;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("comments")
    Call<Comment> createComment(@Body Comment comment);
    @GET("posts")
    Call<List<Post>> getPostsByUser(
            @Query("userId") int[] userId
    );
    @GET("posts/{id}/comments")
    Call<List<Comment>> getCommentsDetail(@Path("id") int postId);
}
