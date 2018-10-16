package com.yueworld.baseproject.ui.setting.presenter;

import com.yueworld.baseproject.MyApplication;
import com.yueworld.baseproject.R;
import com.yueworld.baseproject.api.SubscriberCallBack;
import com.yueworld.baseproject.base.BasePresenter;
import com.yueworld.baseproject.ui.respBean.MessageListResp;
import com.yueworld.baseproject.ui.setting.IMessageView;
import com.yueworld.baseproject.ui.setting.IUpdatePersonInfoView;
import com.yueworld.baseproject.utils.AESUtil;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.GsonHelp;
import com.yueworld.baseproject.utils.UIUtils;
import com.yueworld.baseproject.utils.URLutil;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建时间: 2018/8/25.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class MessagePresenter extends BasePresenter<IMessageView> {
    public MessagePresenter(IMessageView view) {
        super(view);
    }


    /**
     *
     * 获取消息列表
     * @param page
     */
    public void getMessageList(int page){
        String userToken= MyApplication.getUserToken();
        Map<String,String> param=new HashMap<>();
        param.put("UserToken",userToken);
        param.put("PageIndex",page+"");
        String pm= AESUtil.encrypt(param);
        addSubscription(mApiService.getMessage(URLutil.MESSAGE_LIST, Constant.appKy, pm), new SubscriberCallBack() {
            @Override
            protected void onSuccess(String response) {
                try {
                    MessageListResp resp= (MessageListResp) GsonHelp.jsonStringToObject(response,MessageListResp.class);
                    mView.getMessageListSuccess(resp);
                } catch (Exception e) {
                    e.printStackTrace();
                    UIUtils.showToast(MyApplication.getContext().getString(R.string.parse_error));
                    mView.getMessageListFail();
                }

            }

            @Override
            protected void onFailure() {
                mView.getMessageListFail();
            }
        });
    }
}
