package com.yueworld.baseproject.ui.setting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yueworld.baseproject.R;
import com.yueworld.baseproject.ui.respBean.MessageListResp;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间: 2018/9/25.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class MessageAfapter extends BaseAdapter {

    private Context mContext;
    private List<MessageListResp.ReturnParamsBean> datalist;
    private LayoutInflater mInflater;

    public MessageAfapter(Context context,List<MessageListResp.ReturnParamsBean> datalist){
        mContext=context;
        this.datalist=datalist;
        mInflater=LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return datalist==null?0:datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View covertView, ViewGroup viewGroup) {
        MessageViewHolder holder=null;
        if(covertView==null){
            covertView=mInflater.inflate(R.layout.item_message_layout,null);
            holder=new MessageViewHolder(covertView);
            covertView.setTag(holder);
        }else{
            holder= (MessageViewHolder) covertView.getTag();
        }
        MessageListResp.ReturnParamsBean bean=datalist.get(position);
        holder.tvMsgTitle.setText(bean.getNoteTitle());
        holder.tvMsgContent.setText(bean.getNoteBody());
        holder.tvMsgTime.setText(bean.getCreatetT());
        return covertView;
    }


    public static class MessageViewHolder{
        TextView tvMsgTitle,tvMsgTime,tvMsgContent;

        public MessageViewHolder(View rootView){
            tvMsgTitle=rootView.findViewById(R.id.tv_msg_title);
            tvMsgTime=rootView.findViewById(R.id.tv_msg_time);
            tvMsgContent=rootView.findViewById(R.id.tv_msg_content);
        }
    }
}
