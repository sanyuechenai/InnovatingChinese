package com.yueworld.baseproject.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yueworld.baseproject.R;

/**
 * 创建时间: 2018/9/28.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class PicassonUtil {


    public static void loadImg(Context context, String url, ImageView imageView){
        Picasso.with(context).load(url)
                .placeholder(context.getResources().getDrawable(R.mipmap.my_pic))
                .error(context.getResources().getDrawable(R.mipmap.my_pic))
                .into(imageView);
    }

    public static void loadImg(Context context, String url, ImageView imageView,int defaultImgId){
        Picasso.with(context).load(url)
                .placeholder(context.getResources().getDrawable(defaultImgId))
                .error(context.getResources().getDrawable(defaultImgId))
                .into(imageView);
    }
}
