package com.yueworld.baseproject.ui.allCourse.adapter;

import android.view.View;

import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;
import com.yueworld.baseproject.R;
import com.yueworld.baseproject.ui.respBean.RecommendListResp;

import java.util.List;

/**
 * 创建时间: 2018/8/22.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class RecommendCourseAdapter extends BaseQuickAdapter<RecommendListResp.ReturnParamsBean,BaseViewHolder> {

    private ItemClickBack clickBack;
    public RecommendCourseAdapter(List<RecommendListResp.ReturnParamsBean> data) {
        super(R.layout.item_recommend_course, data);
    }


    public void setItemClick(ItemClickBack clickBack){
        this.clickBack=clickBack;
    }
    @Override
    protected void convert(BaseViewHolder helper, final RecommendListResp.ReturnParamsBean item) {
        helper.setText(R.id.tv_title_cn,item.getCourseNameCN());
        helper.setText(R.id.tv_title_en,item.getCourseName());
        helper.setOnClickListener(R.id.cardview_recommend_course, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickBack!=null){
                    clickBack.onItemClickBack(item);
                }
            }
        });
    }

    public interface  ItemClickBack{
        void onItemClickBack(RecommendListResp.ReturnParamsBean item);
    }
}
