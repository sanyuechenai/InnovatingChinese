package com.yueworld.baseproject.ui.myCourse.adapter;

import android.view.View;
import android.widget.ProgressBar;

import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;
import com.yueworld.baseproject.MyApplication;
import com.yueworld.baseproject.R;
import com.yueworld.baseproject.ui.respBean.MyCourseListResp;
import com.yueworld.baseproject.utils.Constant;

import java.util.List;

/**
 * 创建时间: 2018/8/22.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class CurrentCourseAdapter extends BaseQuickAdapter<MyCourseListResp.ReturnParamsBean,BaseViewHolder> {

    private itemClickBack clickBack;
    public CurrentCourseAdapter( List<MyCourseListResp.ReturnParamsBean> data) {
        super(R.layout.item_current_course, data);
    }


    public void setItemClick(itemClickBack clickBack){
        this.clickBack=clickBack;
    }
    @Override
    protected void convert(BaseViewHolder helper, final MyCourseListResp.ReturnParamsBean item) {
        int progress=item.getUnitFinishedCount()/item.getCourUnitCount();
        helper.setProgress(R.id.progressBar,progress);
        ProgressBar progressBar=helper.getView(R.id.progressBar);
        if(Constant.APP_SKIN_CODE.equals("#4ad0af")){
            progressBar.setProgressDrawable(MyApplication.getContext().getResources().getDrawable(R.drawable.progressbar_style));
        }else{
            progressBar.setProgressDrawable(MyApplication.getContext().getResources().getDrawable(R.drawable.progressbar_style2));
        }

        if(Constant.APP_LANGUAGE_CODE==0){
            helper.setText(R.id.tv_course_name,item.getCourseName());
        }else{
            helper.setText(R.id.tv_course_name,item.getCourseNameCN());
        }
        helper.setText(R.id.tv_course_count,item.getUnitFinishedCount()+"/"+item.getCourUnitCount());
        helper.setText(R.id.tv_course_core,item.getCourScore()+"");
        helper.setOnClickListener(R.id.rl_item, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickBack!=null){
                    clickBack.onItemClickBack(item);
                }
            }
        });
    }

    public interface  itemClickBack{
        void onItemClickBack(MyCourseListResp.ReturnParamsBean item);
    }
}
