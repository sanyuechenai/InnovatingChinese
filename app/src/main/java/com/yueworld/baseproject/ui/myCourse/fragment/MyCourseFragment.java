package com.yueworld.baseproject.ui.myCourse.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.yueworld.baseproject.R;
import com.yueworld.baseproject.base.BaseFragment;
import com.yueworld.baseproject.base.BasePresenter;
import com.yueworld.baseproject.ui.allCourse.fragment.CourseCategoryFragment;
import com.yueworld.baseproject.ui.home.adapter.HomePageAdapter;
import com.yueworld.baseproject.ui.myCourse.adapter.CourseTabAdapter;
import com.yueworld.baseproject.ui.setting.fragment.PersonSettingFragment;
import com.yueworld.baseproject.utils.CommonUtils;
import com.yueworld.baseproject.utils.Constant;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import net.lucode.hackware.magicindicator.ext.titles.ColorFlipPagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @create 2018/8/10
 * @author Vincent Chen
 * @Describe 我的课程界面
 */
public class MyCourseFragment extends BaseFragment {

    private RelativeLayout rlTab;
    private ViewPager mViewPager;
    private CourseTabAdapter mAdapter;
    private List<Fragment> myCourseFragments=new ArrayList<>();
    private List<String> titleTabs=new ArrayList<>();


    public MyCourseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_my_course;
    }


    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        initFragmentAndTab();
        rlTab=rootView.findViewById(R.id.rl_tab);
        rlTab.setBackgroundColor(Color.parseColor(Constant.APP_SKIN_CODE));
        mViewPager= (ViewPager) rootView.findViewById(R.id.viewpager_mycourse);
        mAdapter=new CourseTabAdapter(getActivity().getSupportFragmentManager(),myCourseFragments,titleTabs);
        mViewPager.setAdapter(mAdapter);
        initIndicator(rootView);
    }

    @Override
    protected void loadData() {

    }

    private void initFragmentAndTab(){
        titleTabs.add(getResources().getString(R.string.mycourse_title1));
        titleTabs.add(getResources().getString(R.string.mycourse_title2));
        myCourseFragments.add(new CurrentCourseFragment());
        myCourseFragments.add(new FinishedCourseFragment());
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void initIndicator(View rootView){

        final MagicIndicator magicIndicator = (MagicIndicator) rootView.findViewById(R.id.indicator_mycourse);
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return titleTabs.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(titleTabs.get(index));
                simplePagerTitleView.setTextSize(14);
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.colorBlack));
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.colorWhite));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 4));
                indicator.setLineWidth(UIUtil.dip2px(context, 15));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(getResources().getColor(R.color.colorWhite));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerDrawable(new ColorDrawable() {
            @Override
            public int getIntrinsicWidth() {
                return CommonUtils.px(getActivity(),80);
            }
        });

        final FragmentContainerHelper fragmentContainerHelper = new FragmentContainerHelper(magicIndicator);
        fragmentContainerHelper.setInterpolator(new OvershootInterpolator(2.0f));
        fragmentContainerHelper.setDuration(300);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                fragmentContainerHelper.handlePageSelected(position);
            }
        });

    }

    @Override
    public void changeSkin() {
        super.changeSkin();
        rlTab.setBackgroundColor(Color.parseColor(Constant.APP_SKIN_CODE));
    }
}
