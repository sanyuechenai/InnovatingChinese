package com.yueworld.baseproject.ui.respBean;

import java.util.List;

/**
 * 创建时间: 2018/9/28.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class RecommendListResp {


    /**
     * ReturnParams : [{"CourseId":"COUR000003","CourseName":"test cour name 3","CourseNameCN":"测试课程3","LevelId":"HSK1","CourUnitCount":6,"CourseIsRecommend":"Y","CoursePassion":0,"CourseShelvesTime":"2018-09-01T07:15:14","CourseIsFree":"N","CourseImgSrc":null,"Isdel":"N","IsAdded":"N"},{"CourseId":"COUR000004","CourseName":"test cour name 4","CourseNameCN":"测试课程4","LevelId":"HSK2","CourUnitCount":6,"CourseIsRecommend":"Y","CoursePassion":0,"CourseShelvesTime":"2018-09-01T07:15:14","CourseIsFree":"N","CourseImgSrc":null,"Isdel":"N","IsAdded":"N"}]
     * TotalPage : 1
     * Date : 2018-09-28 16:53:42
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

    public void setDate(String Date) {
        this.Date = Date;
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

    public static class ReturnParamsBean {
        /**
         * CourseId : COUR000003
         * CourseName : test cour name 3
         * CourseNameCN : 测试课程3
         * LevelId : HSK1
         * CourUnitCount : 6
         * CourseIsRecommend : Y
         * CoursePassion : 0
         * CourseShelvesTime : 2018-09-01T07:15:14
         * CourseIsFree : N
         * CourseImgSrc : null
         * Isdel : N
         * IsAdded : N
         */

        private String CourseId;
        private String CourseName;
        private String CourseNameCN;
        private String LevelId;
        private int CourUnitCount;
        private String CourseIsRecommend;
        private int CoursePassion;
        private String CourseShelvesTime;
        private String CourseIsFree;
        private String CourseImgSrc;

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

        public String getLevelId() {
            return LevelId;
        }

        public void setLevelId(String LevelId) {
            this.LevelId = LevelId;
        }

        public int getCourUnitCount() {
            return CourUnitCount;
        }

        public void setCourUnitCount(int CourUnitCount) {
            this.CourUnitCount = CourUnitCount;
        }

        public String getCourseIsRecommend() {
            return CourseIsRecommend;
        }

        public void setCourseIsRecommend(String CourseIsRecommend) {
            this.CourseIsRecommend = CourseIsRecommend;
        }

        public int getCoursePassion() {
            return CoursePassion;
        }

        public void setCoursePassion(int CoursePassion) {
            this.CoursePassion = CoursePassion;
        }

        public String getCourseIsFree() {
            return CourseIsFree;
        }

        public void setCourseIsFree(String CourseIsFree) {
            this.CourseIsFree = CourseIsFree;
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
