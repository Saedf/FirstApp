package com.example.saedf.app7learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.saedf.app7learn.Adapter.NewsAdapter;
import com.example.saedf.app7learn.dataFake.NewsDataFakeGenerator;

import java.util.List;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        RecyclerView recyclerView=findViewById(R.id.rv_newsActivity);
        NewsAdapter newsAdapter=new NewsAdapter(NewsDataFakeGenerator.generateDataNews(this),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(newsAdapter);

    }
}
