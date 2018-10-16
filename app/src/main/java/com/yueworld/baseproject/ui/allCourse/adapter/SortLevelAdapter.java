package com.yueworld.baseproject.ui.allCourse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yueworld.baseproject.R;
import com.yueworld.baseproject.ui.allCourse.bean.SortItemBean;
import com.yueworld.baseproject.ui.respBean.LevelListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间: 2018/8/25.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class SortLevelAdapter extends BaseAdapter {

    private Context mContext;
    private List<LevelListBean.ReturnParamsBean> sortList=new ArrayList<>();
    private LayoutInflater mInflater;

    public SortLevelAdapter(Context context){
        mContext=context;
        mInflater=LayoutInflater.from(mContext);
    }

    public void setList(List<LevelListBean.ReturnParamsBean> list){
        if(sortList!=null){
            sortList.clear();
            sortList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public List<LevelListBean.ReturnParamsBean> getListData(){
        if(sortList!=null){
            return sortList;
        }
        return null;
    }
    @Override
    public int getCount() {
        return sortList==null?0:sortList.size();
    }

    @Override
    public Object getItem(int position) {
        return sortList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View covertView, ViewGroup viewGroup) {
        ItemViewHolder holder;
        if(covertView==null){
            covertView=mInflater.inflate(R.layout.item_sortlist_layout,null);
            holder=new ItemViewHolder(covertView);
            covertView.setTag(holder);
        }else{
            holder= (ItemViewHolder) covertView.getTag();
        }
        LevelListBean.ReturnParamsBean sortFlag=sortList.get(position);
        holder.tvSortName.setText(sortFlag.getLevelName());
        if(sortFlag.isSelected()){
            holder.tvSortName.setTextColor(mContext.getResources().getColor(R.color.colorToolbar));
            holder.ivIcon.setVisibility(View.VISIBLE);
        }else{
            holder.tvSortName.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
            holder.ivIcon.setVisibility(View.INVISIBLE);
        }
        return covertView;
    }


    class ItemViewHolder{
        TextView tvSortName;
        ImageView ivIcon;

        public ItemViewHolder(View rootView){
            tvSortName=rootView.findViewById(R.id.tv_sort_name);
            ivIcon=rootView.findViewById(R.id.iv_icon);
        }

    }
}
