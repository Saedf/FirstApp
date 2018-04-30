package com.example.saedf.app7learn.dataFake;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.res.ResourcesCompat;

import com.example.saedf.app7learn.R;
import com.example.saedf.app7learn.dataModel.News;

import java.util.ArrayList;
import java.util.List;

public class NewsDataFakeGenerator {
    public static List<News> generateDataNews(Context context){
        List<News> newsList=new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            News news=new News();
            news.setTitle("لورم ایپسوم متن ساختگی");
            news.setContent("لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است. چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است و برای شرایط فعلی تکنولوژی مورد نیاز و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد.لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است. چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است و برای شرایط فعلی تکنولوژی مورد نیاز و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد.");
            news.setDate("2 ساعت پیش");
            switch (i){
                case 1:
                    news.setImagenews(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.pic1,null));
                    break;
                case 2:
                    news.setImagenews(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.pic2,null));
                    break;
                case 3:
                    news.setImagenews(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.pic3,null));
                    break;
                case 4:
                    news.setImagenews(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.pic4,null));
                    break;
                case 5:
                    news.setImagenews(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.pic5,null));
                    break;
                case 6:
                    news.setImagenews(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.pic6,null));
                    break;
                case 7:
                    news.setImagenews(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.pic3,null));
                    break;
                case 8:
                    news.setImagenews(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.pic1,null));
                    break;
            }
            newsList.add(news);
        }
        return newsList;
    }
}
