package com.yueworld.baseproject.ui.allCourse;

import com.yueworld.baseproject.ui.respBean.CategoryListResp;
import com.yueworld.baseproject.ui.respBean.RecommendListResp;

/**
 * 创建时间: 2018/9/26.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public interface ICategoryListView {

    void getCategoryListSuccess(CategoryListResp resp);

    void getRecommendListSuccess(RecommendListResp resp);

    void getDataFail();

}
