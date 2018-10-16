package com.yueworld.baseproject.ui.allCourse.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.liaoinstan.springview.meituanheader.MeituanFooter;
import com.liaoinstan.springview.meituanheader.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.yueworld.baseproject.R;
import com.yueworld.baseproject.base.BaseActivity;
import com.yueworld.baseproject.ui.allCourse.IAllCategoryView;
import com.yueworld.baseproject.ui.allCourse.adapter.AllCategoryAdapter;
import com.yueworld.baseproject.ui.allCourse.bean.CourseBean;
import com.yueworld.baseproject.ui.allCourse.presenter.AllCategoryPresenter;
import com.yueworld.baseproject.ui.respBean.CategoryListResp;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

public class AllCategoryActivity extends BaseActivity<AllCategoryPresenter> implements IAllCategoryView {

    private SpringView mSpringViewAll;
    private GridView mGridViewAll;
    private List<CategoryListResp.ReturnParamsBean> mDataList=new ArrayList<>();
    private AllCategoryAdapter mAdapter;
    private View noDataView;

    @Override
    protected AllCategoryPresenter createPresenter() {
        return new AllCategoryPresenter(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_all_category;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleTxt("ALL Categories");
        initView();
        initEvent();
        showLoadingDialog("");
        initData();
    }


    private void initView(){
        mSpringViewAll=FindViewById(R.id.springView_allcategory);
        mGridViewAll=FindViewById(R.id.gv_allcategory);
//        noDataView=FindViewById(R.id.view_no_data);
        mSpringViewAll.setHeader(new MeituanHeader(this, pullAnimSrcs, refreshAnimSrcs));
//        mSpringViewAll.setFooter(new MeituanFooter(this, loadingAnimSrcs));
        mAdapter=new AllCategoryAdapter(this,mDataList);
        mGridViewAll.setAdapter(mAdapter);
    }


    private void initData(){
        mPresenter.getCategoryList();
    }

    private void initEvent(){
        mGridViewAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent=new Intent(AllCategoryActivity.this,CategoryCourseActivity.class);
                intent.putExtra(Constant.COURSE_CATEGORY_TITLE,mDataList.get(position).getCatgName());
                intent.putExtra(Constant.COURSE_CATEGORY_ID,mDataList.get(position).getCatgId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void getCategoryListSuccess(CategoryListResp resp) {
        dissMissDialog();
        mSpringViewAll.onFinishFreshAndLoad();
        if(resp.getReturnParams()!=null){
            mDataList.clear();
            mDataList.addAll(resp.getReturnParams());
//            if(mDataList.isEmpty()){
//                noDataView.setVisibility(View.VISIBLE);
//            }else{
//                noDataView.setVisibility(View.GONE);
//            }
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void getCategoryListFail() {
        dissMissDialog();
        mSpringViewAll.onFinishFreshAndLoad();
    }
}
