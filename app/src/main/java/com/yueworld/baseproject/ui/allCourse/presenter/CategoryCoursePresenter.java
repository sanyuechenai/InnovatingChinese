package com.yueworld.baseproject.ui.allCourse.presenter;

import com.yueworld.baseproject.MyApplication;
import com.yueworld.baseproject.api.SubscriberCallBack;
import com.yueworld.baseproject.base.BasePresenter;
import com.yueworld.baseproject.ui.allCourse.IAllCategoryView;
import com.yueworld.baseproject.ui.allCourse.ICategoryCourseView;
import com.yueworld.baseproject.ui.respBean.CategoryListResp;
import com.yueworld.baseproject.ui.respBean.CourseListResp;
import com.yueworld.baseproject.ui.respBean.LevelListBean;
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

public class CategoryCoursePresenter extends BasePresenter<ICategoryCourseView> {
    public CategoryCoursePresenter(ICategoryCourseView view) {
        super(view);
    }


    /**
     * 获取课程等级
     */
    public void getLevelList(){
        addSubscription(mApiService.getLevelList(URLutil.LEVEL_QUERY_CD, Constant.appKy), new SubscriberCallBack() {
            @Override
            protected void onSuccess(String response) {
                try {
                    LevelListBean resp= (LevelListBean) GsonHelp.jsonStringToObject(response,LevelListBean.class);
                    mView.getLevelListSuccess(resp);
                } catch (Exception e) {
                    e.printStackTrace();
                    UIUtils.showToast(MyApplication.parseDataError());
                    mView.getListFail();
                }
            }

            @Override
            protected void onFailure() {
                mView.getListFail();
            }
        });
    }


    /**
     * 获取分类下的课程
     * @param currentPage
     * @param catgId
     * @param levelId
     * @param orderBy
     */
    public void getCourseList(int currentPage,String catgId,String levelId,String orderBy){
        Map<String,String> param=new HashMap<>();
        param.put("UserToken",MyApplication.getUserToken());
        param.put("PageIndex",currentPage+"");
        param.put("CatgId",catgId);
        param.put("LvlId",levelId);
        param.put("OrderBy",orderBy);
        String pm= AESUtil.encrypt(param);
        addSubscription(mApiService.getCourseList(URLutil.COURSE_GET_LIST, Constant.appKy, pm), new SubscriberCallBack() {
            @Override
            protected void onSuccess(String response) {
                try {
                    CourseListResp resp= (CourseListResp) GsonHelp.jsonStringToObject(response,CourseListResp.class);
                    mView.getCourseListSuccess(resp);
                } catch (Exception e) {
                    e.printStackTrace();
                    UIUtils.showToast(MyApplication.parseDataError());
                    mView.getListFail();
                }
            }

            @Override
            protected void onFailure() {
                mView.getListFail();

            }
        });
    }


    /**
     * 添加我的课程
     * @param courseId
  courseId
     */
    public void addCourseToMy(String courseId){
        Map<String,String> param=new HashMap<>();
        param.put("UserToken",MyApplication.getUserToken());
        param.put("CourseId",courseId);
        String pm= AESUtil.encrypt(param);
        addSubscription(mApiService.addToMyCourse(URLutil.ADD_TO_MY_COURSE_CD, Constant.appKy, pm), new SubscriberCallBack() {
            @Override
            protected void onSuccess(String response) {
                mView.addToMyCourseSuccess();

            }

            @Override
            protected void onFailure() {
                mView.addToMyCourseFail();

            }
        });
    }
}
