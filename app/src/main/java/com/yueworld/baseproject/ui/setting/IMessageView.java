package com.yueworld.baseproject.ui.setting;

import com.yueworld.baseproject.ui.respBean.MessageListResp;

/**
 * 创建时间: 2018/9/25.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public interface IMessageView {

    void getMessageListSuccess(MessageListResp resp);

    void getMessageListFail();
}
