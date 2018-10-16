package com.yueworld.baseproject.ui.allCourse.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.liaoinstan.springview.meituanheader.MeituanFooter;
import com.liaoinstan.springview.meituanheader.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.yueworld.baseproject.R;
import com.yueworld.baseproject.base.BaseActivity;
import com.yueworld.baseproject.ui.allCourse.ICategoryCourseView;
import com.yueworld.baseproject.ui.allCourse.adapter.CourseAdapter;
import com.yueworld.baseproject.ui.allCourse.adapter.SortListAdapter;
import com.yueworld.baseproject.ui.allCourse.bean.SortItemBean;
import com.yueworld.baseproject.ui.allCourse.presenter.CategoryCoursePresenter;
import com.yueworld.baseproject.ui.myCourse.activity.CourseActivity;
import com.yueworld.baseproject.ui.respBean.CourseListResp;
import com.yueworld.baseproject.ui.respBean.LevelListBean;
import com.yueworld.baseproject.utils.CommonUtils;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.UIUtils;
import com.yueworld.baseproject.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;


public class CategoryCourseActivity extends BaseActivity<CategoryCoursePresenter> implements View.OnClickListener, ICategoryCourseView {

    private SpringView mSpringView;
    private View noDataView;
    private RecyclerView rvCourseList;
    private LinearLayout llLeftSort,llRightSort;
    private ImageView ivLeftIcon,ivRightIcon;
    private LinearLayout llSortContent;
    private TextView tvGrayBottom,tvLeftSortFlag,tvRightSortFlag;
    private ListView lvSortList;
    private CourseAdapter mCourseAdapter;
    private List<CourseListResp.ReturnParamsBean> courseList=new ArrayList<>();
    private SortListAdapter mSortAdapter;
    private List<SortItemBean>  leftSortList=new ArrayList<>();
    private List<SortItemBean>  rightSortList=new ArrayList<>();
    private int clickFlag=0;//0:未被点击；1;左边点击；2;右边点击
    private int currentPage=1;
    private int totalPage=0;
    private boolean isRefresh=false;
    private String categoryId="";
    private String categoryTitle="";
    private String levelId="";
    private String orderBy="";

