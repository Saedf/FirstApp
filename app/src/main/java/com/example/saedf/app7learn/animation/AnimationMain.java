package com.example.saedf.app7learn.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.saedf.app7learn.MainActivity;
import com.example.saedf.app7learn.R;

public class AnimationMain extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_main);
        setupToolbar();
        setupButtons();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_animation_main_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void setupButtons() {
        Button alphAnimationButton = findViewById(R.id.button_akpha_animation);
        alphAnimationButton.setOnClickListener(this);

        Button translateAnimationButton = (Button) findViewById(R.id.button_translate_animation);
        translateAnimationButton.setOnClickListener(this);

        Button scaleAnimationButton = (Button) findViewById(R.id.button_scale_animation);
        scaleAnimationButton.setOnClickListener(this);

        Button rotateAnimationButton = (Button) findViewById(R.id.button_rotate_animation);
        rotateAnimationButton.setOnClickListener(this);

        Button valueAnimatorAnimationButton = (Button) findViewById(R.id.button_value_animator);
        valueAnimatorAnimationButton.setOnClickListener(this);

        Button animationSetButton=findViewById(R.id.button_animation_set);
        animationSetButton.setOnClickListener(this);

        Button animationYoyoButton=findViewById(R.id.button_yoyo);
        animationYoyoButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(AnimationMain.this, AnimationActivity.class);
        intent.putExtra(AnimationActivity.EXTRA_KEY_ANIMATION_TYPE, Integer.parseInt((String) v.getTag()));
        startActivity(intent);
    }
}
