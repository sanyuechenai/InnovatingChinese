package com.yueworld.baseproject.ui.setting.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.liaoinstan.springview.meituanheader.MeituanFooter;
import com.liaoinstan.springview.meituanheader.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.yueworld.baseproject.R;
import com.yueworld.baseproject.base.BaseActivity;
import com.yueworld.baseproject.ui.respBean.MessageListResp;
import com.yueworld.baseproject.ui.setting.IMessageView;
import com.yueworld.baseproject.ui.setting.adapter.MessageAfapter;
import com.yueworld.baseproject.ui.setting.presenter.MessagePresenter;
import com.yueworld.baseproject.utils.CommonUtils;
import com.yueworld.baseproject.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends BaseActivity<MessagePresenter> implements IMessageView {

    private SpringView mSpringView;
    private ListView lvMessage;
    private View noDataView;
    private MessageAfapter mAdapter;
    private List<MessageListResp.ReturnParamsBean> dataList=new ArrayList<>();
    private int currentPage=1;
    private int totalPage=0;
    private boolean isRefresh=false;

    @Override
    protected MessagePresenter createPresenter() {
        return new MessagePresenter(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleTxt(getString(R.string.txt_message));
        initView();
        initEvent();
        showLoadingDialog("");
        getData();
    }


    private void initView(){
        mSpringView=FindViewById(R.id.springView);
        lvMessage=FindViewById(R.id.lv_message);
        noDataView=FindViewById(R.id.view_no_data);
        mSpringView.setHeader(new MeituanHeader(this, pullAnimSrcs, refreshAnimSrcs));
        mSpringView.setFooter(new MeituanFooter(this, loadingAnimSrcs));
        mAdapter=new MessageAfapter(this,dataList);
        lvMessage.setAdapter(mAdapter);
    }

    private void initEvent(){
        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                isRefresh=true;
                currentPage=1;
                getData();
            }

            @Override
            public void onLoadmore() {
                isRefresh=false;
                currentPage++;
                if(currentPage>totalPage){
                    UIUtils.showToast(getString(R.string.no_more_data));
                    mSpringView.onFinishFreshAndLoad();
                    return;
                }
                getData();

            }
        });
    }


    private void getData(){
        mPresenter.getMessageList(currentPage);
    }

    @Override
    public void getMessageListSuccess(MessageListResp resp) {
        dissMissDialog();
        mSpringView.onFinishFreshAndLoad();
        totalPage=resp.getTotalPage();
        if(isRefresh){
            dataList.clear();
        }
        if(resp.getReturnParams()!=null){
            dataList.addAll(resp.getReturnParams());
        }

        if(dataList.isEmpty()){
            noDataView.setVisibility(View.VISIBLE);
        }else{
            noDataView.setVisibility(View.GONE);
        }
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void getMessageListFail() {
        dissMissDialog();
        mSpringView.onFinishFreshAndLoad();

    }
}
