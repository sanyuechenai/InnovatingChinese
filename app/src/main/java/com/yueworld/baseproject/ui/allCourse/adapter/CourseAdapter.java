package com.yueworld.baseproject.ui.allCourse.adapter;

import android.view.View;

import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.yueworld.baseproject.MyApplication;
import com.yueworld.baseproject.R;
import com.yueworld.baseproject.ui.respBean.CourseListResp;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.StringUtils;
import com.yueworld.baseproject.view.CircleImage;

import java.util.List;

/**
 * 创建时间: 2018/8/22.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class CourseAdapter extends BaseQuickAdapter<CourseListResp.ReturnParamsBean,BaseViewHolder> {

    private itemClickBack clickBack;
    public CourseAdapter(List<CourseListResp.ReturnParamsBean> data) {
        super(R.layout.item_course, data);
    }


    public void setItemClick(itemClickBack clickBack){
        this.clickBack=clickBack;
    }
    @Override
    protected void convert(BaseViewHolder helper, final CourseListResp.ReturnParamsBean item) {
        if(Constant.APP_LANGUAGE_CODE==0){
            helper.setText(R.id.tv_course_name,item.getCourseName());
        }else{
            helper.setText(R.id.tv_course_name,item.getCourseNameCN());
        }
        helper.setText(R.id.tv_course_level,"LEVEL: "+item.getLevelId());
        CircleImage iconCourse=helper.getView(R.id.iv_course_icon);
        if(!StringUtils.isEmpty(item.getCourseImgSrc())){
            Picasso.with(MyApplication.getContext()).load(item.getCourseImgSrc()).placeholder(R.mipmap.my_pic)
                    .error(R.mipmap.my_pic)
                    .into(iconCourse);
        }else{
            iconCourse.setImageResource(R.mipmap.my_pic);
        }

        helper.setOnClickListener(R.id.cardview_item, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickBack!=null){
                    clickBack.onItemClickBack(item);
                }
            }
        });
    }

    public interface  itemClickBack{
        void onItemClickBack(CourseListResp.ReturnParamsBean item);
    }
}
