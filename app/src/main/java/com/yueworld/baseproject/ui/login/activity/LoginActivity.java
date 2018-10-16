package com.yueworld.baseproject.ui.login.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yueworld.baseproject.R;
import com.yueworld.baseproject.base.BaseActivity;
import com.yueworld.baseproject.ui.home.activity.HomeActivity;
import com.yueworld.baseproject.ui.login.ILoginView;
import com.yueworld.baseproject.ui.login.presenter.LoginPresenter;
import com.yueworld.baseproject.ui.respBean.UserBean;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.LocalManageUtil;
import com.yueworld.baseproject.utils.SharePref;
import com.yueworld.baseproject.utils.StringUtils;
import com.yueworld.baseproject.utils.UIUtils;
import com.yueworld.baseproject.view.ConditionalSelectorView;

import java.util.ArrayList;
import java.util.List;

import flyn.Eyes;

public class LoginActivity extends BaseActivity<LoginPresenter> implements View.OnClickListener, ILoginView {

    private TextView tvLogin;
    private EditText etEmail,etPassword;
    private TextView tvForgotPassword,tvRigter,tvLanguage;
    private ImageView ivLoginFacebook,ivLoginWechat,ivLoginWeibo,ivSelectedLanguage;
    private ConditionalSelectorView mLanguageChangeView;
    private List<String> languages=new ArrayList<>();

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarVisible(false);
        Eyes.setStatusBarColor(this,getResources().getColor(R.color.colorBottomBg));
        initView();
        initEvent();
    }

    private void initView(){
        languages.add(getResources().getString(R.string.language_cn));
        languages.add(getResources().getString(R.string.language_en));
        tvLogin=FindViewById(R.id.tv_login);
        tvLogin.setBackgroundColor(Color.parseColor(Constant.APP_SKIN_CODE));
        etEmail=FindViewById(R.id.et_email);
        etPassword=FindViewById(R.id.et_password);
        tvForgotPassword=FindViewById(R.id.tv_forgot_password);
        ivLoginFacebook=FindViewById(R.id.iv_login_facebook);
        ivLoginWechat=FindViewById(R.id.iv_login_wechat);
        ivLoginWeibo=FindViewById(R.id.iv_login_weibo);
        tvRigter=FindViewById(R.id.tv_register);
        tvLanguage=FindViewById(R.id.tv_language);
        ivSelectedLanguage=FindViewById(R.id.iv_selected_applanguage);
        mLanguageChangeView=new ConditionalSelectorView(this,languages);
        tvLanguage.setText(LocalManageUtil.getSelectLanguage(this));
    }

    private void initEvent(){
        tvLogin.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
        tvRigter.setOnClickListener(this);
        ivLoginFacebook.setOnClickListener(this);
        ivLoginWechat.setOnClickListener(this);
        ivLoginWeibo.setOnClickListener(this);
        ivSelectedLanguage.setOnClickListener(this);
        mLanguageChangeView.setSelectCallBack(new ConditionalSelectorView.selectorCallBack() {
            @Override
            public void selectCondition(String condition, int position) {
                tvLanguage.setText(languages.get(position));
                selectLanguage(position);
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent=null;
        switch (view.getId()){
            case R.id.tv_login:
                String email=etEmail.getText().toString().trim();
                String pwd=etPassword.getText().toString().trim();
                if(StringUtils.isEmpty(email)){
                    UIUtils.showToast(getResources().getString(R.string.toast_no_email));
                    return;
                }
//                if(StringUtils.isEmail(etEmail.getText().toString().trim())){
//                    UIUtils.showToast(getResources().getString(R.string.toast_email_irregular));
//                    return;
//                }
                if(StringUtils.isEmpty(pwd)){
                    UIUtils.showToast(getResources().getString(R.string.toast_no_password));
                    return;
                }
                mPresenter.login(email,pwd);
                break;
            case R.id.tv_forgot_password:
                intent=new Intent(this,ForgotPwdActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_login_facebook:
                UIUtils.showToast("facebook 登录");
                break;
            case R.id.iv_login_wechat:
                UIUtils.showToast("微信登录");
                break;
            case R.id.iv_login_weibo:
                UIUtils.showToast("微博登录");
                break;
            case R.id.tv_register:
                intent=new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_selected_applanguage:
                mLanguageChangeView.showConditionalSelectorView();
                break;
        }
    }

    private void selectLanguage(int select) {
        LocalManageUtil.saveSelectLanguage(this, select);
        recreate();
    }

    @Override
    public void loginSuccess(UserBean bean) {
        dissMissDialog();
        new SharePref(this).save(Constant.USER_KEY,bean);
        new SharePref(this).setStringValue(Constant.USER_TOKEN,bean.getUserToken());
        Constant.userBean=bean;
        Intent intent=new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFail() {
        dissMissDialog();

    }
}
