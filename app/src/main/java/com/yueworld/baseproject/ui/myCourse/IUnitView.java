package com.yueworld.baseproject.ui.myCourse;

import com.yueworld.baseproject.ui.respBean.UnitSentenceListResp;

/**
 * 创建时间: 2018/8/23.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public interface IUnitView {


    void getUnitSentenceListSuccess(UnitSentenceListResp resp);

    void getDataFail();
}
