package com.yueworld.baseproject.ui.setting.activity;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yueworld.baseproject.MyApplication;
import com.yueworld.baseproject.R;
import com.yueworld.baseproject.base.BaseActivity;
import com.yueworld.baseproject.ui.respBean.MessageEvent;
import com.yueworld.baseproject.ui.setting.IUpdatePersonInfoView;
import com.yueworld.baseproject.ui.setting.presenter.UpdateUserInfoPresenter;
import com.yueworld.baseproject.utils.CommonUtils;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.SharePref;
import com.yueworld.baseproject.utils.StringUtils;
import com.yueworld.baseproject.utils.UIUtils;

import org.greenrobot.eventbus.EventBus;

public class UpdateUserInfoActivity extends BaseActivity<UpdateUserInfoPresenter> implements IUpdatePersonInfoView {


    private TextView tvBg;
    private TextView tvEmail;
    private EditText etNickname,etAge;
    private ImageView ivMale,ivFamale;
    private TextView tvLogout;
    private String email,nickname,sex,age;
    private String selectedSex;
    private PopupWindow window;
    private LayoutInflater mInflater;

    @Override
    protected UpdateUserInfoPresenter createPresenter() {
        return new UpdateUserInfoPresenter(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_update_user_info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initEvent();
    }



    private void initView(){
        mInflater=LayoutInflater.from(this);
        tvBg=FindViewById(R.id.tv_bg);
        tvEmail=FindViewById(R.id.tv_email);
        etNickname=FindViewById(R.id.et_nickname);

        etAge=FindViewById(R.id.et_age);
        ivMale=FindViewById(R.id.iv_male);
        ivFamale=FindViewById(R.id.iv_famale);
        tvLogout=FindViewById(R.id.tv_logout);
        tvBg.setBackgroundColor(Color.parseColor(Constant.APP_SKIN_CODE));
        tvLogout.setBackgroundColor(Color.parseColor(Constant.APP_SKIN_CODE));
        if(Constant.userBean!=null){
            email=Constant.userBean.getUserEmail();
            nickname=Constant.userBean.getNickname();
            sex=Constant.userBean.getUserGender();
            selectedSex=sex;
            age=String.valueOf(Constant.userBean.getUserAge());
            if(!StringUtils.isEmpty(email)){
                tvEmail.setText(email);
            }
            if(!StringUtils.isEmpty(nickname)){
                etNickname.setText(nickname);
                Editable editable=etNickname.getText();
                Selection.setSelection(editable,editable.length());
            }

            if(!StringUtils.isEmpty(sex)){
                if(sex.equals("男")){
                    ivMale.setImageResource(R.mipmap.icon_selected);
                    ivFamale.setImageResource(R.mipmap.icon_unselected);
                }else{
                    ivMale.setImageResource(R.mipmap.icon_unselected);
                    ivFamale.setImageResource(R.mipmap.icon_selected);
                }

            }

            if(!StringUtils.isEmpty(age)){
                etAge.setText(age);
                Editable editable=etAge.getText();
                Selection.setSelection(editable,editable.length());
            }
        }
    }

    private void initEvent(){
        ivMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivMale.setImageResource(R.mipmap.icon_selected);
                ivFamale.setImageResource(R.mipmap.icon_unselected);
                selectedSex="男";
            }
        });

        ivFamale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivMale.setImageResource(R.mipmap.icon_unselected);
                ivFamale.setImageResource(R.mipmap.icon_selected);
                selectedSex="女";
            }
        });
        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.logout();
                finish();
            }
        });
    }


    @Override
    protected void onLeftIvClick() {
        if(hasChange()){
            showSurePop(etNickname);
        }else{
            finish();
        }
    }

    private boolean  hasChange(){
        if(!TextUtils.equals(nickname,etNickname.getText().toString().trim())){
            return true;
        }

        if(!TextUtils.equals(sex,selectedSex)){
            return true;
        }
        if(!TextUtils.equals(age,etAge.getText().toString().trim())){
            return true;
        }
        return false;
    }


    private void showSurePop(final View parent){
        window=new PopupWindow(this);
        View itemView=mInflater .inflate(R.layout.pop_sure_layout,null);
        TextView tvSure= (TextView) itemView.findViewById(R.id.tv_sure);
        TextView tvCancel=itemView.findViewById(R.id.tv_cancel);
        tvSure.setTextColor(Color.parseColor(Constant.APP_SKIN_CODE));
        tvCancel.setTextColor(Color.parseColor(Constant.APP_SKIN_CODE));
        window.setContentView(itemView);
        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setFocusable(true);
        window.setOutsideTouchable(false);
        window.setTouchable(true);
        window.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        window.showAtLocation(parent, Gravity.CENTER, 0, 0);
        window.update();
        CommonUtils.setWindow(0.2f, this);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                window = null;
                CommonUtils.setWindow(1, UpdateUserInfoActivity.this);
            }
        });

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                if(!StringUtils.isNumeric(etAge.getText().toString().trim())){
                    UIUtils.showToast(getString(R.string.txt_age_unnormal));
                    return;
                }
                showLoadingDialog("");
                mPresenter.updateUserInfo(etNickname.getText().toString().trim(),selectedSex,etAge.getText().toString().trim());
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
                finish();
            }
        });
    }

    @Override
    public void updateUserInfoSuccess() {
        dissMissDialog();
        Constant.userBean.setNickname(etNickname.getText().toString().trim());
        Constant.userBean.setUserGender(selectedSex);
        Constant.userBean.setUserAge(Integer.parseInt(etAge.getText().toString().trim()));
        new SharePref(this).save(Constant.USER_KEY,Constant.userBean);
        MessageEvent messageEvent=new MessageEvent(2);
        EventBus.getDefault().post(messageEvent);
        finish();
    }

    @Override
    public void updateUserInfoFail() {
        dissMissDialog();

    }
}
