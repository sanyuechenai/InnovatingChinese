package com.yueworld.baseproject.ui.allCourse.presenter;

import com.yueworld.baseproject.MyApplication;
import com.yueworld.baseproject.api.SubscriberCallBack;
import com.yueworld.baseproject.base.BasePresenter;
import com.yueworld.baseproject.ui.allCourse.ICategoryCourseView;
import com.yueworld.baseproject.ui.allCourse.ICategoryListView;
import com.yueworld.baseproject.ui.respBean.CategoryListResp;
import com.yueworld.baseproject.ui.respBean.RecommendListResp;
import com.yueworld.baseproject.utils.AESUtil;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.GsonHelp;
import com.yueworld.baseproject.utils.UIUtils;
import com.yueworld.baseproject.utils.URLutil;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建时间: 2018/8/24.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class CategoryRecomendPresenter extends BasePresenter<ICategoryListView> {
    public CategoryRecomendPresenter(ICategoryListView view) {
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
                    mView.getDataFail();
                }

            }

            @Override
            protected void onFailure() {
                mView.getDataFail();

            }
        });
    }


    /**
     * 获取推荐课程列表
     * @param page
     */
    public void getRecommendList(int page){
        Map<String,String> param=new HashMap<>();
        param.put("UserToken",MyApplication.getUserToken());
        param.put("PageIndex",page+"");
        String pm= AESUtil.encrypt(param);
        addSubscription(mApiService.getRecommendCourse(URLutil.COURSE_GET_LIST_RECOM, Constant.appKy, pm), new SubscriberCallBack() {
            @Override
            protected void onSuccess(String response) {
                try {
                    RecommendListResp resp= (RecommendListResp) GsonHelp.jsonStringToObject(response,RecommendListResp.class);
                    mView.getRecommendListSuccess(resp);
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
