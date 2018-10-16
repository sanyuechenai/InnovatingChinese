package com.yueworld.baseproject.ui.respBean;

/**
 * 创建时间: 2018/10/9.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class MessageEvent {

    private int msgType;//0 更换皮肤为深绿色，1更换皮肤为浅绿色，2 用户信息更改

    public MessageEvent(int msgType) {
        this.msgType = msgType;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }
}
