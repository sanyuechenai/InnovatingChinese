package com.yueworld.baseproject.ui.splash;

import com.yueworld.baseproject.ui.respBean.InitAppRespBean;

/**
 * 创建时间: 2018/8/25.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public interface ISplashView {

    void initAppSuccess(InitAppRespBean bean);

    void initAppFail();
}
