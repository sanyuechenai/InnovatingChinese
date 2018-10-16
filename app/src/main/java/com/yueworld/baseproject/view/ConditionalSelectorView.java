package com.yueworld.baseproject.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.yueworld.baseproject.R;

import java.util.List;

/**
 * Created by Vincent Chen on 2017/11/1.
 */
public class ConditionalSelectorView {

    private OptionsPickerView pvOptions;
    private selectorCallBack mCallBack;
    private int callBackTag=0;//回调时的不同标识
    private List<?> conditionalArray;

    public ConditionalSelectorView(Context context, List<String> conditionalArray){
        this.conditionalArray=conditionalArray;
        initOptionPickerView(context);
    }

    /**
     * 初始化控件
     * @param context
     */
    private void initOptionPickerView(Context context){
        pvOptions=new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if(!conditionalArray.isEmpty()){
                    String txt=(String)conditionalArray.get(options1);
                    if(mCallBack!=null){
                        mCallBack.selectCondition(txt,options1);
                    }
                }
            }
        })
//                .setTitleText("城市选择")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0)//默认选中项
                .setBgColor(Color.WHITE)
//                .setTitleBgColor(Color.DKGRAY)
                .setTitleColor(Color.LTGRAY)
                    .setCancelColor(context.getResources().getColor(R.color.colorToolbar))
                .setSubmitColor(context.getResources().getColor(R.color.colorToolbar))
                .setTextColorCenter(context.getResources().getColor(R.color.colorBlack))
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("", "", "")
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();
        pvOptions.setPicker(conditionalArray);
        pvOptions.returnData();
    }

    /**
     * 显示条件筛选View
     */
    public void showConditionalSelectorView(){

        if(pvOptions!=null){
            pvOptions.show();
        }
    }

    /**
     *
     * 重新绑定数据
     * @param array 显示的数据集
     */
    public void notifyBindArrayList(List<String> array){
        conditionalArray=array;
        if(pvOptions!=null){
            pvOptions.setPicker(conditionalArray);
        }
    }

    /**
     * 通过设置标识，返回回调的不同情况
     * @param tag 不同标识
     */
    public void setCallBackTag(int tag){
        callBackTag=tag;
    }

    /**
     * @param callBack 接口回调
     */
    public void setSelectCallBack(selectorCallBack callBack){
        mCallBack=callBack;
    }

    public interface selectorCallBack{
        void selectCondition(String condition, int position);
    }

}
