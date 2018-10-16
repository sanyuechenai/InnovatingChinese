package com.yueworld.baseproject.ui.setting.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yueworld.baseproject.R;
import com.yueworld.baseproject.base.BaseFragment;
import com.yueworld.baseproject.base.BasePresenter;
import com.yueworld.baseproject.ui.home.activity.HomeActivity;
import com.yueworld.baseproject.ui.respBean.MessageEvent;
import com.yueworld.baseproject.ui.setting.activity.MessageActivity;
import com.yueworld.baseproject.ui.setting.activity.SettingActivity;
import com.yueworld.baseproject.ui.setting.activity.UpdateUserInfoActivity;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.LocalManageUtil;
import com.yueworld.baseproject.utils.SharePref;
import com.yueworld.baseproject.utils.StringUtils;
import com.yueworld.baseproject.view.CircleImage;
import com.yueworld.baseproject.view.ConditionalSelectorView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @create 2018/8/10
 * @author Vincent Chen
 * @Describe 个人设置fragment
 */
public class PersonSettingFragment extends BaseFragment {

    private RelativeLayout rlTitleBg;
    private TextView tvBg;
    private CircleImage ivUserIcon;
    private ImageView ivLeft,ivRight;
    private TextView tvTitle;
    private LinearLayout llUser;
    private TextView tvNickname,tvEmail;
    private LinearLayout llBuyVIP,llChangeLanguage,llChangeSkin;
    private ConditionalSelectorView mLanguageChangeView;
    private ConditionalSelectorView mSkinChangeView;
    private List<String> languages=new ArrayList<>();
    private List<String> skins=new ArrayList<>();



    public PersonSettingFragment() {
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
        return R.layout.fragment_person_setting;
    }


    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        languages.add(getResources().getString(R.string.language_cn));
        languages.add(getResources().getString(R.string.language_en));
        skins.add(getResources().getString(R.string.txt_bottle_green));
        skins.add(getResources().getString(R.string.txt_light_green));
        rlTitleBg=rootView.findViewById(R.id.rl_tool_bar);
        tvBg=rootView.findViewById(R.id.tv_bg);
        rlTitleBg.setBackgroundColor(Color.parseColor(Constant.APP_SKIN_CODE));
        tvBg.setBackgroundColor(Color.parseColor(Constant.APP_SKIN_CODE));
        ivUserIcon=rootView.findViewById(R.id.iv_user_icon);
        ivLeft=rootView.findViewById(R.id.iv_title_left);
        ivLeft.setImageResource(R.mipmap.icon_message);
        ivRight=rootView.findViewById(R.id.iv_title_right);
        ivRight.setImageResource(R.mipmap.icon_setting);
        ivRight.setVisibility(View.VISIBLE);
        tvTitle=rootView.findViewById(R.id.tv_title);
        tvTitle.setText("");
        llUser=rootView.findViewById(R.id.ll_user);
        tvNickname=rootView.findViewById(R.id.tv_nickname);
        tvEmail=rootView.findViewById(R.id.tv_email);
        llBuyVIP=rootView.findViewById(R.id.ll_vip_buy);
        llChangeLanguage=rootView.findViewById(R.id.ll_language_change);
        llChangeSkin=rootView.findViewById(R.id.ll_skin_change);
        mLanguageChangeView=new ConditionalSelectorView(this.getActivity(),languages);
        mSkinChangeView=new ConditionalSelectorView(this.getActivity(),skins);
        setUserInfo();
    }

    @Override
    public void initListener() {
        super.initListener();
        llUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), UpdateUserInfoActivity.class);
                startActivity(intent);
            }
        });
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
            }
        });

        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });

        llBuyVIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        llChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLanguageChangeView.showConditionalSelectorView();
            }
        });

        llChangeSkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSkinChangeView.showConditionalSelectorView();

            }
        });

        mLanguageChangeView.setSelectCallBack(new ConditionalSelectorView.selectorCallBack() {
            @Override
            public void selectCondition(String condition, int position) {
                selectLanguage(position);
            }
        });
        mSkinChangeView.setSelectCallBack(new ConditionalSelectorView.selectorCallBack() {
            @Override
            public void selectCondition(String condition, int position) {
                selectedSkin(position);
            }
        });
    }

    @Override
    protected void loadData() {

    }


    /**
     * 设置昵称头像邮箱信息
     */
    private void setUserInfo(){
        if(Constant.userBean!=null){
            if(!StringUtils.isEmpty(Constant.userBean.getNickname())){
                tvNickname.setText(Constant.userBean.getNickname());
            }

            if(!StringUtils.isEmpty(Constant.userBean.getUserEmail())){
                tvEmail.setText(Constant.userBean.getUserEmail());
            }
        }
    }

    private void selectLanguage(int select) {
        LocalManageUtil.saveSelectLanguage(this.getActivity(), select);
        Intent intent=new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void selectedSkin(int position){
        if(position==0){
            Constant.APP_SKIN_CODE="#4ad0af";
        }else{
            Constant.APP_SKIN_CODE="#88b92e";
        }
        new SharePref().setStringValue(Constant.SKIN_TAG,Constant.APP_SKIN_CODE);
        EventBus.getDefault().post(new MessageEvent(0));
    }

    @Override
    public void updateUserInfo() {
        setUserInfo();
    }

    @Override
    public void changeSkin() {
        super.changeSkin();
        rlTitleBg.setBackgroundColor(Color.parseColor(Constant.APP_SKIN_CODE));
        tvBg.setBackgroundColor(Color.parseColor(Constant.APP_SKIN_CODE));
    }
}
