package com.yueworld.baseproject.ui.login.presenter;

import android.util.Log;

import com.yueworld.baseproject.MyApplication;
import com.yueworld.baseproject.R;
import com.yueworld.baseproject.api.SubscriberCallBack;
import com.yueworld.baseproject.base.BasePresenter;
import com.yueworld.baseproject.ui.login.ILoginView;
import com.yueworld.baseproject.ui.respBean.UserBean;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.GsonHelp;
import com.yueworld.baseproject.utils.SharePref;
import com.yueworld.baseproject.utils.UIUtils;
import com.yueworld.baseproject.utils.URLutil;
import com.yueworld.baseproject.utils.AESUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建时间: 2018/8/25.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class LoginPresenter extends BasePresenter<ILoginView> {
    public LoginPresenter(ILoginView view) {
        super(view);
    }


    /**
     * 用户名密码登录
     * @param userId
     * @param password
     */
    public void login(String userId,String password){
        Map<String,String> paramMap=new HashMap<>();
        paramMap.put("UserID",userId);
        paramMap.put("Password",password);
        String pm= AESUtil.encrypt(paramMap);
        addSubscription(mApiService.login(URLutil.LOGIN_CD, Constant.appKy, pm), new SubscriberCallBack() {
            @Override
            protected void onSuccess(String response) {
                try {
                    UserBean bean= (UserBean) GsonHelp.jsonStringToObject(response,UserBean.class);
                    mView.loginSuccess(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                    UIUtils.showToast(MyApplication.getContext().getString(R.string.parse_error));
                    mView.loginFail();
                }

            }

            @Override
            protected void onFailure() {
                mView.loginFail();
            }
        });

    }

    public void tokenLogin(String userToken){
        Map<String,String> paramMap=new HashMap<>();
        paramMap.put("UserToken",userToken);
        String pm= AESUtil.encrypt(paramMap);
        addSubscription(mApiService.tokenLogin(URLutil.TOKEN_LOGIN_CD, Constant.appKy, pm), new SubscriberCallBack() {
            @Override
            protected void onSuccess(String response) {
                try {
                    UserBean bean= (UserBean) GsonHelp.jsonStringToObject(response,UserBean.class);
                    mView.loginSuccess(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                    UIUtils.showToast(MyApplication.getContext().getString(R.string.parse_error));
                    mView.loginFail();
                }

            }

            @Override
            protected void onFailure() {
                    mView.loginFail();
            }
        });
    }
}
