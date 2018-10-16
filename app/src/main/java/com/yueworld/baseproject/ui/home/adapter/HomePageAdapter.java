package com.yueworld.baseproject.ui.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间: 2018/8/10.
 * 创建人: Vincent Chen
 * 功能描述: 首页fragment 的adapter
 */

public class HomePageAdapter extends FragmentPagerAdapter {

    private List<Fragment> homeFragments;
    public HomePageAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        homeFragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return homeFragments.get(position);
    }

    @Override
    public int getCount() {
        return homeFragments==null?0:homeFragments.size();
    }
}
