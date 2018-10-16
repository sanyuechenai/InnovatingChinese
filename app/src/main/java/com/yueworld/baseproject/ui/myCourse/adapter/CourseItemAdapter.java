package com.yueworld.baseproject.ui.myCourse.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yueworld.baseproject.MyApplication;
import com.yueworld.baseproject.R;
import com.yueworld.baseproject.ui.respBean.UnitListResp;
import com.yueworld.baseproject.utils.CommonUtils;
import com.yueworld.baseproject.utils.StringUtils;
import com.yueworld.baseproject.view.CircleImage;
import com.yueworld.baseproject.view.customRecyclerview.CardAdapterHelper;

import java.util.List;

/**
 * 创建时间: 2018/8/23.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class CourseItemAdapter extends RecyclerView.Adapter<CourseItemAdapter.ViewHolder> {

    private List<UnitListResp.ReturnParamsBean> mList;
    private CardAdapterHelper mCardAdapterHelper;
    private ItemClick itemClick;

    public CourseItemAdapter(List<UnitListResp.ReturnParamsBean> list){
        mList=list;
        mCardAdapterHelper=new CardAdapterHelper();
    }

    public void setItemClick(ItemClick itemClick){
        this.itemClick=itemClick;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_layout, parent, false);
        mCardAdapterHelper.onCreateViewHolder(parent, itemView);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        mCardAdapterHelper.onBindViewHolder(holder.itemView, position, getItemCount());
        UnitListResp.ReturnParamsBean bean=mList.get(position);
        holder.tvUnitScore.setText(bean.getUnitScore()+"");
        holder.tvUnitName.setText(bean.getUnitName());
        holder.tvUnitNameCn.setText(bean.getUnitNameCn());
        if(!StringUtils.isEmpty(bean.getUnitImgSrc())){
            Picasso.with(MyApplication.getContext()).load(bean.getUnitImgSrc())
                    .placeholder(R.mipmap.my_pic)
                    .error(R.mipmap.my_pic)
                    .into(holder.ivUnitCovery);
        }else{
            holder.ivUnitCovery.setImageResource(R.mipmap.my_pic);
        }
        if(!StringUtils.isEmpty(bean.getUnitIsChallenge())&&bean.getUnitIsChallenge().equals("Y")){
            holder.rlScore.setVisibility(View.VISIBLE);
            holder.rlLock.setVisibility(View.GONE);
        }else{
            holder.rlScore.setVisibility(View.GONE);
            holder.rlLock.setVisibility(View.VISIBLE);
        }
        holder.rlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(itemClick!=null){
                        itemClick.itemClickBack(position);
                    }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout rlItem,rlLock,rlScore;
        TextView tvUnitScore,tvUnitName,tvUnitNameCn;
        CircleImage ivUnitCovery;

        public ViewHolder(View itemView) {
            super(itemView);
            rlItem=itemView.findViewById(R.id.rl_item);
            rlScore=itemView.findViewById(R.id.rl_score);
            rlLock=itemView.findViewById(R.id.relativeLayout);
            tvUnitScore=itemView.findViewById(R.id.tv_unit_score);
            ivUnitCovery=itemView.findViewById(R.id.iv_course_covery);
            tvUnitName=itemView.findViewById(R.id.tv_unit_name);
            tvUnitNameCn=itemView.findViewById(R.id.tv_unit_name_cn);
        }
    }

    public interface  ItemClick{
        void itemClickBack(int position);
    }
}
