package com.yueworld.baseproject.ui.myCourse.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yueworld.baseproject.R;
import com.yueworld.baseproject.base.BaseActivity;
import com.yueworld.baseproject.ui.myCourse.ICourseView;
import com.yueworld.baseproject.ui.myCourse.adapter.CourseItemAdapter;
import com.yueworld.baseproject.ui.myCourse.presenter.CoursePresenter;
import com.yueworld.baseproject.ui.respBean.CourseListResp;
import com.yueworld.baseproject.ui.respBean.MyCourseListResp;
import com.yueworld.baseproject.ui.respBean.UnitListResp;
import com.yueworld.baseproject.utils.CommonUtils;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.StringUtils;
import com.yueworld.baseproject.utils.UIUtils;
import com.yueworld.baseproject.view.RecycleViewDivider;
import com.yueworld.baseproject.view.customRecyclerview.CardScaleHelper;
import com.yueworld.baseproject.view.customRecyclerview.SpeedRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends BaseActivity<CoursePresenter> implements ICourseView {

    private LinearLayout llHead;
    private TextView courseName,courseNameCn;
    private ProgressBar progressBar;
    private TextView tvRate;
    private View noDataView;
    private SpeedRecyclerView mRecyclerView;
    private CourseItemAdapter mAdapter;
    private List<UnitListResp.ReturnParamsBean> mDataList=new ArrayList<>();
    private CardScaleHelper mCardScaleHelper = null;
    private MyCourseListResp.ReturnParamsBean courseBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courseBean= (MyCourseListResp.ReturnParamsBean) getIntent().getSerializableExtra(Constant.COURSE_TAG);
        initView();
        initEvent();
        initList();
    }

    @Override
    protected CoursePresenter createPresenter() {
        return new CoursePresenter(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_course;
    }


    private void initView(){
        int progress=courseBean.getUnitFinishedCount()/courseBean.getCourUnitCount();
        llHead=FindViewById(R.id.ll_head);
        llHead.setBackgroundColor(Color.parseColor(Constant.APP_SKIN_CODE));
        courseName=FindViewById(R.id.tv_course_name);
        courseNameCn=FindViewById(R.id.tv_course_name_chinese);
        courseName.setText(courseBean.getCourseName());
        courseNameCn.setText(courseBean.getCourseNameCN());
        progressBar=FindViewById(R.id.progressBar);
        progressBar.setProgress(progress);
        tvRate=FindViewById(R.id.tv_rate);
        tvRate.setText(courseBean.getUnitFinishedCount()+"/"+courseBean.getCourUnitCount());
        mRecyclerView=FindViewById(R.id.srv_course);
        noDataView=FindViewById(R.id.view_no_data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
//        mRecyclerView.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.HORIZONTAL, CommonUtils.px(this,10)),getResources().getColor(R.color.colorActivityBg));
        mAdapter=new CourseItemAdapter(mDataList);
        mRecyclerView.setAdapter(mAdapter);
        mCardScaleHelper = new CardScaleHelper();
        mCardScaleHelper.setCurrentItemPos(0);
        mCardScaleHelper.attachToRecyclerView(mRecyclerView);
    }

    private void initList (){
        showLoadingDialog("");
        mPresenter.getUnitByCourse(courseBean.getCourseId());

    }

    private void initEvent(){
        mAdapter.setItemClick(new CourseItemAdapter.ItemClick() {
            @Override
            public void itemClickBack(int position) {
                if(!StringUtils.isEmpty(mDataList.get(position).getUnitIsChallenge())&&mDataList.get(position).getUnitIsChallenge().equals("Y")){
                    Intent intent=new Intent(CourseActivity.this,UnitActivity.class);
                    intent.putExtra(Constant.COURSE_ID,courseBean.getCourseId());
                    intent.putExtra(Constant.UNIT_TAG,mDataList.get(position));
                    intent.putExtra(Constant.TITLE_FLAG,position+1+"/"+mDataList.size());
                    startActivity(intent);
                }else{
                    UIUtils.showToast(getString(R.string.txt_finished_unit));
                }

            }
        });
    }

    @Override
    public void getUnitListSuccess(UnitListResp resp) {
        dissMissDialog();
        if(resp.getReturnParams()!=null){
            mDataList.addAll(resp.getReturnParams());
            mAdapter.notifyDataSetChanged();
            if(mDataList.isEmpty()){
                noDataView.setVisibility(View.VISIBLE);
            }else{
                noDataView.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public void getDataFail() {
        dissMissDialog();

    }
}
