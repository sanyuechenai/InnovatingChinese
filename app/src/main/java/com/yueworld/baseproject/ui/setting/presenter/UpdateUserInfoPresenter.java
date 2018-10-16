package com.yueworld.baseproject.ui.setting.presenter;

import com.yueworld.baseproject.MyApplication;
import com.yueworld.baseproject.api.SubscriberCallBack;
import com.yueworld.baseproject.base.BasePresenter;
import com.yueworld.baseproject.ui.setting.IUpdatePersonInfoView;
import com.yueworld.baseproject.utils.AESUtil;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.URLutil;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建时间: 2018/8/25.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class UpdateUserInfoPresenter extends BasePresenter<IUpdatePersonInfoView> {
    public UpdateUserInfoPresenter(IUpdatePersonInfoView view) {
        super(view);
    }


    /**
     * 更新用户信息
     * @param nickname
     * @param userSex
     * @param age
     */
    public void updateUserInfo(String nickname,String userSex,String age){
        Map<String,String> param=new HashMap<>();
        param.put("UserToken", MyApplication.getUserToken());
        param.put("NickName",nickname);
        param.put("UserGender",userSex);
        param.put("UserAge",age);
        param.put("LangId","EN");
        String pm= AESUtil.encrypt(param);
        addSubscription(mApiService.updateUserInfo(URLutil.USER_UPDATE_BASEINFO, Constant.appKy, pm), new SubscriberCallBack() {
            @Override
            protected void onSuccess(String response) {
                mView.updateUserInfoSuccess();

            }

            @Override
            protected void onFailure() {
                mView.updateUserInfoFail();

            }
        });

    }
}
