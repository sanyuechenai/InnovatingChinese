package com.yueworld.baseproject.ui.myCourse.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 创建时间: 2018/8/10.
 * 创建人: Vincent Chen
 * 功能描述: 我的课程的页面切换的adapter
 */

public class CourseTabAdapter extends FragmentPagerAdapter {

    private List<Fragment> courseFragments;
    private List<String> titles;
    public CourseTabAdapter(FragmentManager fm, List<Fragment> fragments,List<String> titles) {
        super(fm);
        courseFragments=fragments;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return courseFragments.get(position);
    }

    @Override
    public int getCount() {
        return courseFragments==null?0:courseFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
