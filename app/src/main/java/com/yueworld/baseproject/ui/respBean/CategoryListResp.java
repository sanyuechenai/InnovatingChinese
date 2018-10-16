package com.yueworld.baseproject.ui.respBean;

import java.util.List;

/**
 * 创建时间: 2018/9/26.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class CategoryListResp {


    /**
     * ReturnParams : [{"CatgId":"Daily","CatgName":"Daily","CatgImgSrc":"/image/Daily.jpg","CatgSeqno":1,"Createby":"administrator","Modifyby":"administrator","CreateT":"2018-09-01T07:15:14","ModifyT":"2018-09-01T07:15:14","CourCount":6},{"CatgId":"Traval","CatgName":"Traval","CatgImgSrc":"/image/Traval.jpg","CatgSeqno":2,"Createby":"administrator","Modifyby":"administrator","CreateT":"2018-09-01T07:15:14","ModifyT":"2018-09-01T07:15:14","CourCount":0},{"CatgId":"Carrer","CatgName":"Carrer","CatgImgSrc":"/image/Carrer.jpg","CatgSeqno":3,"Createby":"administrator","Modifyby":"administrator","CreateT":"2018-09-01T07:15:14","ModifyT":"2018-09-01T07:15:14","CourCount":0},{"CatgId":"Business","CatgName":"Business","CatgImgSrc":"/image/Business.jpg","CatgSeqno":4,"Createby":"administrator","Modifyby":"administrator","CreateT":"2018-09-01T07:15:14","ModifyT":"2018-09-01T07:15:14","CourCount":0},{"CatgId":"News","CatgName":"News","CatgImgSrc":"/image/News.jpg","CatgSeqno":5,"Createby":"administrator","Modifyby":"administrator","CreateT":"2018-09-01T07:15:14","ModifyT":"2018-09-01T07:15:14","CourCount":0},{"CatgId":"School","CatgName":"School","CatgImgSrc":"/image/School.jpg","CatgSeqno":6,"Createby":"administrator","Modifyby":"administrator","CreateT":"2018-09-01T07:15:14","ModifyT":"2018-09-01T07:15:14","CourCount":0},{"CatgId":"Family","CatgName":"Family","CatgImgSrc":"/image/Family.jpg","CatgSeqno":7,"Createby":"administrator","Modifyby":"administrator","CreateT":"2018-09-01T07:15:14","ModifyT":"2018-09-01T07:15:14","CourCount":0},{"CatgId":"Economy","CatgName":"Economy","CatgImgSrc":"/image/Economy.jpg","CatgSeqno":8,"Createby":"administrator","Modifyby":"administrator","CreateT":"2018-09-01T07:15:14","ModifyT":"2018-09-01T07:15:14","CourCount":0},{"CatgId":"Food","CatgName":"Food","CatgImgSrc":"/image/Food.jpg","CatgSeqno":9,"Createby":"administrator","Modifyby":"administrator","CreateT":"2018-09-01T07:15:14","ModifyT":"2018-09-01T07:15:14","CourCount":0},{"CatgId":"Movie","CatgName":"Movie","CatgImgSrc":"/image/Movie.jpg","CatgSeqno":10,"Createby":"administrator","Modifyby":"administrator","CreateT":"2018-09-01T07:15:14","ModifyT":"2018-09-01T07:15:14","CourCount":0}]
     * Date : 2018-09-26 17:40:44
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

    public static class ReturnParamsBean {
        /**
         * CatgId : Daily
         * CatgName : Daily
         * CatgImgSrc : /image/Daily.jpg
         * CatgSeqno : 1
         * Createby : administrator
         * Modifyby : administrator
         * CreateT : 2018-09-01T07:15:14
         * ModifyT : 2018-09-01T07:15:14
         * CourCount : 6
         */

        private String CatgId;
        private String CatgName;
        private String CatgImgSrc;
        private int CatgSeqno;
        private int CourCount;

        public String getCatgId() {
            return CatgId;
        }

        public void setCatgId(String CatgId) {
            this.CatgId = CatgId;
        }

        public String getCatgName() {
            return CatgName;
        }

        public void setCatgName(Object CatgName) {
            if(CatgName!=null){
                this.CatgName = (String) CatgName;
            }else{
                this.CatgName = "";
            }

        }

        public String getCatgImgSrc() {
            return CatgImgSrc;
        }

        public void setCatgImgSrc(Object CatgImgSrc) {
            if(CatgImgSrc!=null){
                this.CatgImgSrc = (String) CatgImgSrc;
            }else{
                this.CatgImgSrc = "";
            }

        }

        public int getCatgSeqno() {
            return CatgSeqno;
        }

        public void setCatgSeqno(int CatgSeqno) {
            this.CatgSeqno = CatgSeqno;
        }

        public int getCourCount() {
            return CourCount;
        }

        public void setCourCount(int CourCount) {
            this.CourCount = CourCount;
        }
    }
}
