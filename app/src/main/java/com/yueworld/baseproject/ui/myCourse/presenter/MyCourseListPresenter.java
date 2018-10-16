package com.yueworld.baseproject.ui.myCourse.presenter;

import com.yueworld.baseproject.MyApplication;
import com.yueworld.baseproject.R;
import com.yueworld.baseproject.api.SubscriberCallBack;
import com.yueworld.baseproject.base.BasePresenter;
import com.yueworld.baseproject.ui.myCourse.ICourseView;
import com.yueworld.baseproject.ui.myCourse.IMyCourseListView;
import com.yueworld.baseproject.ui.respBean.MyCourseListResp;
import com.yueworld.baseproject.utils.AESUtil;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.GsonHelp;
import com.yueworld.baseproject.utils.SharePref;
import com.yueworld.baseproject.utils.UIUtils;
import com.yueworld.baseproject.utils.URLutil;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建时间: 2018/8/23.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class MyCourseListPresenter extends BasePresenter<IMyCourseListView> {
    public MyCourseListPresenter(IMyCourseListView view) {
        super(view);
    }


    public void getMyCourseList(String isFinished,int page){
        String userToken=MyApplication.getUserToken();
        Map<String,String> param=new HashMap<>();
        param.put("UserToken",userToken);
        param.put("IsFinished",isFinished);
        param.put("PageIndex",page+"");
        String pm= AESUtil.encrypt(param);
        addSubscription(mApiService.getMyCourseList(URLutil.MY_COURSE_LIST_CD, Constant.appKy, pm), new SubscriberCallBack() {
            @Override
            protected void onSuccess(String response) {
                try {
                    MyCourseListResp resp= (MyCourseListResp) GsonHelp.jsonStringToObject(response,MyCourseListResp.class);
                    mView.getMyCourseListSuccess(resp);
                } catch (Exception e) {
                    e.printStackTrace();
                    UIUtils.showToast(MyApplication.getContext().getString(R.string.parse_error));
                    mView.getMyCourseListFail();
                }
            }

            @Override
            protected void onFailure() {
                mView.getMyCourseListFail();
            }
        });

    }
}
