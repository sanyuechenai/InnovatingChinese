package com.yueworld.baseproject.ui.myCourse.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yueworld.baseproject.R;
import com.yueworld.baseproject.base.BaseActivity;
import com.yueworld.baseproject.ui.myCourse.bean.UnitItemBean;
import com.yueworld.baseproject.ui.myCourse.presenter.UnitChallengePresenter;
import com.yueworld.baseproject.ui.respBean.UnitSentenceListResp;
import com.yueworld.baseproject.utils.CommonUtils;

import java.util.List;

public class UnitChallengeActivity extends BaseActivity<UnitChallengePresenter> {

    private TextView tvExample;
    private LinearLayout llResult;
    private List<UnitSentenceListResp.ReturnParamsBean> unitList;
    private int currentPosition=0;

    @Override
    protected UnitChallengePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_unit_challenge;
    }

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                currentPosition++;
                tvExample.setText(unitList.get(currentPosition).getSenName());
                addResultView();
                if(currentPosition!=unitList.size()-1){
                    mHandler.sendEmptyMessageDelayed(0,2000);
                }else{
                    mHandler.removeMessages(0);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unitList= (List<UnitSentenceListResp.ReturnParamsBean>) getIntent().getSerializableExtra("unit");
        setTitleTxt("Unit Name");
        initView();
        if(unitList.size()>1){
            mHandler.sendEmptyMessageDelayed(0,2000);
        }
    }


    private void initView(){
        tvExample=FindViewById(R.id.tv_example_unit);
        llResult=FindViewById(R.id.ll_result);
        if(unitList!=null&&!unitList.isEmpty()){
            tvExample.setText(unitList.get(0).getSenName());
        }
    }


    private void addResultView(){
        ImageView iv=new ImageView(this);
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(CommonUtils.px(this,20),CommonUtils.px(this,20));
        lp.rightMargin=CommonUtils.px(this,10);
        iv.setLayoutParams(lp);
        iv.setImageResource(R.mipmap.icon_small_ok);
        llResult.addView(iv);
    }

}
