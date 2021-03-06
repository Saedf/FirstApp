package com.example.saedf.app7learn.dataFake;

import android.content.Context;

import com.example.saedf.app7learn.AppWeatherActivity;
import com.example.saedf.app7learn.BotickActivity;
import com.example.saedf.app7learn.MainActivity;
import com.example.saedf.app7learn.MusicPlayerActivity;
import com.example.saedf.app7learn.NewsActivity;
import com.example.saedf.app7learn.PostActivity;
import com.example.saedf.app7learn.ProfileActivity;
import com.example.saedf.app7learn.R;
import com.example.saedf.app7learn.SignUpActivity;
import com.example.saedf.app7learn.animation.AnimationActivity;
import com.example.saedf.app7learn.animation.AnimationMain;
import com.example.saedf.app7learn.dataModel.AppFeature;

import java.util.ArrayList;
import java.util.List;

public class FeatureDataFakeGenerator {
    public static List<AppFeature> getAppFeatures(Context context) {
        List<AppFeature> appFeatureList = new ArrayList<>();

        AppFeature appFeature = new AppFeature();
        appFeature.setId(AppFeature.ID_POST_ACTIVITY);
        appFeature.setTitle("Latest Post");
        appFeature.setFeatureImage(R.mipmap.posts);
        appFeature.setDestinationActivity(PostActivity.class);
        appFeatureList.add(appFeature);

        appFeature = new AppFeature();
        appFeature.setId(AppFeature.ID_USER_PROFILE);
        appFeature.setTitle("User Profiel");
        appFeature.setFeatureImage(R.mipmap.user_profile);
        appFeature.setDestinationActivity(ProfileActivity.class);
        appFeatureList.add(appFeature);

        appFeature = new AppFeature();
        appFeature.setId(AppFeature.ID_FASHON);
        appFeature.setTitle("Fashion");
        appFeature.setFeatureImage(R.mipmap.fashion);
        appFeature.setDestinationActivity(BotickActivity.class);
        appFeatureList.add(appFeature);


        appFeature = new AppFeature();
        appFeature.setId(AppFeature.ID_MUSIC);
        appFeature.setTitle("Music Player");
        appFeature.setFeatureImage(R.mipmap.music_player);
        appFeature.setDestinationActivity(MusicPlayerActivity.class);
        appFeatureList.add(appFeature);

        appFeature = new AppFeature();
        appFeature.setId(AppFeature.ID_VIDEO);
        appFeature.setTitle("Weather");
        appFeature.setFeatureImage(R.mipmap.video_player);
        appFeature.setDestinationActivity(AppWeatherActivity.class);
        appFeatureList.add(appFeature);


        appFeature = new AppFeature();
        appFeature.setId(AppFeature.ID_LOGIN);
        appFeature.setTitle("Login");
        appFeature.setFeatureImage(R.mipmap.login);
        appFeature.setDestinationActivity(SignUpActivity.class);
        appFeatureList.add(appFeature);

        appFeature = new AppFeature();
        appFeature.setId(AppFeature.ID_ANIMATIONS);
        appFeature.setTitle(context.getString(R.string.app_feature_animation_in_android));
        appFeature.setFeatureImage(R.mipmap.animations_in_android);
        appFeature.setDestinationActivity(AnimationMain.class);
        appFeatureList.add(appFeature);


        return appFeatureList;

    }
}