    @Override
    protected CategoryCoursePresenter createPresenter() {
        return new CategoryCoursePresenter(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_category_course;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryTitle=getIntent().getStringExtra(Constant.COURSE_CATEGORY_TITLE);
        categoryId=getIntent().getStringExtra(Constant.COURSE_CATEGORY_ID);
        setTitleTxt(categoryTitle);
        initList();
        initView();
        initEvent();
    }


    private void initView(){
        llLeftSort=FindViewById(R.id.ll_shuaixuan1);
        llRightSort=FindViewById(R.id.ll_shuaixuan2);
        tvLeftSortFlag=FindViewById(R.id.tv_sort_flag1);
        tvRightSortFlag=FindViewById(R.id.tv_sort_flag2);
        ivLeftIcon=FindViewById(R.id.iv_left_icon);
        ivRightIcon=FindViewById(R.id.iv_right_icon);
        mSpringView=FindViewById(R.id.springView);
        rvCourseList=FindViewById(R.id.rv_course);
        llSortContent=FindViewById(R.id.ll_sort_content);
        lvSortList=FindViewById(R.id.lv_sort_list);
        tvGrayBottom=FindViewById(R.id.tv_gray_bottom);
        mSpringView.setHeader(new MeituanHeader(this, pullAnimSrcs, refreshAnimSrcs));
        mSpringView.setFooter(new MeituanFooter(this, loadingAnimSrcs));
        noDataView=FindViewById(R.id.view_no_data);
        rvCourseList.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.VERTICAL, CommonUtils.px(this,10), getResources().getColor(R.color.colorGrayDD)));
        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        rvCourseList.setLayoutManager(manager);
        mCourseAdapter=new CourseAdapter(courseList);
        mCourseAdapter.setEnableLoadMore(false);
        rvCourseList.setAdapter(mCourseAdapter);
        mSortAdapter=new SortListAdapter(this);
        lvSortList.setAdapter(mSortAdapter);
    }

    private void initList(){
        showLoadingDialog("");
        getCourseList();
        mPresenter.getLevelList();
        rightSortList.add(new SortItemBean("Passion","Passion",false));
        rightSortList.add(new SortItemBean("ShelyesT","ShelyesT",false));
        rightSortList.add(new SortItemBean("Isfree","Isfree",false));
    }


    private void getCourseList(){
        mPresenter.getCourseList(currentPage,categoryId,levelId,orderBy);
    }

    private void initEvent(){
        llLeftSort.setOnClickListener(this);
        llRightSort.setOnClickListener(this);
        lvSortList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(mSortAdapter.getListData()!=null){
                    setAllListUnselected(mSortAdapter.getListData());
                    mSortAdapter.getListData().get(position).setSelected(true);
                    if(clickFlag==1&&llSortContent.getVisibility()==View.VISIBLE){
                        tvLeftSortFlag.setText(mSortAdapter.getListData().get(position).getSortName());
                        tvLeftSortFlag.setTextColor(Color.parseColor(Constant.APP_SKIN_CODE));
                        levelId=mSortAdapter.getListData().get(position).getSortId();
                        ivLeftIcon.setImageResource(R.mipmap.icon_todown);
                    }else if(clickFlag==2&&llSortContent.getVisibility()==View.VISIBLE){
                        tvRightSortFlag.setText(mSortAdapter.getListData().get(position).getSortName());
                        tvRightSortFlag.setTextColor(Color.parseColor(Constant.APP_SKIN_CODE));
                        orderBy=mSortAdapter.getListData().get(position).getSortId();
                        ivRightIcon.setImageResource(R.mipmap.icon_todown);
                    }
                    mSortAdapter.notifyDataSetChanged();
                    CommonUtils.animateCollapsing(llSortContent);
                    showLoadingDialog("");
                    isRefresh=true;
                    currentPage=1;
                    getCourseList();
                }
            }
        });

        mCourseAdapter.setItemClick(new CourseAdapter.itemClickBack() {
            @Override
            public void onItemClickBack(CourseListResp.ReturnParamsBean item) {
                //添加课程
                showLoadingDialog("");
                mPresenter.addCourseToMy(item.getCourseId());
            }
        });

        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                isRefresh=true;
                currentPage=1;
                getCourseList();
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
                getCourseList();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_shuaixuan1:
                ivRightIcon.setImageResource(R.mipmap.icon_todown);
                if(llSortContent.getVisibility()==View.GONE){
                    clickFlag=1;
                    mSortAdapter.setList(leftSortList);
                    ivLeftIcon.setImageResource(R.mipmap.icon_up);
                    CommonUtils.animateExpanding(llSortContent,CommonUtils.getheight(this),mAnimationHandler);
                }else{//
                    if(clickFlag==1){//上次点击的就是左边
                        ivLeftIcon.setImageResource(R.mipmap.icon_todown);
                        CommonUtils.animateCollapsing(llSortContent);
                    }else{//上次点击的是右边
                        clickFlag=1;
                        ivLeftIcon.setImageResource(R.mipmap.icon_up);
                        mSortAdapter.setList(leftSortList);
                    }
                }

                break;
            case R.id.ll_shuaixuan2:
                ivLeftIcon.setImageResource(R.mipmap.icon_todown);

                if(llSortContent.getVisibility()==View.GONE){
                    clickFlag=2;
                    mSortAdapter.setList(rightSortList);
                    ivRightIcon.setImageResource(R.mipmap.icon_up);
                    CommonUtils.animateExpanding(llSortContent,CommonUtils.getheight(this),mAnimationHandler);
                }else{//
                    if(clickFlag==1){//上次点击的就是左边
                        clickFlag=2;
                        ivRightIcon.setImageResource(R.mipmap.icon_up);
                        mSortAdapter.setList(rightSortList);
                    }else{//上次点击的是右边
                        ivRightIcon.setImageResource(R.mipmap.icon_todown);
                        CommonUtils.animateCollapsing(llSortContent);
                    }
                }
                break;
        }
    }


    private Handler mAnimationHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what== Constant.ANIMATION_END_FLAG){
                tvGrayBottom.setBackgroundColor(getResources().getColor(R.color.colorGrayTransparent));
            }
        }
    };

    private void setAllListUnselected(List<SortItemBean> list){
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setSelected(false);
        }
    }

    @Override
    public void onBackPressed() {
        if(llSortContent.getVisibility()==View.VISIBLE){
            CommonUtils.animateCollapsing(llSortContent);
            return ;
        }
        super.onBackPressed();
    }

    @Override
    public void getLevelListSuccess(LevelListBean resp) {
        dissMissDialog();
        if(resp.getReturnParams()!=null){
            List<LevelListBean.ReturnParamsBean> list=resp.getReturnParams();
            for (int i = 0; i <list.size() ; i++) {
                leftSortList.add(new SortItemBean(list.get(i).getLevelId(),list.get(i).getLevelName(),false));
            }
        }
    }

    @Override
    public void getCourseListSuccess(CourseListResp resp) {
        dissMissDialog();
        mSpringView.onFinishFreshAndLoad();
        totalPage=resp.getTotalPage();
        if(isRefresh){
            courseList.clear();
        }
        if(resp.getReturnParams()!=null){
            courseList.addAll(resp.getReturnParams());
        }

        if(courseList.isEmpty()){
            noDataView.setVisibility(View.VISIBLE);
        }else{
            noDataView.setVisibility(View.GONE);
        }
        mCourseAdapter.notifyDataSetChanged();
    }

    @Override
    public void addToMyCourseSuccess() {
        dissMissDialog();
        UIUtils.showToast(getString(R.string.txt_addcourse_success));
    }

    @Override
    public void addToMyCourseFail() {
        dissMissDialog();

    }

    @Override
    public void getListFail() {
        dissMissDialog();
        mSpringView.onFinishFreshAndLoad();

    }
}
