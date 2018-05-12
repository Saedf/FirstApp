package com.example.saedf.app7learn.animation;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.saedf.app7learn.R;

public class AnimationActivity extends AppCompatActivity {
    private static final String TAG = "AnimationActivity";
    public static final String EXTRA_KEY_ANIMATION_TYPE = "animation_type";
    private int animation_type = 0;
    public static final int TYPE_ALPHA = 0;
    public static final int TYPE_TRANLATE = 1;
    public static final int TYPE_SCALE = 2;
    public static final int TYPE_ROTATE = 3;
    public static final int TYPE_VALUE_ANIMATOR = 4;
    public static final int TYPE_ANIMATION_SET = 5;
    public static final int TYPE_YOYO = 6;

    private ImageView koroushImage;
    private SwitchCompat loadFromXmlSwitch;
    private boolean mustLoadFromXml = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        animation_type = getIntent().getIntExtra(EXTRA_KEY_ANIMATION_TYPE, TYPE_ALPHA);
        Log.i(TAG, "onCreate: " + animation_type);
        Button btnStart = findViewById(R.id.button_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnimation();
            }
        });
        koroushImage = findViewById(R.id.image_kourosh);
        loadFromXmlSwitch = findViewById(R.id.switch_from_xml);
        loadFromXmlSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mustLoadFromXml = isChecked;
            }
        });
    }

    private void showAnimation() {
        switch (animation_type) {
            case TYPE_ALPHA:
                showAlphaAnimation();
                break;
            case TYPE_TRANLATE:
                showTranslateAnimation();
                break;
            case TYPE_SCALE:
                showScaleAnimation();
                break;
            case TYPE_ROTATE:
                showRotateAnimation();
                break;
            case TYPE_VALUE_ANIMATOR:
                showValueAnimator();
                break;
            case TYPE_ANIMATION_SET:
                showAnimationSet();
                break;
            case TYPE_YOYO:
                showYoyoAnimation();
                break;
        }
    }

    private void showYoyoAnimation() {
//        YoYo.with(Techniques.Shake)
//                .duration(1000)
//                .playOn(kouroshImage);
    }

    private void showAnimationSet() {
        if (mustLoadFromXml) {
            AnimationSet animationSet = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.sample_set);
            animationSet.setDuration(1000);
            animationSet.setFillAfter(true);
            animationSet.setRepeatCount(Animation.INFINITE);
            animationSet.setRepeatMode(Animation.REVERSE);
            koroushImage.startAnimation(animationSet);
        } else {
            AnimationSet animationSet = new AnimationSet(true);
            ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0,
                    Animation.ABSOLUTE, 0, Animation.RELATIVE_TO_SELF, 1);
            animationSet.addAnimation(scaleAnimation);
            animationSet.addAnimation(translateAnimation);

            animationSet.setDuration(1000);
            animationSet.setRepeatMode(Animation.REVERSE);
            animationSet.setRepeatCount(Animation.INFINITE);
            animationSet.setFillAfter(true);
            koroushImage.startAnimation(animationSet);
        }
    }

    private void showValueAnimator() {
        final FrameLayout frameLayout = findViewById(R.id.frame_layout_root);
        final ValueAnimator valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), ContextCompat.getColor(this,
                R.color.colorPrimary), ContextCompat.getColor(this, R.color.colorPrimaryDark),
                ContextCompat.getColor(this, R.color.color_white));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                frameLayout.setBackgroundColor((Integer) valueAnimator.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.start();
    }

    private void showRotateAnimation() {
        if (mustLoadFromXml) {
            RotateAnimation rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(this, R.anim.sample_rotate);
            rotateAnimation.setDuration(1000);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setInterpolator(new AccelerateInterpolator());
            koroushImage.startAnimation(rotateAnimation);

        } else {
            RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setDuration(1000);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setRepeatCount(0);
            koroushImage.startAnimation(rotateAnimation);
        }

    }

    private void showScaleAnimation() {
        if (mustLoadFromXml) {
            ScaleAnimation scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(this, R.anim.sample_scale);
            scaleAnimation.setDuration(1000);
            scaleAnimation.setRepeatCount(Animation.INFINITE);
            scaleAnimation.setRepeatMode(Animation.REVERSE);
            scaleAnimation.setInterpolator(new DecelerateInterpolator());
            koroushImage.startAnimation(scaleAnimation);
        } else {
            ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimation.setFillAfter(true);
            scaleAnimation.setInterpolator(new AccelerateInterpolator());
            scaleAnimation.setDuration(1000);
            koroushImage.startAnimation(scaleAnimation);
        }

    }

    private void showTranslateAnimation() {
        if (mustLoadFromXml) {
            TranslateAnimation translateAnimation = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.sample_translate);
            translateAnimation.setDuration(1000);
            translateAnimation.setRepeatCount(Animation.INFINITE);
            translateAnimation.setRepeatMode(Animation.REVERSE);
            translateAnimation.setInterpolator(new AccelerateInterpolator());
            koroushImage.startAnimation(translateAnimation);

        } else {
            TranslateAnimation translateAnimation = new TranslateAnimation(
                    Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 200, Animation.ABSOLUTE,
                    0, Animation.RELATIVE_TO_PARENT, 1.0f
            );
            translateAnimation.setDuration(2000);
            translateAnimation.setFillAfter(true);
            translateAnimation.setInterpolator(new BounceInterpolator());
            koroushImage.startAnimation(translateAnimation);
        }

    }

    private void showAlphaAnimation() {
        if (mustLoadFromXml) {
            AlphaAnimation alphaAnimation = (AlphaAnimation) AnimationUtils.loadAnimation(this, R.anim.sample_alpha);
            alphaAnimation.setDuration(2000);
            alphaAnimation.setRepeatCount(Animation.INFINITE);
            alphaAnimation.setRepeatMode(Animation.REVERSE);
            koroushImage.startAnimation(alphaAnimation);
        } else {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.5f);
            alphaAnimation.setDuration(2000);
            alphaAnimation.setFillAfter(true);
            koroushImage.startAnimation(alphaAnimation);
        }
    }
}
