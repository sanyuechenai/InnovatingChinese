package com.yueworld.baseproject.ui.allCourse.bean;

/**
 * 创建时间: 2018/8/24.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class CourseBean {

    public String courseName;
    public int unitNum;
    public int iconPath;

    public CourseBean(String courseName,int unitNum,int iconPath){
        this.courseName=courseName;
        this.unitNum=unitNum;
        this.iconPath=iconPath;
    }
}
