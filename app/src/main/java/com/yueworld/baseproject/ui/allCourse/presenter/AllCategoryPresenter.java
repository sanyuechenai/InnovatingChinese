package com.yueworld.baseproject.ui.allCourse.presenter;

import com.yueworld.baseproject.MyApplication;
import com.yueworld.baseproject.api.SubscriberCallBack;
import com.yueworld.baseproject.base.BasePresenter;
import com.yueworld.baseproject.ui.allCourse.IAllCategoryView;
import com.yueworld.baseproject.ui.respBean.CategoryListResp;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.GsonHelp;
import com.yueworld.baseproject.utils.UIUtils;
import com.yueworld.baseproject.utils.URLutil;

/**
 * 创建时间: 2018/8/24.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class AllCategoryPresenter extends BasePresenter<IAllCategoryView> {
    public AllCategoryPresenter(IAllCategoryView view) {
        super(view);
    }


    /**
     * 获取分类列表
     */
    public void getCategoryList(){
        addSubscription(mApiService.getCategoryList(URLutil.CATEGORY_QUERY_CD, Constant.appKy), new SubscriberCallBack() {
            @Override
            protected void onSuccess(String response) {
                try {
                    CategoryListResp resp= (CategoryListResp) GsonHelp.jsonStringToObject(response,CategoryListResp.class);
                    mView.getCategoryListSuccess(resp);
                } catch (Exception e) {
                    e.printStackTrace();
                    UIUtils.showToast(MyApplication.parseDataError());
                    mView.getCategoryListFail();
                }

            }

            @Override
            protected void onFailure() {
                mView.getCategoryListFail();

            }
        });
    }
}
