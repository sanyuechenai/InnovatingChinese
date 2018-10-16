package com.yueworld.baseproject.ui.respBean;

import java.io.Serializable;
import java.util.List;

/**
 * 创建时间: 2018/10/11.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class UnitSentenceListResp implements Serializable{


    /**
     * ReturnParams : [{"SedId":"SEN000001","SenName":"例句一","AudId":"AUD000001","AudName":"音频一","AudSrc":"/audio/音频四.mp3","SenGender":"女","SenPassion":0,"UnitSenSeqno":1,"LangId":"EN","TranName":"译文一","SenScore":0},{"SedId":"SEN000002","SenName":"例句二","AudId":"AUD000002","AudName":"音频二","AudSrc":"/audio/音频四.mp3","SenGender":"女","SenPassion":0,"UnitSenSeqno":2,"LangId":"EN","TranName":"译文二","SenScore":0},{"SedId":"SEN000003","SenName":"例句三","AudId":"AUD000003","AudName":"音频三","AudSrc":"/audio/音频四.mp3","SenGender":"女","SenPassion":0,"UnitSenSeqno":3,"LangId":"EN","TranName":"译文三","SenScore":0},{"SedId":"SEN000004","SenName":"例句四","AudId":"AUD000004","AudName":"音频四","AudSrc":"/audio/音频四.mp3","SenGender":"女","SenPassion":0,"UnitSenSeqno":4,"LangId":"EN","TranName":"译文四","SenScore":0},{"SedId":"SEN000005","SenName":"例句五","AudId":"AUD000005","AudName":"音频五","AudSrc":"/audio/音频五.mp3","SenGender":"男","SenPassion":0,"UnitSenSeqno":5,"LangId":"EN","TranName":"译文五","SenScore":0}]
     * Date : 2018-09-24 19:47:33
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

    public static class ReturnParamsBean implements Serializable {
        /**
         * SedId : SEN000001
         * SenName : 例句一
         * AudId : AUD000001
         * AudName : 音频一
         * AudSrc : /audio/音频四.mp3
         * SenGender : 女
         * SenPassion : 0
         * UnitSenSeqno : 1
         * LangId : EN
         * TranName : 译文一
         * SenScore : 0
         */

        private String SedId;
        private String SenName;
        private String AudId;
        private String AudName;
        private String AudSrc;
        private String SenGender;
        private int SenPassion;
        private int UnitSenSeqno;
        private String LangId;
        private String TranName;
        private int SenScore;

        public String getSedId() {
            return SedId;
        }

        public void setSedId(String SedId) {
            this.SedId = SedId;
        }

        public String getSenName() {
            return SenName;
        }

        public void setSenName(String SenName) {
            this.SenName = SenName;
        }

        public String getAudId() {
            return AudId;
        }

        public void setAudId(String AudId) {
            this.AudId = AudId;
        }

        public String getAudName() {
            return AudName;
        }

        public void setAudName(String AudName) {
            this.AudName = AudName;
        }

        public String getAudSrc() {
            return AudSrc;
        }

        public void setAudSrc(String AudSrc) {
            this.AudSrc = AudSrc;
        }

        public String getSenGender() {
            return SenGender;
        }

        public void setSenGender(String SenGender) {
            this.SenGender = SenGender;
        }

        public int getSenPassion() {
            return SenPassion;
        }

        public void setSenPassion(int SenPassion) {
            this.SenPassion = SenPassion;
        }

        public int getUnitSenSeqno() {
            return UnitSenSeqno;
        }

        public void setUnitSenSeqno(int UnitSenSeqno) {
            this.UnitSenSeqno = UnitSenSeqno;
        }

        public String getLangId() {
            return LangId;
        }

        public void setLangId(String LangId) {
            this.LangId = LangId;
        }

        public String getTranName() {
            return TranName;
        }

        public void setTranName(String TranName) {
            this.TranName = TranName;
        }

        public int getSenScore() {
            return SenScore;
        }

        public void setSenScore(int SenScore) {
            this.SenScore = SenScore;
        }
    }
}
