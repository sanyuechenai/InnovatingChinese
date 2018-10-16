package com.yueworld.baseproject.ui.respBean;

import java.util.List;

/**
 * 创建时间: 2018/10/8.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class MessageListResp {


    /**
     * ReturnParams : [{"NoteId":2,"NoteTitle":"TEST TITLE2","NoteBody":"TEST MESSAGE2","CreatetT":"2018-09-01T07:15:14","Isdel":"N"},{"NoteId":1,"NoteTitle":"TEST TITLE1","NoteBody":"TEST MESSAGE1","CreatetT":"2018-09-01T07:15:14","Isdel":"N"}]
     * TotalPage : 1
     * Date : 2018-09-24 21:22:08
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
         * NoteId : 2
         * NoteTitle : TEST TITLE2
         * NoteBody : TEST MESSAGE2
         * CreatetT : 2018-09-01T07:15:14
         * Isdel : N
         */

        private int NoteId;
        private String NoteTitle;
        private String NoteBody;
        private String CreatetT;

        public int getNoteId() {
            return NoteId;
        }

        public void setNoteId(int NoteId) {
            this.NoteId = NoteId;
        }

        public String getNoteTitle() {
            return NoteTitle;
        }

        public void setNoteTitle(String NoteTitle) {
            this.NoteTitle = NoteTitle;
        }

        public String getNoteBody() {
            return NoteBody;
        }

        public void setNoteBody(String NoteBody) {
            this.NoteBody = NoteBody;
        }

        public String getCreatetT() {
            return CreatetT;
        }

        public void setCreatetT(String CreatetT) {
            this.CreatetT = CreatetT;
        }
    }
}
