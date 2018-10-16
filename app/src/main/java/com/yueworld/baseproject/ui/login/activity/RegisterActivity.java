package com.yueworld.baseproject.ui.login.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yueworld.baseproject.R;
import com.yueworld.baseproject.base.BaseActivity;
import com.yueworld.baseproject.ui.login.presenter.RegisterPresenter;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.StringUtils;
import com.yueworld.baseproject.utils.UIUtils;

public class RegisterActivity extends BaseActivity<RegisterPresenter> {

    private EditText etEmail,etNickname,etPhone,etPwd,etConfirmPwd;
    private TextView tvRegister;

    @Override
    protected RegisterPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleTxt(getString(R.string.sign_up));
        initView();
        initEvent();
    }



    private void initView(){
        etEmail=FindViewById(R.id.et_email);
        etNickname=FindViewById(R.id.et_nickname);
        etPhone=FindViewById(R.id.et_phone);
        etPwd=FindViewById(R.id.et_pwd);
        etConfirmPwd=FindViewById(R.id.et_confirm_pwd);
        tvRegister=FindViewById(R.id.tv_sign_up);
        tvRegister.setBackgroundColor(Color.parseColor(Constant.APP_SKIN_CODE));
    }


    private void initEvent(){
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=etEmail.getText().toString().trim();
                String nickname=etNickname.getText().toString().trim();
                String phone=etPhone.getText().toString().trim();
                String pwd=etPwd.getText().toString().trim();
                String pwd2=etConfirmPwd.getText().toString().trim();
                if(judgeTxtNull(email,nickname,phone,pwd,pwd2)){
                    UIUtils.showToast("开始注册");
                }
            }
        });
    }


    private boolean judgeTxtNull(String email,String nickname,String phone,String pwd,String pwd2){
        if(StringUtils.isEmpty(email)){
            UIUtils.showToast(getString(R.string.txt_toast_email));
            return false;
        }
        if(!StringUtils.isEmail(email)){
            UIUtils.showToast(getString(R.string.txt_toast_email_not_normal));
            return false;
        }
        if(StringUtils.isEmpty(nickname)){
            UIUtils.showToast(getString(R.string.txt_toast_nickname));
            return false;
        }
        if(StringUtils.isEmpty(phone)){
            UIUtils.showToast(getString(R.string.txt_toast_phone));
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
}
