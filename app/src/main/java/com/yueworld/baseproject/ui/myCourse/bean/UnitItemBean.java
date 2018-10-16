package com.yueworld.baseproject.ui.myCourse.bean;

import java.io.Serializable;

/**
 * 创建时间: 2018/8/23.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class UnitItemBean implements Serializable {

    public String nameCn;
    public String nameEn;

    public UnitItemBean(String nameCn,String nameEn){
        this.nameCn=nameCn;
        this.nameEn=nameEn;
    }
}
