package com.yueworld.baseproject.ui.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yueworld.baseproject.R;
import com.yueworld.baseproject.base.BaseActivity;
import com.yueworld.baseproject.ui.home.activity.HomeActivity;
import com.yueworld.baseproject.ui.login.activity.LoginActivity;
import com.yueworld.baseproject.ui.respBean.InitAppRespBean;
import com.yueworld.baseproject.ui.respBean.UserBean;
import com.yueworld.baseproject.utils.CommonUtils;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.SharePref;
import com.yueworld.baseproject.utils.StringUtils;
import com.yueworld.baseproject.utils.UIUtils;

public class SplashActivity extends BaseActivity<SplashPresenter> implements ISplashView {

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarVisible(false);
        setNeedPermissionCallBack(new PermissionCallBack() {
            @Override
            public void doNext() {
                String appVersion=new SharePref().getStringValue(Constant.APP_VERSION);
                if(!CommonUtils.getVersionName().equals(appVersion)){
                    mPresenter.initApp();
                }else{
                    toActivity();
                }
            }
        });
    }

    @Override
    public void initAppSuccess(InitAppRespBean bean) {
        new SharePref().setLongValue(Constant.TOKEN_KEY,bean.getTokenKey());
        new SharePref().setStringValue(Constant.TOKEN_DEVICE,bean.getTokenDevice());
        new SharePref().setStringValue(Constant.APP_VERSION,CommonUtils.getVersionName());
        toActivity();
    }

    @Override
    public void initAppFail() {
        UIUtils.showToast("app 初始化失败，可能网络不好");
    }


    private void toActivity(){
        initAppKYandSecretKey();
        String userToken=new SharePref(this).getStringValue(Constant.USER_TOKEN);
        Intent intent=null;
        if(!StringUtils.isEmpty(userToken)){
            Constant.userBean= (UserBean) new SharePref(this).get(Constant.USER_KEY);
            intent=new Intent(SplashActivity.this, HomeActivity.class);
        }else{
            intent=new Intent(SplashActivity.this, LoginActivity.class);
        }
        startActivity(intent);
        finish();
    }


    private void initAppKYandSecretKey(){
        Constant.secretKey=new SharePref().getStringValue(Constant.TOKEN_DEVICE);
        Constant.appKy=new SharePref().getLongValue(Constant.TOKEN_KEY)+"";
    }
}
