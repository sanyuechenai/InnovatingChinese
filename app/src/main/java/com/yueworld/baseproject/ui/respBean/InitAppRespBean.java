package com.yueworld.baseproject.ui.respBean;

/**
 * 创建时间: 2018/9/25.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class InitAppRespBean {


    /**
     * TokenKey : 2134939668
     * TokenDevice : 0d7de03cbdd741ce9695e6b85037204a
     * Date : 2018-09-25 16:46:21
     * Success : 100
     * ReturnString : null
     */

    private long TokenKey;
    private String TokenDevice;
    private String Date;
    private int Success;
    private String ReturnString;

    public long getTokenKey() {
        return TokenKey;
    }

    public void setTokenKey(long TokenKey) {
        this.TokenKey = TokenKey;
    }

    public String getTokenDevice() {
        return TokenDevice;
    }

    public void setTokenDevice(Object TokenDevice) {
        if(TokenDevice!=null){
            this.TokenDevice = (String) TokenDevice;
        }else{
            this.TokenDevice = "";
        }

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
}
