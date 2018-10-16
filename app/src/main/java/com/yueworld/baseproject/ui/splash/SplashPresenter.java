package com.yueworld.baseproject.ui.splash;

import com.yueworld.baseproject.api.SubscriberCallBack;
import com.yueworld.baseproject.base.BasePresenter;
import com.yueworld.baseproject.ui.respBean.InitAppRespBean;
import com.yueworld.baseproject.utils.CommonUtils;
import com.yueworld.baseproject.utils.GsonHelp;
import com.yueworld.baseproject.utils.NetErrorUtil;
import com.yueworld.baseproject.utils.UIUtils;
import com.yueworld.baseproject.utils.URLutil;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;

/**
 * 创建时间: 2018/8/25.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class SplashPresenter extends BasePresenter<ISplashView> {
    public SplashPresenter(ISplashView view) {
        super(view);
    }


    /**
     * 初始化app
     */
    public void initApp(){
        Map<String,String> param=new HashMap<>();
        param.put("AppNo",CommonUtils.getUniqueId());
        param.put("OsVersion","android "+CommonUtils.getVersionName());
        addSubscription(mApiService.initApp(URLutil.INIT_ALL_CD, GsonHelp.objectToJsonString(param)), new Subscriber<InitAppRespBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                NetErrorUtil.errorType(e);

            }

            @Override
            public void onNext(InitAppRespBean bean) {
                if(bean.getSuccess()==100){
                    mView.initAppSuccess(bean);
                }else{
                    mView.initAppFail();
                }
            }
        });
    }
}
