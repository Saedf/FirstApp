package com.example.saedf.app7learn;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.saedf.app7learn.Adapter.AppFeatureAdapter;
import com.example.saedf.app7learn.dataFake.FeatureDataFakeGenerator;

public class MainActivity extends AppCompatActivity {
private AppFeatureAdapter appFeatureAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
    }


    private void setupView() {
        setupRecyclerView();
        setupToolbar();

    }


    private void setupToolbar() {
        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);

        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.is_not_visited));
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        ActionBarDrawerToggle drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,0,0);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


    }

    private void setupRecyclerView() {
        RecyclerView recyclerView=findViewById(R.id.recycler_view_mainactivity);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        appFeatureAdapter=new AppFeatureAdapter(this);
        recyclerView.setAdapter(appFeatureAdapter);
        appFeatureAdapter.setAppFeature(FeatureDataFakeGenerator.getAppFeatures(this));


    }
}
