package com.yueworld.baseproject.api;

import android.accounts.NetworkErrorException;
import android.util.Log;


import com.yueworld.baseproject.MyApplication;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.SharePref;
import com.yueworld.baseproject.utils.StringUtils;
import com.yueworld.baseproject.utils.UIUtils;
import com.yueworld.baseproject.utils.AESUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;


/**
 * @create 2018/7/9
 * @author Vincent Chen
 * @Describe
 */

public abstract class SubscriberCallBack extends Subscriber {

    /**
     * 对返回的code统一处理
     * @param response
     */
    @Override
    public void onNext(Object response) {
        try {
            String decipherString= AESUtil.decrypt(Constant.secretKey.getBytes(),response.toString());
            JSONObject jsonObject=new JSONObject(decipherString);
            Log.i("1234", "Object>>: "+response.toString());
            Log.i("1234", "response>>: "+jsonObject.toString());
            if((jsonObject.optInt("Success"))==100){
                onSuccess(decipherString);
            }else if((jsonObject.optInt("Success"))==300203){//用户token失效，重新登录
                MyApplication.logout();
            } else{
                if(!StringUtils.isEmpty(jsonObject.optString("ReturnString"))){
                    UIUtils.showToast(jsonObject.optString("ReturnString").toString());
                }
                onFailure();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            UIUtils.showToast("数据解析错误");
            onFailure();
        }

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if(e instanceof ConnectException
                || e instanceof TimeoutException
                || e instanceof NetworkErrorException
                || e instanceof UnknownHostException){
            UIUtils.showToast("网络错误，请稍后再试");
        }else{
            UIUtils.showToast(e.getMessage());
        }
        onFailure();
    }

    protected abstract void onSuccess(String response);
//    protected abstract void onError();

    protected abstract void onFailure();

}
