package com.yueworld.baseproject.ui.respBean;

import java.io.Serializable;

/**
 * 创建时间: 2018/9/26.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class UserBean implements Serializable{


    /**
     * UserId : testid1
     * UserName : user_name
     * Pwd : null
     * Pwcount : 0
     * Nickname : 超人
     * UserSource : user_source
     * UserEmail : testid1@aliyun.com
     * UserEmailCheck : N
     * UserEmailCheckT : 0001-01-01T00:00:00
     * UserPhone1 : 15801856069
     * UserPhone2 : 15801856069
     * UserGender : 女
     * UserAge : 18
     * UserSign : user_sign
     * UserProfile : user_profile
     * UserType : VIP
     * Islock : N
     * LockT : 0001-01-01T00:00:00
     * LockReason : lock_reason
     * LangId : null
     * Createby : null
     * Modifyby : null
     * CreateT : 0001-01-01T00:00:00
     * ModifyT : 0001-01-01T00:00:00
     * UserToken : null
     * Date : 2018-09-26 11:51:22
     * Success : 100
     * ReturnString : null
     */

    private String UserId;
    private String UserName;
    private String Pwd;
    private int Pwcount;
    private String Nickname;
    private String UserSource;
    private String UserEmail;
    private String UserEmailCheck;
    private String UserEmailCheckT;
    private String UserPhone1;
    private String UserPhone2;
    private String UserGender;
    private int UserAge;
    private String UserSign;
    private String UserProfile;
    private String UserType;
    private String Islock;
    private String LockT;
    private String LockReason;
    private String LangId;
    private String UserToken;
    private String Date;
    private int Success;
    private String ReturnString;

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

    public String getUserName() {
        return UserName;
    }

    public void setUserName(Object UserName) {
        if(UserName!=null){
            this.UserName = (String) UserName;
        }else{
            this.UserName = "";
        }

    }

    public Object getPwd() {
        return Pwd;
    }

    public void setPwd(Object Pwd) {
        if(Pwd!=null){
            this.Pwd = (String) Pwd;
        }else{
            this.Pwd = "";
        }

    }

    public int getPwcount() {
        return Pwcount;
    }

    public void setPwcount(int Pwcount) {
        this.Pwcount = Pwcount;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(Object Nickname) {
        if(Nickname!=null){
            this.Nickname = (String) Nickname;
        }else{
            this.Nickname = "";
        }

    }

    public String getUserSource() {
        return UserSource;
    }

    public void setUserSource(Object UserSource) {
        if(UserSource!=null){
            this.UserSource = (String) UserSource;
        }else{
            this.UserSource = "";
        }

    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(Object UserEmail) {
        if(UserEmail!=null){
            this.UserEmail = (String) UserEmail;
        }else{
            this.UserEmail = "";
        }

    }

    public String getUserEmailCheck() {
        return UserEmailCheck;
    }

    public void setUserEmailCheck(Object UserEmailCheck) {
        if(UserEmailCheck!=null){
            this.UserEmailCheck = (String) UserEmailCheck;
        }else{
            this.UserEmailCheck = "";
        }

    }

    public String getUserEmailCheckT() {
        return UserEmailCheckT;
    }

    public void setUserEmailCheckT(Object UserEmailCheckT) {
        if(UserEmailCheckT!=null){
            this.UserEmailCheckT = (String) UserEmailCheckT;
        }else{
            this.UserEmailCheckT = "";
        }

    }

    public String getUserPhone1() {
        return UserPhone1;
    }

    public void setUserPhone1(Object UserPhone1) {
        if(UserPhone1!=null){
            this.UserPhone1 = (String) UserPhone1;
        }else{
            this.UserPhone1 = "";
        }

    }

    public String getUserPhone2() {
        return UserPhone2;
    }

    public void setUserPhone2(Object UserPhone2) {
        if(UserPhone2!=null){
            this.UserPhone2 = (String) UserPhone2;
        }else{
            this.UserPhone2 = "";
        }

    }

    public String getUserGender() {
        return UserGender;
    }

    public void setUserGender(Object UserGender) {
        if(UserGender!=null){
            this.UserGender = (String) UserGender;
        }else{
            this.UserGender = "";
        }

    }

    public int getUserAge() {
        return UserAge;
    }

    public void setUserAge(int UserAge) {
        this.UserAge = UserAge;
    }

    public String getUserSign() {
        return UserSign;
    }

    public void setUserSign(Object UserSign) {
        if(UserSign!=null){
            this.UserSign = (String) UserSign;
        }else{
            this.UserSign = "";
        }

    }

    public String getUserProfile() {
        return UserProfile;
    }

    public void setUserProfile(Object UserProfile) {
        if(UserProfile!=null){
            this.UserProfile = (String) UserProfile;
        }else{
            this.UserProfile = "";
        }

    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(Object UserType) {
        if(UserType!=null){
            this.UserType = (String) UserType;
        }else{
            this.UserType = "";
        }

    }

    public String getIslock() {
        return Islock;
    }

    public void setIslock(Object Islock) {
        if(Islock!=null){
            this.Islock = (String) Islock;
        }else{
            this.Islock = "";
        }

    }

    public String getLockT() {
        return LockT;
    }

    public void setLockT(Object LockT) {
        if(LockT!=null){
            this.LockT = (String) LockT;
        }else{
            this.LockT = "";
        }

    }

    public String getLockReason() {
        return LockReason;
    }

    public void setLockReason(Object LockReason) {
        if(LockReason!=null){
            this.LockReason = (String) LockReason;
        }else{
            this.LockReason = "";
        }

    }

    public String getLangId() {
        return LangId;
    }

    public void setLangId(Object LangId) {
        if(LangId!=null){
            this.LangId = (String) LangId;
        }else{
            this.LangId = "";
        }

    }

    public String getUserToken() {
        return UserToken;
    }

    public void setUserToken(Object UserToken) {
        if(UserToken!=null){
            this.UserToken = (String) UserToken;
        }else{
            this.UserToken = "";
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
