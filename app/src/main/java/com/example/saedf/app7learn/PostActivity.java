package com.example.saedf.app7learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.saedf.app7learn.Adapter.PostAdapter;
import com.example.saedf.app7learn.ApiService.ApiService;
import com.example.saedf.app7learn.dataModel.Post;

import java.util.List;

public class
PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ApiService apiService=new ApiService(this);
        apiService.getPosts(new ApiService.OnRecivedNews() {
            @Override
            public void onrecived(List<Post> postList) {
                RecyclerView recyclerView=findViewById(R.id.rv_activitypost);
                PostAdapter postAdapter=new PostAdapter(PostActivity.this,postList);
                recyclerView.setLayoutManager(new LinearLayoutManager(PostActivity.this,
                        LinearLayoutManager.VERTICAL,false));
                recyclerView.setAdapter(postAdapter);
            }
        });
    }
}
