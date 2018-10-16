package com.yueworld.baseproject.ui.myCourse.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liaoinstan.springview.meituanheader.MeituanFooter;
import com.liaoinstan.springview.meituanheader.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.yueworld.baseproject.R;
import com.yueworld.baseproject.base.BaseActivity;
import com.yueworld.baseproject.ui.myCourse.IUnitView;
import com.yueworld.baseproject.ui.myCourse.adapter.UnitListAdapter;
import com.yueworld.baseproject.ui.myCourse.bean.UnitItemBean;
import com.yueworld.baseproject.ui.myCourse.presenter.UnitPresenter;
import com.yueworld.baseproject.ui.respBean.UnitListResp;
import com.yueworld.baseproject.ui.respBean.UnitSentenceListResp;
import com.yueworld.baseproject.utils.CommonUtils;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.view.CircleImage;
import com.yueworld.baseproject.view.RecycleViewDivider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UnitActivity extends BaseActivity<UnitPresenter> implements TextToSpeech.OnInitListener, IUnitView {


    private SpringView mSpringView;
    private RecyclerView mRecyclerView;
    private RelativeLayout rlHead;
    private CircleImage ivUnitCovery;
    private TextView tvUnitNameEn,tvUnitNameCn;
    private CardView cardView;
    private UnitListAdapter mAdapter;
    private List<UnitSentenceListResp.ReturnParamsBean> mDataList=new ArrayList<>();
    private TextToSpeech tts;
    private TextView footView;
    private String courseId="";
    private UnitListResp.ReturnParamsBean unitBean;
    private String title="";

    @Override
    protected UnitPresenter createPresenter() {
        return new UnitPresenter(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_unit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courseId=getIntent().getStringExtra(Constant.COURSE_ID);
        unitBean= (UnitListResp.ReturnParamsBean) getIntent().getSerializableExtra(Constant.UNIT_TAG);
        title=getIntent().getStringExtra(Constant.TITLE_FLAG);
        setTitleLineVisible(R.color.colorBlack);
        setTitleTxt(title);
        initData();
        initView();
        initEvent();
    }

    private void initView(){
        tts=new TextToSpeech(this,this);
        mSpringView=FindViewById(R.id.springview_unit);
        mRecyclerView=FindViewById(R.id.rv_unit_list);
        rlHead=FindViewById(R.id.rl_head);
        rlHead.setBackgroundColor(Color.parseColor(Constant.APP_SKIN_CODE));
        ivUnitCovery=FindViewById(R.id.iv_unit_covery);
        tvUnitNameEn=FindViewById(R.id.tv_unit_name_en);
        tvUnitNameCn=FindViewById(R.id.tv_unit_name_cn);
        tvUnitNameEn.setText(unitBean.getUnitName());
        tvUnitNameCn.setText(unitBean.getUnitNameCn());
        footView=FindViewById(R.id.tv_add_more);
        cardView=FindViewById(R.id.card_view_addmore);
        cardView.setCardBackgroundColor(Color.parseColor(Constant.APP_SKIN_CODE));
        footView.setText(getString(R.string.txt_challenge));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.VERTICAL, CommonUtils.px(this,10), getResources().getColor(R.color.colorGrayDD)));
        mSpringView.setHeader(new MeituanHeader(this, pullAnimSrcs, refreshAnimSrcs));
        mSpringView.setFooter(new MeituanFooter(this, loadingAnimSrcs));
        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false){
            @Override
            public boolean canScrollVertically() {
                return false;//解决scrollView 嵌套RecyclerView滑动冲突
            }

        };
        mRecyclerView.setLayoutManager(manager);
        mAdapter=new UnitListAdapter(mDataList);
        mAdapter.setEnableLoadMore(false);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData(){
        mPresenter.getUnitSentenceList(courseId,unitBean.getUnitId());
    }

    private void initEvent(){
        mAdapter.setItemClick(new UnitListAdapter.ItemClickBack() {
            @Override
            public void onItemLongClickBack(UnitSentenceListResp.ReturnParamsBean item) {
                tts.setPitch(1.0f);
                // 设置语速
                tts.setSpeechRate(1.0f);
                //播放语音
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    tts.speak(item.getSenName(), TextToSpeech.QUEUE_ADD, null,null);
                }
            }

            @Override
            public void onClickBack(UnitSentenceListResp.ReturnParamsBean item) {

                Intent intent=new Intent(UnitActivity.this,UnitStudyActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("unit", (Serializable) mDataList);
                bundle.putSerializable("position", getCurrentPosition(item));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        footView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UnitActivity.this,UnitChallengeActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("unit", (Serializable) mDataList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


    private int getCurrentPosition(UnitSentenceListResp.ReturnParamsBean item){
        for (int i = 0; i < mDataList.size(); i++) {
            if(item.equals(mDataList.get(i))){
                return i;
            }
        }
        return 0;
    }

    @Override
    public void onInit(int i) {

    }

    @Override
    public void getUnitSentenceListSuccess(UnitSentenceListResp resp) {
        dissMissDialog();
        if(resp.getReturnParams()!=null){
            mDataList.clear();
            mDataList.addAll(resp.getReturnParams());
            if(mDataList.isEmpty()){
                footView.setVisibility(View.GONE);
            }else{
                footView.setVisibility(View.VISIBLE);
            }
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void getDataFail() {
        dissMissDialog();

    }
}
