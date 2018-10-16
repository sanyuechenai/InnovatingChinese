package com.yueworld.baseproject.ui.respBean;

import java.util.List;

/**
 * 创建时间: 2018/10/8.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class LevelListBean {


    /**
     * ReturnParams : [{"LevelId":"HSK1","LevelName":"HSK 1","LevelSeqno":1,"Isdel":"N"},{"LevelId":"HSK2","LevelName":"HSK 2","LevelSeqno":2,"Isdel":"N"},{"LevelId":"HSK3","LevelName":"HSK 3","LevelSeqno":3,"Isdel":"N"},{"LevelId":"HSK4","LevelName":"HSK 4","LevelSeqno":4,"Isdel":"N"}]
     * Date : 2018-09-24 19:19:12
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
         * LevelId : HSK1
         * LevelName : HSK 1
         * LevelSeqno : 1
         * Isdel : N
         */

        private String LevelId;
        private String LevelName;
        private int LevelSeqno;
        private boolean isSelected=false;

        public String getLevelId() {
            return LevelId;
        }

        public void setLevelId(String LevelId) {
            this.LevelId = LevelId;
        }

        public String getLevelName() {
            return LevelName;
        }

        public void setLevelName(String LevelName) {
            this.LevelName = LevelName;
        }

        public int getLevelSeqno() {
            return LevelSeqno;
        }

        public void setLevelSeqno(int LevelSeqno) {
            this.LevelSeqno = LevelSeqno;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }
}
