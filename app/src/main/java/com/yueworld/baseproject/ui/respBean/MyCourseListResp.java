package com.yueworld.baseproject.ui.respBean;

import java.io.Serializable;
import java.util.List;

/**
 * 创建时间: 2018/9/26.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class MyCourseListResp {


    /**
     * ReturnParams : [{"UserId":"testid1","UnitFinishedCount":0,"CourIsFinished":"N","CourScore":0,"CourseId":"COUR000001","CourseName":"test cour name 1","CourseNameCN":"测试课程1","CourUnitCount":6,"CourseImgSrc":"/image/测试课程1.jpg"}]
     * TotalPage : 1
     * Date : 2018-09-26 14:39:57
     * Success : 100
     * ReturnString : null
     */

    private int TotalPage;
    private String Date;
    private int Success;
    private String ReturnString;
    private List<ReturnParamsBean> ReturnParams;

    public int getTotalPage() {
        return TotalPage;
    }

    public void setTotalPage(int TotalPage) {
        this.TotalPage = TotalPage;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(Object Date) {
        if(Date!=null){
            this.Date = (String) Date;
        }else{
            this.Date = "";
        }

    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int Success) {
        this.Success = Success;
    }

    public String getReturnString() {
        return ReturnString;
    }

    public void setReturnString(Object ReturnString) {
        if(ReturnString!=null){
            this.ReturnString = (String) ReturnString;
        }else{
            this.ReturnString = "";
        }

    }

    public List<ReturnParamsBean> getReturnParams() {
        return ReturnParams;
    }

    public void setReturnParams(List<ReturnParamsBean> ReturnParams) {
        this.ReturnParams = ReturnParams;
    }

    public static class ReturnParamsBean implements Serializable{
        /**
         * UserId : testid1
         * UnitFinishedCount : 0
         * CourIsFinished : N
         * CourScore : 0
         * CourseId : COUR000001
         * CourseName : test cour name 1
         * CourseNameCN : 测试课程1
         * CourUnitCount : 6
         * CourseImgSrc : /image/测试课程1.jpg
         */

        private String UserId;
        private int UnitFinishedCount;
        private String CourIsFinished;
        private int CourScore;
        private String CourseId;
        private String CourseName;
        private String CourseNameCN;
        private int CourUnitCount;
        private String CourseImgSrc;

        public String getUserId() {
            return UserId;
        }

        public void setUserId(Object UserId) {
            if(UserId!=null){
                this.UserId = (String) UserId;
            }else{
                this.UserId = "";
            }

        }

        public int getUnitFinishedCount() {
            return UnitFinishedCount;
        }

        public void setUnitFinishedCount(int UnitFinishedCount) {
            this.UnitFinishedCount = UnitFinishedCount;
        }

        public String getCourIsFinished() {
            return CourIsFinished;
        }

        public void setCourIsFinished(Object CourIsFinished) {
            if(CourIsFinished!=null){
                this.CourIsFinished = (String) CourIsFinished;
            }else{
                this.CourIsFinished = "";
            }

        }

        public int getCourScore() {
            return CourScore;
        }

        public void setCourScore(int CourScore) {
            this.CourScore = CourScore;
        }

        public String getCourseId() {
            return CourseId;
        }

        public void setCourseId(String CourseId) {
            this.CourseId = CourseId;
        }

        public String getCourseName() {
            return CourseName;
        }

        public void setCourseName(Object CourseName) {
            if(CourseName!=null){
                this.CourseName = (String) CourseName;
            }else{
                this.CourseName = "";
            }

        }

        public String getCourseNameCN() {
            return CourseNameCN;
        }

        public void setCourseNameCN(Object CourseNameCN) {
            if(CourseNameCN!=null){
                this.CourseNameCN = (String) CourseNameCN;
            }else{
                this.CourseNameCN = "";
            }

        }

        public int getCourUnitCount() {
            return CourUnitCount;
        }

        public void setCourUnitCount(int CourUnitCount) {
            this.CourUnitCount = CourUnitCount;
        }

        public String getCourseImgSrc() {
            return CourseImgSrc;
        }

        public void setCourseImgSrc(Object CourseImgSrc) {
            if(CourseImgSrc!=null){
                this.CourseImgSrc = (String) CourseImgSrc;
            }else{
                this.CourseImgSrc = "";
            }

        }
    }
}
