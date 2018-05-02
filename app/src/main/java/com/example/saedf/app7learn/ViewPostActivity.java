package com.example.saedf.app7learn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saedf.app7learn.SqlLiteOpenHelper.PostDataBaseOpenHelper;
import com.squareup.picasso.Picasso;

public class ViewPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);
        Intent intent=getIntent();
        int id=intent.getIntExtra(PostDataBaseOpenHelper.COL_ID,0);
        setPostisVisited(id);
        String title=intent.getStringExtra(PostDataBaseOpenHelper.COL_TITLE);
        String content=intent.getStringExtra(PostDataBaseOpenHelper.COL_CONTENT);
        String Date=intent.getStringExtra(PostDataBaseOpenHelper.COL_DATE);
        String imageUrl=intent.getStringExtra(PostDataBaseOpenHelper.COL_POST_IMAGE_URL);

        ImageView imageView=findViewById(R.id.iv_viewpostActivity_imagepost);
        TextView tvTitle=findViewById(R.id.tv_viewpostActivity_titlepost);
        TextView tvContent=findViewById(R.id.tv_viewpostActivity_contentpost);
        TextView tvDate=findViewById(R.id.tv_viewpostActivity_datepost);

        Picasso.with(this).load(imageUrl.replace("localhost","172.20.200.45")).into(imageView);
        tvTitle.setText(title);
        tvContent.setText(content);
        tvDate.setText(Date);

    }
    private void setPostisVisited(int id){
        PostDataBaseOpenHelper postDataBaseOpenHelper=new PostDataBaseOpenHelper(this);
        postDataBaseOpenHelper.setPostIsVisited(id,1);
    }
}
