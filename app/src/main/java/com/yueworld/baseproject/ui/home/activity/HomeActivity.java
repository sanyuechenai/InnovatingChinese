package com.yueworld.baseproject.ui.home.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.chaychan.library.BottomBarLayout;
import com.yueworld.baseproject.R;
import com.yueworld.baseproject.base.BaseActivity;
import com.yueworld.baseproject.ui.home.adapter.HomePageAdapter;
import com.yueworld.baseproject.ui.allCourse.fragment.CourseCategoryFragment;
import com.yueworld.baseproject.ui.myCourse.fragment.MyCourseFragment;
import com.yueworld.baseproject.ui.setting.fragment.PersonSettingFragment;
import com.yueworld.baseproject.ui.home.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity<HomePresenter>{


    private ViewPager mViewPager;
    private BottomBarLayout mBottomBarLayout;
    private HomePageAdapter mAdapter;
    private List<Fragment> homeFragments=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarVisible(false);
        initFragments();
        initView();
    }

    @Override
    protected HomePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_home;
    }


    private void initView(){
        mViewPager= (ViewPager) findViewById(R.id.home_viewpager);
        mBottomBarLayout= (BottomBarLayout) findViewById(R.id.home_bottom);
        mAdapter=new HomePageAdapter(getSupportFragmentManager(),homeFragments);
        mViewPager.setAdapter(mAdapter);
        mBottomBarLayout.setViewPager(mViewPager);
        mBottomBarLayout.setSmoothScroll(false);
    }


    private void initFragments(){
        homeFragments.add(new MyCourseFragment());
        homeFragments.add(new CourseCategoryFragment());
        homeFragments.add(new PersonSettingFragment());
    }

    @Override
    protected void changeActivity() {
        mViewPager.setCurrentItem(1);//切换到课程分类页面
    }
}
