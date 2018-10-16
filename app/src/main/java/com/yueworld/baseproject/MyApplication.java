package com.yueworld.baseproject;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;

import com.yueworld.baseproject.ui.login.activity.LoginActivity;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.LocalManageUtil;
import com.yueworld.baseproject.utils.SharePref;
import com.yueworld.baseproject.utils.StringUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Vincent Chen on 2018/6/6.
 */
public class MyApplication extends Application {

    private static Context mContext;//上下文
    private static Thread mMainThread;//主线程
    private static long mMainThreadId;//主线程id
    private static Looper mMainLooper;//循环队列
    private static Handler mHandler;//主线程Handler
    public static MyApplication myApplication=null;
    public static List<Activity> mActivities = new LinkedList<Activity>();

    public synchronized static MyApplication getInstance() {
        if (null == myApplication) {
            myApplication = new MyApplication();
        }
        return myApplication;
    }

    @Override
    protected void attachBaseContext(Context base) {
        LocalManageUtil.saveSystemCurrentLanguage(base);
        super.attachBaseContext(base);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        LocalManageUtil.setApplicationLanguage(this);
        //对全局属性赋值

        mContext = getApplicationContext();
        mMainThread = Thread.currentThread();
        mMainThreadId = android.os.Process.myTid();
        mHandler = new Handler();
        String skinColor=new SharePref().getStringValue(Constant.SKIN_TAG);//获取皮肤设置信息
        if(!StringUtils.isEmpty(skinColor)){
            Constant.APP_SKIN_CODE=skinColor;
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //保存系统选择语言
        LocalManageUtil.onConfigurationChanged(getApplicationContext());
    }

    /**
     * 重启当前应用
     */
    public static void restart() {
        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }

    public static Context getContext() {
        return mContext;
    }

    public static String getUserToken(){
        return new SharePref(getContext()).getStringValue(Constant.USER_TOKEN);
    }

    public static void logout(){
        new SharePref(mContext).clearData();
        Constant.userBean=null;
        clearAllActivities();
        Intent intent = new Intent();
        intent.setClass(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }

    public static String parseDataError(){
        return getContext().getString(R.string.parse_error);
    }



    public static void setContext(Context mContext) {
        MyApplication.mContext = mContext;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static void setMainThread(Thread mMainThread) {
        MyApplication.mMainThread = mMainThread;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static void setMainThreadId(long mMainThreadId) {
        MyApplication.mMainThreadId = mMainThreadId;
    }

    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    public static void setMainThreadLooper(Looper mMainLooper) {
        MyApplication.mMainLooper = mMainLooper;
    }

    public static Handler getMainHandler() {
        return mHandler;
    }

    public static void setMainHandler(Handler mHandler) {
        MyApplication.mHandler = mHandler;
    }

    /***
     * <清除所有activitys> <功能详细描述> 返回类型:void
     */
    public static void clearAllActivities() {
        for (Activity activity : mActivities) {
            activity.finish();
            activity = null;
        }
        mActivities.clear();
    }

}
