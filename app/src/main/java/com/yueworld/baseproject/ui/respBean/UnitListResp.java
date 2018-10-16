package com.yueworld.baseproject.ui.respBean;

import java.io.Serializable;
import java.util.List;

/**
 * 创建时间: 2018/10/8.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class UnitListResp implements Serializable{


    /**
     * ReturnParams : [{"CourseId":"COUR000001","UnitId":"UNIT0000001","UnitName":"test unit name 1","UnitNameCn":"测试单元1","UnitIsfree":"Y","CourseUnitSeqno":1,"UnitImgSrc":"/image/测试单元1.jpg","UnitSenCount":5,"UnitPassion":1,"UnitIsFinished":null,"UnitScore":0,"UnitIsChallenge":null},{"CourseId":"COUR000001","UnitId":"UNIT0000002","UnitName":"test unit name 2","UnitNameCn":"测试单元2","UnitIsfree":"Y","CourseUnitSeqno":2,"UnitImgSrc":"/image/测试单元2.jpg","UnitSenCount":5,"UnitPassion":2,"UnitIsFinished":null,"UnitScore":0,"UnitIsChallenge":null},{"CourseId":"COUR000001","UnitId":"UNIT0000003","UnitName":"test unit name 3","UnitNameCn":"测试单元3","UnitIsfree":"Y","CourseUnitSeqno":3,"UnitImgSrc":"/image/测试单元3.jpg","UnitSenCount":5,"UnitPassion":3,"UnitIsFinished":null,"UnitScore":0,"UnitIsChallenge":null},{"CourseId":"COUR000001","UnitId":"UNIT0000004","UnitName":"test unit name 4","UnitNameCn":"测试单元4","UnitIsfree":"Y","CourseUnitSeqno":4,"UnitImgSrc":"/image/测试单元4.jpg","UnitSenCount":5,"UnitPassion":4,"UnitIsFinished":null,"UnitScore":0,"UnitIsChallenge":null},{"CourseId":"COUR000001","UnitId":"UNIT0000005","UnitName":"test unit name 5","UnitNameCn":"测试单元5","UnitIsfree":"Y","CourseUnitSeqno":5,"UnitImgSrc":"/image/测试单元5.jpg","UnitSenCount":5,"UnitPassion":5,"UnitIsFinished":null,"UnitScore":0,"UnitIsChallenge":null},{"CourseId":"COUR000001","UnitId":"UNIT0000006","UnitName":"test unit name 6","UnitNameCn":"测试单元6","UnitIsfree":"Y","CourseUnitSeqno":6,"UnitImgSrc":"/image/测试单元6.jpg","UnitSenCount":5,"UnitPassion":6,"UnitIsFinished":null,"UnitScore":0,"UnitIsChallenge":null}]
     * Date : 2018-09-24 19:46:29
     * Success : 100
     * ReturnString : null
     */

    private String Date;
    private int Success;
    private String ReturnString;
    private List<ReturnParamsBean> ReturnParams;

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
         * UnitId : UNIT0000001
         * UnitName : test unit name 1
         * UnitNameCn : 测试单元1
         * UnitIsfree : Y
         * CourseUnitSeqno : 1
         * UnitImgSrc : /image/测试单元1.jpg
         * UnitSenCount : 5
         * UnitPassion : 1
         * UnitIsFinished : null
         * UnitScore : 0
         * UnitIsChallenge : null
         */

        private String CourseId;
        private String UnitId;
        private String UnitName;
        private String UnitNameCn;
        private String UnitIsfree;
        private int CourseUnitSeqno;
        private String UnitImgSrc;
        private int UnitSenCount;
        private int UnitPassion;
        private String UnitIsFinished="";
        private int UnitScore;
        private String UnitIsChallenge="";

        public String getCourseId() {
            return CourseId;
        }

        public void setCourseId(String CourseId) {
            this.CourseId = CourseId;
        }

        public String getUnitId() {
            return UnitId;
        }

        public void setUnitId(String UnitId) {
            this.UnitId = UnitId;
        }

        public String getUnitName() {
            return UnitName;
        }

        public void setUnitName(String UnitName) {
            this.UnitName = UnitName;
        }

        public String getUnitNameCn() {
            return UnitNameCn;
        }

        public void setUnitNameCn(String UnitNameCn) {
            this.UnitNameCn = UnitNameCn;
        }

        public String getUnitIsfree() {
            return UnitIsfree;
        }

        public void setUnitIsfree(String UnitIsfree) {
            this.UnitIsfree = UnitIsfree;
        }

        public int getCourseUnitSeqno() {
            return CourseUnitSeqno;
        }

        public void setCourseUnitSeqno(int CourseUnitSeqno) {
            this.CourseUnitSeqno = CourseUnitSeqno;
        }

        public String getUnitImgSrc() {
            return UnitImgSrc;
        }

        public void setUnitImgSrc(String UnitImgSrc) {
            this.UnitImgSrc = UnitImgSrc;
        }

        public int getUnitSenCount() {
            return UnitSenCount;
        }

        public void setUnitSenCount(int UnitSenCount) {
            this.UnitSenCount = UnitSenCount;
        }

        public int getUnitPassion() {
            return UnitPassion;
        }

        public void setUnitPassion(int UnitPassion) {
            this.UnitPassion = UnitPassion;
        }

        public String getUnitIsFinished() {
            return UnitIsFinished;
        }

        public void setUnitIsFinished(Object UnitIsFinished) {
            if(UnitIsFinished!=null){
                this.UnitIsFinished = (String) UnitIsFinished;
            }else{
                this.UnitIsFinished = "";
            }

        }

        public int getUnitScore() {
            return UnitScore;
        }

        public void setUnitScore(int UnitScore) {
            this.UnitScore = UnitScore;
        }

        public String getUnitIsChallenge() {
            return UnitIsChallenge;
        }

        public void setUnitIsChallenge(Object UnitIsChallenge) {
            if(UnitIsChallenge!=null){
                this.UnitIsChallenge = (String) UnitIsChallenge;
            }else{
                this.UnitIsChallenge = "";
            }

        }
    }
}
