package com.yueworld.baseproject.ui.myCourse.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.czt.mp3recorder.MP3Recorder;
import com.piterwilson.audio.MP3RadioStreamDelegate;
import com.piterwilson.audio.MP3RadioStreamPlayer;
import com.shuyu.waveview.AudioPlayer;
import com.shuyu.waveview.AudioWaveView;
import com.yueworld.baseproject.R;
import com.yueworld.baseproject.base.BaseActivity;
import com.yueworld.baseproject.ui.myCourse.adapter.UnitStudyItemAdapter;
import com.yueworld.baseproject.ui.myCourse.bean.UnitItemBean;
import com.yueworld.baseproject.ui.myCourse.presenter.UnitStudyPresenter;
import com.yueworld.baseproject.ui.respBean.UnitSentenceListResp;
import com.yueworld.baseproject.utils.CommonUtils;
import com.yueworld.baseproject.utils.FileUtils;
import com.yueworld.baseproject.utils.StringUtils;
import com.yueworld.baseproject.utils.UIUtils;
import com.yueworld.baseproject.view.customRecyclerview.CardScaleHelper;
import com.yueworld.baseproject.view.customRecyclerview.SpeedRecyclerView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static net.lucode.hackware.magicindicator.buildins.UIUtil.getScreenWidth;

public class UnitStudyActivity extends BaseActivity<UnitStudyPresenter> implements View.OnClickListener, MP3RadioStreamDelegate {

    private SpeedRecyclerView mRecyclerView;
    private UnitStudyItemAdapter mAdapter;
    private List<UnitSentenceListResp.ReturnParamsBean> mDataList;
    private CardScaleHelper mCardScaleHelper = null;
    private int currentPosition=0;
    private AudioWaveView mAudioView;
    private AudioWaveView playWaveView;
    private MP3Recorder mRecorder;
    private MP3RadioStreamPlayer player;
    private ImageView ivLeftPlay,ivRecording,ivRightPlay;
    private String filePath="";//存放每个unit的录音文件 的地址


