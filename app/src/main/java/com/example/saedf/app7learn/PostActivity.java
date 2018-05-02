package com.example.saedf.app7learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.saedf.app7learn.Adapter.PostAdapter;
import com.example.saedf.app7learn.ApiService.ApiService;
import com.example.saedf.app7learn.SqlLiteOpenHelper.PostDataBaseOpenHelper;
import com.example.saedf.app7learn.dataModel.Post;

import java.util.List;

public class
PostActivity extends AppCompatActivity {
private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        setupRecyclerView();
        getPostFromDataBase();
        ApiService apiService=new ApiService(this);
        apiService.getPosts(new ApiService.OnRecivedNews() {
            @Override
            public void onrecived(List<Post> postList) {
                PostDataBaseOpenHelper postDataBaseOpenHelper=new PostDataBaseOpenHelper(PostActivity.this);
                postDataBaseOpenHelper.addListPostToDB(postList);

                PostAdapter postAdapter=new PostAdapter(PostActivity.this,postList);

                recyclerView.setAdapter(postAdapter);
            }
        });
    }
    private void setupRecyclerView(){
        recyclerView=findViewById(R.id.rv_activitypost);
        recyclerView.setLayoutManager(new LinearLayoutManager(PostActivity.this,
                LinearLayoutManager.VERTICAL,false));
    }
    private void getPostFromDataBase(){
        PostDataBaseOpenHelper postDataBaseOpenHelper=new PostDataBaseOpenHelper(this);
        List<Post> postList=postDataBaseOpenHelper.getPostList();
        PostAdapter postAdapter=new PostAdapter(this,postList);
        recyclerView.setAdapter(postAdapter);
    }
}
