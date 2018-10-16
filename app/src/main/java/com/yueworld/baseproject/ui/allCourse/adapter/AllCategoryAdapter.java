package com.yueworld.baseproject.ui.allCourse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yueworld.baseproject.R;
import com.yueworld.baseproject.ui.allCourse.bean.CourseBean;
import com.yueworld.baseproject.ui.respBean.CategoryListResp;
import com.yueworld.baseproject.utils.PicassonUtil;
import com.yueworld.baseproject.utils.StringUtils;

import java.util.List;

/**
 * 创建时间: 2018/8/24.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class AllCategoryAdapter extends BaseAdapter {

    private Context mContext;
    private List<CategoryListResp.ReturnParamsBean> dataList;
    private LayoutInflater mInflater;

    public AllCategoryAdapter(Context context, List<CategoryListResp.ReturnParamsBean> list){
        mContext=context;
        dataList=list;
        mInflater=LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return dataList==null?0:dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View covertView, ViewGroup viewGroup) {
        MyViewHolder holder;
        if(covertView==null){
            covertView=mInflater.inflate(R.layout.item_allcategory_layout,null);
            holder=new MyViewHolder(covertView);
            covertView.setTag(holder);
        }else{
            holder= (MyViewHolder) covertView.getTag();
        }
        CategoryListResp.ReturnParamsBean bean=dataList.get(position);
        holder.tvCategoryname.setText(bean.getCatgName());
        holder.tvUnitNum.setText(bean.getCourCount()+"");
        if(!StringUtils.isEmpty(bean.getCatgImgSrc())){
            PicassonUtil.loadImg(mContext,bean.getCatgImgSrc(),holder.ivCourseIcon);
        }else{
            holder.ivCourseIcon.setImageResource(R.mipmap.my_pic);
        }
        return covertView;
    }


    class MyViewHolder {
        TextView tvCategoryname,tvUnitNum;
        ImageView ivCourseIcon;
        public MyViewHolder(View rootView){
            tvCategoryname=rootView.findViewById(R.id.tv_category_name);
            tvUnitNum=rootView.findViewById(R.id.tv_unit_num);
            ivCourseIcon=rootView.findViewById(R.id.iv_course_icon);
        }
    }
}
