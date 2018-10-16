package com.yueworld.baseproject.ui.myCourse.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liaoinstan.springview.meituanheader.MeituanFooter;
import com.liaoinstan.springview.meituanheader.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.yueworld.baseproject.R;
import com.yueworld.baseproject.base.BaseFragment;
import com.yueworld.baseproject.ui.myCourse.IMyCourseListView;
import com.yueworld.baseproject.ui.myCourse.activity.CourseActivity;
import com.yueworld.baseproject.ui.myCourse.adapter.CurrentCourseAdapter;
import com.yueworld.baseproject.ui.myCourse.presenter.MyCourseListPresenter;
import com.yueworld.baseproject.ui.respBean.MessageEvent;
import com.yueworld.baseproject.ui.respBean.MyCourseListResp;
import com.yueworld.baseproject.utils.CommonUtils;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.UIUtils;
import com.yueworld.baseproject.view.RecycleViewDivider;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @create 2018/8/10
 * @author Vincent Chen
 * @Describe 当前学习的课程fragment
 */
public class CurrentCourseFragment extends BaseFragment<MyCourseListPresenter> implements IMyCourseListView {

    private SpringView mSpringView;
    private RecyclerView mRecyclerView;
    private CurrentCourseAdapter mAdapter;
    private List<MyCourseListResp.ReturnParamsBean> mDataList=new ArrayList<>();
    private View foorView;
    private CardView cardViewAdd;
    private boolean isRefresh=false;
    private int currentPage=1;
    private int totalPage=0;


    public CurrentCourseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    protected MyCourseListPresenter createPresenter() {
        return new MyCourseListPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_current_course;
    }


    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        mSpringView=rootView.findViewById(R.id.sv_current_course);
        mRecyclerView=rootView.findViewById(R.id.rv_current_course);
        foorView=rootView.findViewById(R.id.view_foot);
        cardViewAdd=rootView.findViewById(R.id.card_view_addmore);
        cardViewAdd.setCardBackgroundColor(Color.parseColor(Constant.APP_SKIN_CODE));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.VERTICAL, CommonUtils.px(getActivity(),10), getResources().getColor(R.color.colorGrayDD)));
        mSpringView.setHeader(new MeituanHeader(getActivity(), pullAnimSrcs, refreshAnimSrcs));
        mSpringView.setFooter(new MeituanFooter(getActivity(), loadingAnimSrcs));
//        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false){
            @Override
            public boolean canScrollVertically() {
                return false;//解决scrollView 嵌套RecyclerView滑动冲突
            }

        };
        mRecyclerView.setLayoutManager(manager);
        mAdapter=new CurrentCourseAdapter(mDataList);
        mAdapter.setEnableLoadMore(false);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
        showLoadingDialog("");
        getData();
    }

    private void getData(){
        mPresenter.getMyCourseList("N",currentPage);
    }

    @Override
    public void initListener() {
        super.initListener();
        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                currentPage=1;
                isRefresh=true;
                getData();
            }

            @Override
            public void onLoadmore() {
                currentPage++;
                isRefresh=false;
                if(currentPage>totalPage){
                    UIUtils.showToast(getString(R.string.no_more_data));
                    mSpringView.onFinishFreshAndLoad();
                    return;
                }
                getData();

            }
        });
        mAdapter.setItemClick(new CurrentCourseAdapter.itemClickBack() {
            @Override
            public void onItemClickBack(MyCourseListResp.ReturnParamsBean item) {
                Intent intent=new Intent(getActivity(), CourseActivity.class);
                intent.putExtra(Constant.COURSE_TAG,item);
                startActivity(intent);
            }
        });
        foorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//跳到课程分类界面
                EventBus.getDefault().post(new MessageEvent(1));
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void getMyCourseListSuccess(MyCourseListResp resp) {
        mSpringView.onFinishFreshAndLoad();
        dissMissDialog();
        if(resp!=null){
            totalPage=resp.getTotalPage();
            if(isRefresh){
                mDataList.clear();
            }
            if(resp.getReturnParams()!=null){
                mDataList.addAll(resp.getReturnParams());
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getMyCourseListFail() {
        mSpringView.onFinishFreshAndLoad();
        dissMissDialog();
    }

    @Override
    public void changeSkin() {
        super.changeSkin();
        cardViewAdd.setCardBackgroundColor(Color.parseColor(Constant.APP_SKIN_CODE));
        mAdapter.notifyDataSetChanged();
    }
}
