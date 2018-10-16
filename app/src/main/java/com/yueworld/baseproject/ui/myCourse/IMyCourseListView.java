package com.yueworld.baseproject.ui.myCourse;

import com.yueworld.baseproject.ui.respBean.MyCourseListResp;

/**
 * 创建时间: 2018/9/26.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public interface IMyCourseListView {

    void getMyCourseListSuccess(MyCourseListResp resp);

    void getMyCourseListFail();
}
