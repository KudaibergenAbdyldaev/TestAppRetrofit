package com.example.testapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testapp.Network.ApiInterface;
import com.example.testapp.Network.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondActivity extends AppCompatActivity {

    private TextView textViewResult;
    private ApiInterface jsonPlaceHolderApi;
    private EditText edtName, edtEmail, edtComment;
    private String stName, stEmail, stComment;
    private Comment comment;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textViewResult = findViewById(R.id.textView2);


        final Intent intent = getIntent();
        id = intent.getStringExtra("id");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(ApiInterface.class);
        createComment();
        getComments();
    }

    private void getComments(){
        Call<List<Comment>> call = jsonPlaceHolderApi.getCommentsDetail(Integer.parseInt(id));
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code: "+response.code());
                    return;
                }

                List<Comment> comments = response.body();
                for (Comment comment:comments){
                    String content = "";
                    content += "Name: "+comment.getName() + "\n";
                    content += "Email: "+comment.getEmail() + "\n";
                    content += "Comment: "+comment.getComment() + "\n\n";

                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });


    }

    private void createComment() {
        Call<Comment> call = jsonPlaceHolderApi.createComment(comment);
        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code:" + response.code());
                    return;
                }
                Comment postResponse = response.body();

                String content = "";
                assert postResponse != null;
                content += "Name: " + postResponse.getName() + "\n";
                content += "Email: " + postResponse.getEmail() + "\n";
                content += "Comment: " + postResponse.getComment() + "\n\n";

                textViewResult.append(content);
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
            }
        });

    }
    private void showAddDialog() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Comment");

        LayoutInflater inflater = LayoutInflater.from(this);
        final View login_layout = inflater.inflate(R.layout.item_add_data, null);
        edtName = login_layout.findViewById(R.id.edt_name);
        edtEmail = login_layout.findViewById(R.id.edt_email);
        edtComment = login_layout.findViewById(R.id.edt_comment);

        dialog.setView(login_layout);

        dialog.setPositiveButton("POST", (dialog1, which) -> {
            stName = edtName.getText().toString().trim();
            stEmail = edtEmail.getText().toString().trim();
            stComment = edtComment.getText().toString().trim();
            comment = new Comment(Integer.parseInt(id), stName, stEmail, stComment);
            createComment();
        });

        dialog.setNegativeButton("CANCEL", (dialog12, which) -> dialog12.dismiss());

        dialog.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            showAddDialog();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}