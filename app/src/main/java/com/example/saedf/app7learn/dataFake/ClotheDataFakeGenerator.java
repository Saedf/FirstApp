package com.example.saedf.app7learn.dataFake;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;

import com.example.saedf.app7learn.R;
import com.example.saedf.app7learn.dataModel.Clothe;

import java.util.ArrayList;
import java.util.List;

public class ClotheDataFakeGenerator {
    public static List<Clothe> getData(Context context){
        List<Clothe> clotheList=new ArrayList<>();
        for (int i = 1; i <=8; i++) {
            Clothe clothe=new Clothe();
            clothe.setTitleClothe("لباس فروشگاهی دیجی کالا");
            clothe.setViewCountclothe(700+i);
           switch (i){
               case 1:
                   clothe.setImageClothe(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.pic1_clothes,null));
                   break;
               case 2:
                   clothe.setImageClothe(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.pic2__clothes,null));
                   break;
               case 3:
                   clothe.setImageClothe(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.pic3_clothes,null));
                   break;
               case 4:
                   clothe.setImageClothe(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.pic4_clothes,null));
                   break;
               case 5:
                   clothe.setImageClothe(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.pic5_clothes,null));
                   break;
               case 6:
                   clothe.setImageClothe(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.pic6_clothes,null));
                   break;
               case 7:
                   clothe.setImageClothe(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.pic7_clothes,null));
                   break;
               case 8:
                   clothe.setImageClothe(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.pic8_clothes,null));
                   break;
           }
           clotheList.add(clothe);
        }
        return clotheList;
    }
}
