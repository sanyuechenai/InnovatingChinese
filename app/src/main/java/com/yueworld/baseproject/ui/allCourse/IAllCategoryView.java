package com.yueworld.baseproject.ui.allCourse;

import com.yueworld.baseproject.ui.respBean.CategoryListResp;

/**
 * 创建时间: 2018/8/24.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public interface IAllCategoryView {

    void getCategoryListSuccess(CategoryListResp resp);

    void getCategoryListFail();
}
