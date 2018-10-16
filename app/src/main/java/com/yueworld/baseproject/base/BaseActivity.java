package com.yueworld.baseproject.base;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.github.nukc.stateview.StateView;
import com.yueworld.baseproject.MyApplication;
import com.yueworld.baseproject.R;
import com.yueworld.baseproject.ui.home.activity.HomeActivity;
import com.yueworld.baseproject.ui.respBean.MessageEvent;
import com.yueworld.baseproject.utils.Constant;
import com.yueworld.baseproject.utils.LocalManageUtil;
import com.yueworld.baseproject.utils.UIUtils;
import com.yueworld.baseproject.view.LoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import flyn.Eyes;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    protected T mPresenter;
    private static long mPreTime;
    private static Activity mCurrentActivity;// 对所有activity进行管理
    protected StateView mStateView;
    private LoadingDialog loadingDialog;
    private View toolBar;
    private RelativeLayout rlTitleBar;
    private LinearLayout mLlContainer;//布局容器
    private ImageView mIvLeft,mIvRight;
    private TextView mTvTitle,mTvRight;
    private View titleLine;
    protected int[] pullAnimSrcs = new int[]{R.drawable.mt_pull, R.drawable.mt_pull01, R.drawable.mt_pull02, R.drawable.mt_pull03, R.drawable.mt_pull04, R.drawable.mt_pull05};
    protected int[] refreshAnimSrcs = new int[]{R.drawable.mt_refreshing01, R.drawable.mt_refreshing02, R.drawable.mt_refreshing03, R.drawable.mt_refreshing04, R.drawable.mt_refreshing05, R.drawable.mt_refreshing06};
    protected int[] loadingAnimSrcs = new int[]{R.drawable.mt_loading01, R.drawable.mt_loading02};


    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO

    };
    private static final int PERMISSON_REQUESTCODE = 0;
    private PermissionCallBack permissionCallBack;


    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract T createPresenter();

    /**
     * 所有继承此基类的activity都会实现该方法
     *
     * @return 布局id
     */
    protected abstract int getContentViewId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        //设置状态栏文字颜色
        Eyes.setStatusBarColor(this,Color.parseColor(Constant.APP_SKIN_CODE));
        //初始化的时候将其添加到集合中
        synchronized (MyApplication.mActivities) {
            MyApplication.mActivities.add(this);
        }
        initView();
        loadingDialog=new LoadingDialog(this);
        mPresenter = createPresenter();
        initEvent();
        EventBus.getDefault().register(this);
    }




    private void initView(){
        toolBar=findViewById(R.id.view_title_bar);
        rlTitleBar=findViewById(R.id.rl_tool_bar);
        rlTitleBar.setBackgroundColor(Color.parseColor(Constant.APP_SKIN_CODE));
        mLlContainer= (LinearLayout) findViewById(R.id.layout_container);
        mIvLeft= (ImageView) findViewById(R.id.iv_title_left);
        mIvRight= (ImageView) findViewById(R.id.iv_title_right);
        mTvTitle= (TextView) findViewById(R.id.tv_title);
        mTvRight= (TextView) findViewById(R.id.tv_title_right);
        titleLine=findViewById(R.id.title_line);
        initContentView();
    }


    private void initEvent(){
        mIvLeft.setOnClickListener(onClickListener);
        mIvRight.setOnClickListener(onClickListener);
        mTvRight.setOnClickListener(onClickListener);
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_title_left:
                    onLeftIvClick();
                    break;
                case R.id.iv_title_right:
                    onRightIvClick();
                    break;
                case R.id.tv_title_right:
                    onRightTvClick();
                    break;
            }
        }
    };



    /**
     * 添加子布局
     */
    private void initContentView(){
        if (getContentViewId() != 0) {
            mLlContainer.addView(
                    getLayoutInflater().inflate(getContentViewId(), null),
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );
        } else {
            mLlContainer.setVisibility(View.GONE);
        }
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T FindViewById(int rsId) {
        return (T) findViewById(rsId);
    }





    protected void showLoadingDialog(String txt){
        if(loadingDialog!=null){
            loadingDialog.showDialog(txt);
        }
    }


    protected void dissMissDialog(){
        if(loadingDialog!=null){
            loadingDialog.dissMissDialog();
        }
    }

    @Override
    public void startActivity(Intent intent){
        super.startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.scale_gone);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.right_in, R.anim.scale_gone);
    }


    /**
     * title的左边图标点击事件
     */
    protected void onLeftIvClick(){
        this.finish();
    }

    /**
     * title的右边图标点击事件
     */
    protected void onRightIvClick(){
    }

    /**
     * title的右边文字点击事件
     */
    protected void onRightTvClick(){
    }

    protected void setTitleLineVisible(){
        if(titleLine!=null){
            titleLine.setVisibility(View.VISIBLE);
        }
    }

    protected void setTitleLineVisible(int colorId){
        if(titleLine!=null){
            titleLine.setVisibility(View.VISIBLE);
            titleLine.setBackgroundColor(getResources().getColor(colorId));
        }
    }




    /**
     * 设置左边图标
     * @param resId
     */
    protected void setLeftImg(int resId){
        if(mIvLeft!=null){
            mIvLeft.setVisibility(View.VISIBLE);
            mIvLeft.setImageResource(resId);
        }
    }

    /**
     * 设置右边图标
     * @param resId
     */
    protected void setRightImg(int resId){
        if(mIvRight!=null){
            mTvRight.setVisibility(View.GONE);
            mIvRight.setVisibility(View.VISIBLE);
            mIvRight.setImageResource(resId);
        }
    }

    /**
     * 设置右边文字
     * @param txt
     */
    protected void setRightTV(String txt){
        if(mTvRight!=null){
            mIvRight.setVisibility(View.GONE);
            mTvRight.setVisibility(View.VISIBLE);
            mTvRight.setText(txt);
        }
    }

    /**
     * 设置右边文字和颜色
     * @param txt
     */
    protected void setRightTV(String txt,int colorId){
        setRightTV(txt);
        mTvRight.setTextColor(getResources().getColor(colorId));
    }


    /**
     * 设置title文字
     * @param txt
     */
    protected void setTitleTxt(String txt){
        if(mTvTitle!=null){
            mTvTitle.setText(txt);
        }
    }

    /**
     * 设置title文字和颜色
     * @param txt
     */
    protected void setTitleTxt(String txt,int colorId){
        setTitleTxt(txt);
        mTvTitle.setTextColor(getResources().getColor(colorId));
    }


    /**
     * 设置title的背景颜色
     * @param colorId
     */
    protected void setToolBarBg(int colorId){
        if(rlTitleBar!=null){
            rlTitleBar.setBackgroundColor(getResources().getColor(colorId));
        }
    }


    /**
     * 设置toolBar是否显示
     * @param isVisible
     */
    protected void setToolBarVisible(boolean isVisible){
        if(toolBar!=null){
            toolBar.setVisibility(isVisible?View.VISIBLE:View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCurrentActivity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCurrentActivity = null;
    }


    /**
     * Eventbus接收器
     * @param messageEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateUserCoinsEvent(MessageEvent messageEvent){
        if(messageEvent.getMsgType()==2){//更改用户信息
        }else if(messageEvent.getMsgType()==0){//更改皮肤
            Eyes.setStatusBarColor(this,Color.parseColor(Constant.APP_SKIN_CODE));
            changeSkin();
        }else if(messageEvent.getMsgType()==1){//切换页面
            changeActivity();
        }
    }


    public void changeSkin(){

    }

    protected void changeActivity(){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //销毁的时候从集合中移除
        synchronized (MyApplication.mActivities) {
            MyApplication.mActivities.remove(this);
        }

        if (mPresenter != null) {
            mPresenter.detachView();
        }

        EventBus.getDefault().unregister(this);
    }

    protected void setNeedPermissionCallBack(PermissionCallBack callBack){
        permissionCallBack=callBack;
        checkPermissions(needPermissions);
    }

    /**
     *
     * @since 2.5.0
     *
     */
    private void checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(
                            new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }else{
            if(permissionCallBack!=null){
                permissionCallBack.doNext();
            }
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     *
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否说有的权限都已经授权
     * @param grantResults
     * @return
     * @since 2.5.0
     *
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     *
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage(R.string.notifyMsg);

        // 拒绝, 退出应用
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton("设置",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                        finish();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }

    /**
     *  启动应用的设置
     *
     * @since 2.5.0
     *
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                showMissingPermissionDialog();
            }else{
                if(permissionCallBack!=null){
                    permissionCallBack.doNext();
                }
            }
        }
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocalManageUtil.setLocal(newBase));
    }

    public interface PermissionCallBack{
        void doNext();
    }


    /**
     * 统一退出控制
     */
    @Override
    public void onBackPressed() {
        if (mCurrentActivity instanceof HomeActivity){
            //如果是主页面
            if (System.currentTimeMillis() - mPreTime > 2000) {// 两次点击间隔大于2秒
                mPreTime = System.currentTimeMillis();
                UIUtils.showToast("再点击一次退出");
                return;
            }
        }
        super.onBackPressed();// finish()
    }
}
