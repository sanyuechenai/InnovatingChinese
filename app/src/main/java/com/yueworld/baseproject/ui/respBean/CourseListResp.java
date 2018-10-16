package com.yueworld.baseproject.ui.respBean;

import java.io.Serializable;
import java.util.List;

/**
 * 创建时间: 2018/10/8.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class CourseListResp implements Serializable {


    /**
     * ReturnParams : [{"CourseId":"COUR000001","CourseName":"test cour name 1","CourseNameCN":"测试课程1","LevelId":"HSK1","CourUnitCount":6,"CourseIsRecommend":"N","CoursePassion":0,"CourseShelvesTime":"2018-09-01T07:15:14","CourseIsFree":"N","CourseImgSrc":"/image/测试课程1.jpg","Isdel":"N","IsAdded":"N"},{"CourseId":"COUR000002","CourseName":"test cour name 2","CourseNameCN":"测试课程2","LevelId":"HSK1","CourUnitCount":6,"CourseIsRecommend":"N","CoursePassion":0,"CourseShelvesTime":"2018-09-01T07:15:14","CourseIsFree":"N","CourseImgSrc":"/image/测试课程1.jpg","Isdel":"N","IsAdded":"N"},{"CourseId":"COUR000003","CourseName":"test cour name 3","CourseNameCN":"测试课程3","LevelId":"HSK1","CourUnitCount":6,"CourseIsRecommend":"N","CoursePassion":0,"CourseShelvesTime":"2018-09-01T07:15:14","CourseIsFree":"N","CourseImgSrc":"/image/测试课程3.jpg","Isdel":"N","IsAdded":"N"},{"CourseId":"COUR000004","CourseName":"test cour name 4","CourseNameCN":"测试课程4","LevelId":"HSK2","CourUnitCount":6,"CourseIsRecommend":"N","CoursePassion":0,"CourseShelvesTime":"2018-09-01T07:15:14","CourseIsFree":"N","CourseImgSrc":"/image/测试课程4.jpg","Isdel":"N","IsAdded":"N"},{"CourseId":"COUR000005","CourseName":"test cour name 5","CourseNameCN":"测试课程5","LevelId":"HSK2","CourUnitCount":6,"CourseIsRecommend":"N","CoursePassion":0,"CourseShelvesTime":"2018-09-01T07:15:14","CourseIsFree":"N","CourseImgSrc":"/image/测试课程5.jpg","Isdel":"N","IsAdded":"N"},{"CourseId":"COUR000006","CourseName":"test cour name 6","CourseNameCN":"测试课程6","LevelId":"HSK3","CourUnitCount":6,"CourseIsRecommend":"N","CoursePassion":0,"CourseShelvesTime":"2018-09-01T07:15:14","CourseIsFree":"N","CourseImgSrc":"/image/测试课程6.jpg","Isdel":"N","IsAdded":"N"}]
     * TotalPage : 1
     * Date : 2018-09-24 19:39:11
     * Success : 100
     * ReturnString : null
     */

    private int TotalPage;
    private int Success;
    private String ReturnString;
    private List<ReturnParamsBean> ReturnParams;

    public int getTotalPage() {
        return TotalPage;
    }

    public void setTotalPage(int TotalPage) {
        this.TotalPage = TotalPage;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int Success) {
        this.Success = Success;
    }

    public Object getReturnString() {
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
         * CourseId : COUR000001
         * CourseName : test cour name 1
         * CourseNameCN : 测试课程1
         * LevelId : HSK1
         * CourUnitCount : 6
         * CourseIsRecommend : N
         * CoursePassion : 0
         * CourseShelvesTime : 2018-09-01T07:15:14
         * CourseIsFree : N
         * CourseImgSrc : /image/测试课程1.jpg
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
        private String IsAdded;

        public String getCourseId() {
            return CourseId;
        }

        public void setCourseId(String CourseId) {
            this.CourseId = CourseId;
        }

        public String getCourseName() {
            return CourseName;
        }

        public void setCourseName(String CourseName) {
            this.CourseName = CourseName;
        }

        public String getCourseNameCN() {
            return CourseNameCN;
        }

        public void setCourseNameCN(String CourseNameCN) {
            this.CourseNameCN = CourseNameCN;
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

        public String getCourseShelvesTime() {
            return CourseShelvesTime;
        }

        public void setCourseShelvesTime(String CourseShelvesTime) {
            this.CourseShelvesTime = CourseShelvesTime;
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

        public void setCourseImgSrc(String CourseImgSrc) {
            this.CourseImgSrc = CourseImgSrc;
        }

        public String getIsAdded() {
            return IsAdded;
        }

        public void setIsAdded(String IsAdded) {
            this.IsAdded = IsAdded;
        }
    }
}
