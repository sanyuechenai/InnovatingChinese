package com.yueworld.baseproject.ui.myCourse.presenter;

import com.yueworld.baseproject.MyApplication;
import com.yueworld.baseproject.api.SubscriberCallBack;
import com.yueworld.baseproject.base.BasePresenter;
import com.yueworld.baseproject.ui.myCourse.ICourseView;
import com.yueworld.baseproject.ui.respBean.UnitListResp;
import com.yueworld.baseproject.utils.AESUtil;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.GsonHelp;
import com.yueworld.baseproject.utils.UIUtils;
import com.yueworld.baseproject.utils.URLutil;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建时间: 2018/8/23.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class CoursePresenter extends BasePresenter<ICourseView> {
    public CoursePresenter(ICourseView view) {
        super(view);
    }


    /**
     * 获取课程下的单元
     * @param courseId
     */
    public void getUnitByCourse(String courseId){
        Map<String,String> param=new HashMap<>();
        param.put("UserToken", MyApplication.getUserToken());
        param.put("CourseId",courseId);
        String pm= AESUtil.encrypt(param);
        addSubscription(mApiService.getUnitList(URLutil.UNIT_GET_LIST, Constant.appKy, pm), new SubscriberCallBack() {
            @Override
            protected void onSuccess(String response) {

                try {
                    UnitListResp resp= (UnitListResp) GsonHelp.jsonStringToObject(response,UnitListResp.class);
                    mView.getUnitListSuccess(resp);
                } catch (Exception e) {
                    e.printStackTrace();
                    UIUtils.showToast(MyApplication.parseDataError());
                    mView.getDataFail();
                }

            }

            @Override
            protected void onFailure() {
                mView.getDataFail();
            }
        });
    }
}
