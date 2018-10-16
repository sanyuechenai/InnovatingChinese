package com.yueworld.baseproject.ui.myCourse.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yueworld.baseproject.R;
import com.yueworld.baseproject.ui.myCourse.bean.UnitItemBean;
import com.yueworld.baseproject.ui.respBean.UnitSentenceListResp;
import com.yueworld.baseproject.view.customRecyclerview.CardAdapterHelper;

import java.util.List;

/**
 * 创建时间: 2018/8/23.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class UnitStudyItemAdapter extends RecyclerView.Adapter<UnitStudyItemAdapter.ViewHolder> {

    private List<UnitSentenceListResp.ReturnParamsBean> mList;

    public UnitStudyItemAdapter(List<UnitSentenceListResp.ReturnParamsBean> list){
        mList=list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unit_study_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UnitSentenceListResp.ReturnParamsBean bean=mList.get(position);
        holder.tvUnitCn.setText(bean.getSenName());
        holder.tvUnitEn.setText(bean.getTranName());

    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvUnitCn,tvUnitEn;

        public ViewHolder(View itemView) {
            super(itemView);
            tvUnitCn=itemView.findViewById(R.id.tv_unit_cn);
            tvUnitEn=itemView.findViewById(R.id.tv_unit_en);
        }
    }

}
