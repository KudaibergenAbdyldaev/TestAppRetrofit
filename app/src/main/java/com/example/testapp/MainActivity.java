package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.testapp.Adapter.MyAdapter;
import com.example.testapp.Network.ApiInterface;
import com.example.testapp.Network.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface jsonPlaceHolderApi = retrofit.create(ApiInterface.class);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Call<List<Post>> call = jsonPlaceHolderApi.getPostsByUser(new int[]{1, 2, 3});

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                MyAdapter adapter = new MyAdapter(MainActivity.this, response.body());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            }
        });

    }


}