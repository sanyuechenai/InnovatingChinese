package com.yueworld.baseproject.ui.myCourse.adapter;

import android.view.View;

import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;
import com.yueworld.baseproject.R;
import com.yueworld.baseproject.ui.myCourse.bean.UnitItemBean;
import com.yueworld.baseproject.ui.respBean.UnitSentenceListResp;

import java.util.List;

/**
 * 创建时间: 2018/8/22.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class UnitListAdapter extends BaseQuickAdapter<UnitSentenceListResp.ReturnParamsBean,BaseViewHolder> {

    private ItemClickBack clickBack;
    public UnitListAdapter(List<UnitSentenceListResp.ReturnParamsBean> data) {
        super(R.layout.item_unit_layout, data);
    }


    public void setItemClick(ItemClickBack clickBack){
        this.clickBack=clickBack;
    }
    @Override
    protected void convert(BaseViewHolder helper, final UnitSentenceListResp.ReturnParamsBean item) {
        helper.setText(R.id.tv_name_cn,item.getSenName());
        helper.setText(R.id.tv_name_en,item.getTranName());
        helper.setOnLongClickListener(R.id.card_view, new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(clickBack!=null){
                    clickBack.onItemLongClickBack(item);
                }
                return true;
            }
        });
        helper.setOnClickListener(R.id.card_view, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickBack!=null){
                    clickBack.onClickBack(item);
                }
            }
        });
    }

    public interface  ItemClickBack{
        void onItemLongClickBack(UnitSentenceListResp.ReturnParamsBean item);
        void onClickBack(UnitSentenceListResp.ReturnParamsBean item);
    }
}
