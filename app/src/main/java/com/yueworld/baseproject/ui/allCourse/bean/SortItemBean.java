package com.yueworld.baseproject.ui.allCourse.bean;

/**
 * 创建时间: 2018/8/25.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class SortItemBean {

    private String sortId;
    private String sortName;
    private boolean isSelected=false;

    public SortItemBean(String sortId, String sortName,boolean isSelected){
        this.sortId=sortId;
        this.sortName=sortName;
        this.isSelected=isSelected;
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
