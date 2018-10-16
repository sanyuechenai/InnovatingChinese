package com.yueworld.baseproject.ui.login.activity;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yueworld.baseproject.R;
import com.yueworld.baseproject.base.BaseActivity;
import com.yueworld.baseproject.ui.login.presenter.ForgotPwdPresenter;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.StringUtils;
import com.yueworld.baseproject.utils.UIUtils;

public class ForgotPwdActivity extends BaseActivity<ForgotPwdPresenter> {


    private EditText etEmail,etCode,etPwd,etConfirmPwd;
    private TextView tvSend,tvSubmit;
    private static final int HANDLER_MSG=0X66;
    private int seconds=60;

    @Override
    protected ForgotPwdPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_forgot_pwd;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleTxt(getString(R.string.txt_title_forgot_pwd));
        initView();
        initEvent();
    }


    private void initView(){
        etEmail=FindViewById(R.id.et_email);
        etCode=FindViewById(R.id.et_code);
        etPwd=FindViewById(R.id.et_new_pwd);
        etConfirmPwd=FindViewById(R.id.et_confirm_newpwd);
        tvSend=FindViewById(R.id.tv_send);
        tvSubmit=FindViewById(R.id.tv_sumit);
        tvSubmit.setBackgroundColor(Color.parseColor(Constant.APP_SKIN_CODE));
    }


    private void initEvent(){
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=etEmail.getText().toString().trim();
                if(StringUtils.isEmpty(email)){
                    UIUtils.showToast(getString(R.string.txt_toast_email));
                    return;
                }

                if(!StringUtils.isEmail(email)){
                    UIUtils.showToast(getString(R.string.txt_toast_email_not_normal));
                    return;
                }
                tvSend.setText(seconds+"s");
                tvSend.setEnabled(false);
                mHandler.sendEmptyMessageDelayed(HANDLER_MSG,1000);
            }
        });

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=etEmail.getText().toString().trim();
                String code =etCode.getText().toString().trim();
                String pwd=etPwd.getText().toString().trim();
                String pwd2=etConfirmPwd.getText().toString().trim();
                if(judgeTxtNull(email,code,pwd,pwd2)){
                    UIUtils.showToast("提交");
                }
            }
        });
    }


    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==HANDLER_MSG){
                seconds=seconds-1;
                if(seconds==0){
                    tvSend.setText(getString(R.string.txt_send));
                    tvSend.setEnabled(true);
                }else{
                    tvSend.setText(seconds+"s");
                    mHandler.sendEmptyMessageDelayed(HANDLER_MSG,1000);
                }
            }
        }
    };


    private boolean judgeTxtNull(String email,String code,String pwd,String pwd2){
        if(StringUtils.isEmpty(email)){
            UIUtils.showToast(getString(R.string.txt_toast_email));
            return false;
        }
        if(StringUtils.isEmail(email)){
            UIUtils.showToast(getString(R.string.txt_toast_email_not_normal));
            return false;
        }
        if(StringUtils.isEmpty(code)){
            UIUtils.showToast(getString(R.string.txt_toast_code));
            return false;
        }
        if(StringUtils.isEmpty(pwd)){
            UIUtils.showToast(getString(R.string.txt_toast_pwd));
            return false;
        }
        if(StringUtils.isEmpty(pwd2)){
            UIUtils.showToast(getString(R.string.txt_toast_confirm_pwd));
            return false;
        }

        if(!TextUtils.equals(pwd,pwd2)){
            UIUtils.showToast(getString(R.string.txt_toast_confirm_pwd));
            return false;
        }
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(HANDLER_MSG);
    }
}
