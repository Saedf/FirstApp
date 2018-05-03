package com.example.saedf.app7learn;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.saedf.app7learn.Adapter.PostAdapter;
import com.example.saedf.app7learn.ApiService.ApiService;
import com.example.saedf.app7learn.AsycnchronisProcessing.DownloadImagePostTask;
import com.example.saedf.app7learn.SqlLiteOpenHelper.PostDataBaseOpenHelper;
import com.example.saedf.app7learn.dataModel.Post;

import java.util.ArrayList;
import java.util.List;

public class PostWithAsyncTaskActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Post> postList = new ArrayList<>();
    private static final int REQUEST_PERMISSION_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_with_async_task);
        setupRecyclerView();
        getPostFromDataBase();
        ApiService apiService = new ApiService(this);
        apiService.getPosts(new ApiService.OnRecivedNews() {
            @Override
            public void onrecived(List<Post> postList) {
                PostWithAsyncTaskActivity.this.postList=postList;
                PostDataBaseOpenHelper postDataBaseOpenHelper = new PostDataBaseOpenHelper(PostWithAsyncTaskActivity.this);

                postDataBaseOpenHelper.addPostToDBWithAsyncTask(postList);

                PostAdapter postAdapter = new PostAdapter(PostWithAsyncTaskActivity.this, postList);
                recyclerView.setAdapter(postAdapter);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(PostWithAsyncTaskActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        savedImageintoSDcard();
                    } else {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
                    }
                } else {
                    savedImageintoSDcard();
                }
            }
        });

    }

    private void savedImageintoSDcard() {
        List<String> urls=new ArrayList<>();
        for (int i = 0; i < postList.size(); i++) {
            urls.add(postList.get(i).getImagenewsUrl().replace("localhost",
                    "172.20.200.45"));
            DownloadImagePostTask downloadImagePostTask=new DownloadImagePostTask(urls,PostWithAsyncTaskActivity.this);
            downloadImagePostTask.execute();

        }
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.rv_activitypostwith_asyncTask);
        recyclerView.setLayoutManager(new LinearLayoutManager(PostWithAsyncTaskActivity.this,
                LinearLayoutManager.VERTICAL, false));
    }

    private void getPostFromDataBase() {
        PostDataBaseOpenHelper postDataBaseOpenHelper = new PostDataBaseOpenHelper(this);
        List<Post> postList = postDataBaseOpenHelper.getPostList();
        PostAdapter postAdapter = new PostAdapter(this, postList);
        recyclerView.setAdapter(postAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==REQUEST_PERMISSION_CODE){
            if (grantResults.length>0){
                savedImageintoSDcard();
            }else {
                Toast.makeText(this, "مجوز لازم برای ذخیره سازی عکس ها وجود ندارد!", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
