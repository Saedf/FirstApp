package com.example.saedf.app7learn;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.example.saedf.app7learn.fragment.ClotheViewPagerAdapter;

public class BotickActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botick);
        TabLayout tabLayout=findViewById(R.id.tablayotu_botickActivity);
        ViewPager viewPager=findViewById(R.id.vp_botickActivity);
        ClotheViewPagerAdapter clotheViewPagerAdapter=new ClotheViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(clotheViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        Toolbar toolbar=findViewById(R.id.toolbar_botickactivity);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
