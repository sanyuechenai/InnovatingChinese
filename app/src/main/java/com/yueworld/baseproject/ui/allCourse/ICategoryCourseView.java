package com.yueworld.baseproject.ui.allCourse;

import com.yueworld.baseproject.ui.respBean.CourseListResp;
import com.yueworld.baseproject.ui.respBean.LevelListBean;

/**
 * 创建时间: 2018/8/25.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public interface ICategoryCourseView {

    void getLevelListSuccess(LevelListBean resp);

    void getCourseListSuccess(CourseListResp resp);

    void addToMyCourseSuccess();

    void addToMyCourseFail();

    void getListFail();
}
