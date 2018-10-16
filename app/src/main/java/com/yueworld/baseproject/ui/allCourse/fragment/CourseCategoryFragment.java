package com.yueworld.baseproject.ui.allCourse.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.liaoinstan.springview.meituanheader.MeituanFooter;
import com.liaoinstan.springview.meituanheader.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.yueworld.baseproject.R;
import com.yueworld.baseproject.base.BaseFragment;
import com.yueworld.baseproject.base.BasePresenter;
import com.yueworld.baseproject.ui.allCourse.ICategoryListView;
import com.yueworld.baseproject.ui.allCourse.activity.AllCategoryActivity;
import com.yueworld.baseproject.ui.allCourse.activity.CategoryCourseActivity;
import com.yueworld.baseproject.ui.allCourse.adapter.CourseCategoryAdapter;
import com.yueworld.baseproject.ui.allCourse.adapter.RecommendCourseAdapter;
import com.yueworld.baseproject.ui.allCourse.bean.CourseBean;
import com.yueworld.baseproject.ui.allCourse.presenter.CategoryRecomendPresenter;
import com.yueworld.baseproject.ui.respBean.CategoryListResp;
import com.yueworld.baseproject.ui.respBean.RecommendListResp;
import com.yueworld.baseproject.utils.CommonUtils;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.StringUtils;
import com.yueworld.baseproject.utils.UIUtils;
import com.yueworld.baseproject.view.MyGridView;
import com.yueworld.baseproject.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * @create 2018/8/10
 * @author Vincent Chen
 * @Describe 课程频道fragment
 */
public class CourseCategoryFragment extends BaseFragment<CategoryRecomendPresenter> implements ICategoryListView {


    private LinearLayout llHead;
    private SpringView springView;
    private MyGridView mGridView;
    private RecyclerView mRecyclerView;
    private CourseCategoryAdapter mGridAdapter;
    private List<CategoryListResp.ReturnParamsBean> courseCategorylist=new ArrayList<>();
    private RecommendCourseAdapter mRecommendAdapter;
    private List<RecommendListResp.ReturnParamsBean> mRecommendList=new ArrayList<>();
    private int currentPage=1;
    private int totalPage=0;
    private boolean isRefresh=false;


    public CourseCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    protected CategoryRecomendPresenter createPresenter() {
        return new CategoryRecomendPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_course_category;
    }

    @Override
    protected void loadData() {
        showLoadingDialog("");
        mPresenter.getCategoryList();
//        mPresenter.getRecommendList(currentPage);

    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        llHead=rootView.findViewById(R.id.ll_head);
        llHead.setBackgroundColor(Color.parseColor(Constant.APP_SKIN_CODE));
        springView=rootView.findViewById(R.id.springView);
        springView.setHeader(new MeituanHeader(getActivity(), pullAnimSrcs, refreshAnimSrcs));
        springView.setFooter(new MeituanFooter(getActivity(), loadingAnimSrcs));
        mGridView=rootView.findViewById(R.id.gv_category);
        mRecyclerView=rootView.findViewById(R.id.rv_recomend_course);
        mGridAdapter=new CourseCategoryAdapter(getActivity(),courseCategorylist);
        mGridView.setAdapter(mGridAdapter);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false){
            @Override
            public boolean canScrollVertically() {
                return false;//解决scrollView 嵌套RecyclerView滑动冲突
            }

        };
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.VERTICAL, CommonUtils.px(getActivity(),10), getResources().getColor(R.color.colorGrayDD)));
        mRecommendAdapter=new RecommendCourseAdapter(mRecommendList);
        mRecommendAdapter.setEnableLoadMore(false);
        mRecyclerView.setAdapter(mRecommendAdapter);
    }

    @Override
    public void initListener() {
        super.initListener();
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                CategoryListResp.ReturnParamsBean bean=courseCategorylist.get(position);
                if(StringUtils.isEmpty(bean.getCatgId())){
                    Intent intent=new Intent(getActivity(), AllCategoryActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent=new Intent(getActivity(), CategoryCourseActivity.class);
                    intent.putExtra(Constant.COURSE_CATEGORY_TITLE,bean.getCatgName());
                    intent.putExtra(Constant.COURSE_CATEGORY_ID,bean.getCatgId());
                    startActivity(intent);
                }
            }
        });

        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                isRefresh=true;
                currentPage=1;
                mPresenter.getRecommendList(currentPage);
            }

            @Override
            public void onLoadmore() {
                isRefresh=false;
                currentPage++;
                if(currentPage>totalPage){
                    UIUtils.showToast(getString(R.string.no_more_data));
                    springView.onFinishFreshAndLoad();
                    return;
                }
                mPresenter.getRecommendList(currentPage);
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void getCategoryListSuccess(CategoryListResp resp) {
        dissMissDialog();
        if(resp.getReturnParams()!=null){
            courseCategorylist.clear();
            courseCategorylist.addAll(resp.getReturnParams());
            CategoryListResp.ReturnParamsBean childBean=new CategoryListResp.ReturnParamsBean();
            childBean.setCatgName("All");
            if(courseCategorylist.size()<8){
                courseCategorylist.add(childBean);
            }else{
                courseCategorylist.add(7,childBean);
            }
            mGridAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void getRecommendListSuccess(RecommendListResp resp) {
        dissMissDialog();
        springView.onFinishFreshAndLoad();
        totalPage=resp.getTotalPage();
        if(resp.getReturnParams()!=null){
            if(isRefresh){
                mRecommendList.clear();
            }
            mRecommendList.addAll(resp.getReturnParams());
            mRecommendAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getDataFail() {
        dissMissDialog();
        springView.onFinishFreshAndLoad();

    }

    @Override
    public void changeSkin() {
        super.changeSkin();
        llHead.setBackgroundColor(Color.parseColor(Constant.APP_SKIN_CODE));
    }
}
