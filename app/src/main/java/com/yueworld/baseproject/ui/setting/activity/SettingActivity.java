package com.yueworld.baseproject.ui.setting.activity;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yueworld.baseproject.R;
import com.yueworld.baseproject.base.BaseActivity;
import com.yueworld.baseproject.base.BasePresenter;
import com.yueworld.baseproject.utils.CommonUtils;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.DataCleanManager;
import com.yueworld.baseproject.utils.StringUtils;
import com.yueworld.baseproject.utils.UIUtils;

public class SettingActivity extends BaseActivity {


    private LinearLayout llCache,llAbout;
    private TextView tvCacheSize;
    private PopupWindow window;
    private LayoutInflater mInflater;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleTxt(getString(R.string.txt_setting));
        initView();
        initEvent();
    }


    private void initView(){
        mInflater=LayoutInflater.from(this);
        llCache= (LinearLayout) FindViewById(R.id.ll_cache);
        llAbout= (LinearLayout) FindViewById(R.id.ll_about);
        tvCacheSize= (TextView) FindViewById(R.id.tv_cache_size);
        tvCacheSize.setText(DataCleanManager.getCacheSize(this));
    }

    private void initEvent(){
        llCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSurePop(llCache);

            }
        });

        llAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    private void showSurePop(final View parent){
        window=new PopupWindow(this);
        View itemView=mInflater .inflate(R.layout.pop_sure_layout,null);
        TextView tvInfo=itemView.findViewById(R.id.tv_shuoming);
        tvInfo.setText(getString(R.string.txt_pop_info));
        TextView tvSure= (TextView) itemView.findViewById(R.id.tv_sure);
        TextView tvCancel=itemView.findViewById(R.id.tv_cancel);
        tvSure.setTextColor(Color.parseColor(Constant.APP_SKIN_CODE));
        tvCancel.setTextColor(Color.parseColor(Constant.APP_SKIN_CODE));
        window.setContentView(itemView);
        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setFocusable(true);
        window.setOutsideTouchable(false);
        window.setTouchable(true);
        window.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        window.showAtLocation(parent, Gravity.CENTER, 0, 0);
        window.update();
        CommonUtils.setWindow(0.2f, this);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                window = null;
                CommonUtils.setWindow(1, SettingActivity.this);
            }
        });

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                DataCleanManager.clearCache(SettingActivity.this);
                tvCacheSize.setText(0+"");
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
            }
        });
    }
}
