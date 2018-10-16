package com.yueworld.baseproject.utils;

import android.accounts.NetworkErrorException;

import com.yueworld.baseproject.MyApplication;
import com.yueworld.baseproject.R;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

/**
 * 创建时间: 2018/9/26.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class NetErrorUtil {
    public static void errorType(Throwable e){
        if(e instanceof ConnectException
                || e instanceof TimeoutException
                || e instanceof NetworkErrorException
                || e instanceof UnknownHostException){
            UIUtils.showToast(MyApplication.getContext().getString(R.string.net_error));
        }else{
            UIUtils.showToast(e.getMessage());
        }
    }
}
