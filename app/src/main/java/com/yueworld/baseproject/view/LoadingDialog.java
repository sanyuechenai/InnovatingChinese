package com.yueworld.baseproject.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.yueworld.baseproject.R;


/**
 * Created by Vincent Chen on 2018/5/31.
 */
public class LoadingDialog extends Dialog {

    private Context context;
    private  LoadingDialog dialog;
    private ImageView ivLoading;
    private TextView tvLoading;
    private static AnimationDrawable animationDrawable;
    private View DialogView;

    public LoadingDialog(Context context) {
        super(context,R.style.LoadingDialog);
        init(context);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        init(context);
    }

    private void init(Context context){
        DialogView= LayoutInflater.from(context).inflate(R.layout.dialog_layout,null);
        tvLoading= (TextView) DialogView.findViewById(R.id.tvLoading);
        ivLoading = (ImageView) DialogView.findViewById(R.id.ivLoading);
        this.setContentView(DialogView);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
    }

    //显示dialog的方法
    public  void showDialog(String loadingTxt){
            if(tvLoading!=null){
                tvLoading.setText(loadingTxt);
                tvLoading.setVisibility(View.VISIBLE);
            }
            this.show();
    }

    public void dissMissDialog(){
            if(animationDrawable!=null){
                animationDrawable.stop();
            }
            this.dismiss();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus && DialogView != null){
            tvLoading= (TextView) DialogView.findViewById(R.id.tvLoading);
            ivLoading = (ImageView) DialogView.findViewById(R.id.ivLoading);
            ivLoading.setImageResource(R.drawable.loading_img_animation);
            animationDrawable= (AnimationDrawable) ivLoading.getDrawable();
            animationDrawable.start();
        }
    }
}
