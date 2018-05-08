package com.example.saedf.app7learn;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.saedf.app7learn.Adapter.AppFeatureAdapter;
import com.example.saedf.app7learn.dataFake.FeatureDataFakeGenerator;

public class MainActivity extends AppCompatActivity {
private AppFeatureAdapter appFeatureAdapter;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();


    }


    private void setupView() {
        setupRecyclerView();
        setupToolbar();
        setupNavigationView();
        final CoordinatorLayout coordinatorLayout=findViewById(R.id.coordinator_layout);
        FloatingActionButton floatingActionButton=findViewById(R.id.float_action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(coordinatorLayout,"Float Action Button Clicked",Snackbar.LENGTH_LONG)
                        .setAction("Retry", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Retry Button Click", Toast.LENGTH_LONG).show();
                            }
                        }).show();
               // Toast.makeText(MainActivity.this, "Float Action Button is Click", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setupNavigationView() {
        NavigationView navigationView=findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_menu_profile:startActivity(new Intent(MainActivity.this,ProfileActivity.class));
                    break;
                    case  R.id.navigation_menu_store:startActivity(new Intent(MainActivity.this,BotickActivity.class));
                    break;

                }
                return true;
            }
        });
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
        GridLayoutManager  gridLayoutManager=new GridLayoutManager(this,2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position==0){
                    return 2;
                }else {
                    return 1;
                }
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        appFeatureAdapter=new AppFeatureAdapter(this);
        recyclerView.setAdapter(appFeatureAdapter);
        appFeatureAdapter.setAppFeature(FeatureDataFakeGenerator.getAppFeatures(this));


    }
}