    @Override
    protected UnitStudyPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_unit_start;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getSerializableExtra("unit")!=null){
            mDataList= (List<UnitSentenceListResp.ReturnParamsBean>) getIntent().getSerializableExtra("unit");
            currentPosition=getIntent().getIntExtra("position",0);
        }
        setTitleTxt("Unit Name");
        initView();
        initEvent();
    }


    private void initView(){
        mRecyclerView=FindViewById(R.id.srv_unit_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter=new UnitStudyItemAdapter(mDataList);
        mRecyclerView.setAdapter(mAdapter);
        mCardScaleHelper = new CardScaleHelper(1.0f,0,0);
        mCardScaleHelper.setCurrentItemPos(currentPosition);
        mCardScaleHelper.attachToRecyclerView(mRecyclerView);
        mAudioView=FindViewById(R.id.audioWave);
        playWaveView=FindViewById(R.id.playWave);
        ivLeftPlay=FindViewById(R.id.iv_left_play);
        ivRecording=FindViewById(R.id.iv_recording);
        ivRightPlay=FindViewById(R.id.iv_right_play);
        initAudioPlayer();
    }

    private void initAudioPlayer(){


    }

    private void initEvent(){
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    UIUtils.showToast("当前划到到第 "+mCardScaleHelper.getCurrentItemPos()+" 页");
                    currentPosition=mCardScaleHelper.getCurrentItemPos();
                    ivRightPlay.setEnabled(false);
                    ivRightPlay.setImageResource(R.mipmap.icon_gray_rightbutton);
                }
            }
        });
        ivLeftPlay.setOnClickListener(this);
        ivRightPlay.setOnClickListener(this);
        ivRecording.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                startRecording();
                return false;//结束时自动调一次短点击事件
            }
        });
        ivRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopRecording();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.iv_left_play:
                UIUtils.showToast("播放在线语音");
                break;
            case R.id.iv_right_play:
                startPlay();
                break;
        }

    }

    /**
     * 开始录音
     */
    private void startRecording(){
        File  recordingFile=new File(FileUtils.getRecordingFilePath());
        if(!recordingFile.exists()){
            if(!recordingFile.mkdirs()){
                UIUtils.showToast("创建存放录音文件的文件夹失败");
                return;
            }
        }
        mAudioView.setVisibility(View.VISIBLE);
        playWaveView.setVisibility(View.GONE);
        int offset = CommonUtils.px(this,1);
        filePath = FileUtils.getRecordingFilePath() + UUID.randomUUID().toString()+ ".mp3";
        mRecorder = new MP3Recorder(new File(filePath));
        int size = CommonUtils.getWidth(this)/ offset;//控件默认的间隔是1
        mRecorder.setDataList(mAudioView.getRecList(), size);
        mRecorder.setErrorHandler(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == MP3Recorder.ERROR_TYPE) {
                    UIUtils.showToast("没有麦克风权限");
                    recordingError();
                }
            }
        });
        try {
            mRecorder.start();
            mAudioView.startView();
        } catch (IOException e) {
            e.printStackTrace();
            UIUtils.showToast("录音出现异常");
            recordingError();
            return;
        }
    }


    /**
     * 录音失败
     */
    private void recordingError(){
        FileUtils.deleteFile(filePath);
        filePath="";
        mRecorder.stop();
        mAudioView.stopView();
    }


    /**
     * 停止录音
     */
    private void stopRecording(){
        if (mRecorder != null && mRecorder.isRecording()) {
            mRecorder.setPause(false);
            mRecorder.stop();
            mAudioView.stopView();
            if(new File(filePath).exists()){
                ivRightPlay.setEnabled(true);
                ivRightPlay.setImageResource(R.mipmap.icon_right_canplay);
            }else{
                ivRightPlay.setEnabled(false);
                ivRightPlay.setImageResource(R.mipmap.icon_gray_rightbutton);
            }
        }
    }

    private void startPlay(){
        if(StringUtils.isEmpty(filePath)||!new File(filePath).exists()){
            UIUtils.showToast("录音文件不存在");
            return;
        }
        if(player!=null){
            player.stop();
            player.release();
            player = null;
        }
        player = new MP3RadioStreamPlayer();
        player.setDelegate(this);
        int size = getScreenWidth(this) / CommonUtils.px(this, 1);//控件默认的间隔是1
        player.setDataList(playWaveView.getRecList(), size);
        playWaveView.setVisibility(View.VISIBLE);
        mAudioView.setVisibility(View.GONE);
        player.setUrlString(filePath);
        playWaveView.setBaseRecorder(player);
        playWaveView.startView();
        try {
            player.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRadioPlayerPlaybackStarted(MP3RadioStreamPlayer player) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ivRecording.setEnabled(false);
                ivRightPlay.setEnabled(false);
                ivRightPlay.setImageResource(R.mipmap.icon_gray_rightbutton);
            }
        });


    }

    @Override
    public void onRadioPlayerStopped(MP3RadioStreamPlayer player) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ivRecording.setEnabled(true);
                ivRightPlay.setEnabled(true);
                ivRightPlay.setImageResource(R.mipmap.icon_right_canplay);
            }
        });


    }

    @Override
    public void onRadioPlayerError(MP3RadioStreamPlayer player) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                UIUtils.showToast("播放异常");
                ivRecording.setEnabled(true);
                ivRightPlay.setEnabled(true);
                ivRightPlay.setImageResource(R.mipmap.icon_right_canplay);
            }
        });


    }

    @Override
    public void onRadioPlayerBuffering(MP3RadioStreamPlayer player) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ivRecording.setEnabled(false);
                ivRightPlay.setEnabled(false);
                ivRightPlay.setImageResource(R.mipmap.icon_gray_rightbutton);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mAudioView!=null){
            mAudioView.stopView();
        }
        if(playWaveView!=null){
            playWaveView.stopView();
        }
        if(player!=null){
            player.stop();
        }
    }
}
