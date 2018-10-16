package com.yueworld.baseproject.ui.login;

import com.yueworld.baseproject.ui.respBean.UserBean;

/**
 * 创建时间: 2018/8/25.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public interface ILoginView {

    void loginSuccess(UserBean bean);

    void loginFail();
}
