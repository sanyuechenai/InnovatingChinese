package com.yueworld.baseproject.api;


import com.yueworld.baseproject.ui.respBean.InitAppRespBean;
import com.yueworld.baseproject.utils.UIUtils;
import com.yueworld.baseproject.utils.URLutil;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Vincent Chen on 2018/6/4.
 */
public interface ApiService {


    /**
     * 初始化app
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(URLutil.APP_URL)
    Observable<InitAppRespBean> initApp(@Field("cd") String cd, @Field("pm") String pm);


    /**
     * 用户登录
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(URLutil.APP_URL)
    Observable<String> login(@Field("cd") String cd,@Field("ky") String ky, @Field("pm") String pm);

    /**
     * 用户token 登录
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(URLutil.APP_URL)
    Observable<String> tokenLogin(@Field("cd") String cd,@Field("ky") String ky, @Field("pm") String pm);

    /**
     * 用户注册
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(URLutil.APP_URL)
    Observable<String> register(@Field("cd") String cd,@Field("ky") String ky, @Field("pm") String pm);


    /**
     * 获取我的课程列表
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(URLutil.APP_URL)
    Observable<String> getMyCourseList(@Field("cd") String cd,@Field("ky") String ky, @Field("pm") String pm);

    /**
     * 分类列表
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(URLutil.APP_URL)
    Observable<String> getCategoryList(@Field("cd") String cd,@Field("ky") String ky);

    /**
     * 推荐课程列表
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(URLutil.APP_URL)
    Observable<String> getRecommendCourse(@Field("cd") String cd,@Field("ky") String ky,@Field("pm") String pm);


    /**
     * 获取课程等级
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(URLutil.APP_URL)
    Observable<String> getLevelList(@Field("cd") String cd,@Field("ky") String ky);


    /**
     * 获取分类下的课程
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(URLutil.APP_URL)
    Observable<String> getCourseList(@Field("cd") String cd,@Field("ky") String ky,@Field("pm") String pm);


    /**
     * 获取课程的单元
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(URLutil.APP_URL)
    Observable<String> getUnitList(@Field("cd") String cd,@Field("ky") String ky,@Field("pm") String pm);

    /**
     * 添加我的课程
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(URLutil.APP_URL)
    Observable<String> addToMyCourse(@Field("cd") String cd,@Field("ky") String ky,@Field("pm") String pm);

    /**
     * 获取消息
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(URLutil.APP_URL)
    Observable<String> getMessage(@Field("cd") String cd,@Field("ky") String ky,@Field("pm") String pm);

    /**
     * 更新用户信息
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(URLutil.APP_URL)
    Observable<String> updateUserInfo(@Field("cd") String cd,@Field("ky") String ky,@Field("pm") String pm);

    /**
     * 获取单元例句list
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(URLutil.APP_URL)
    Observable<String> getUnitSentenceList(@Field("cd") String cd,@Field("ky") String ky,@Field("pm") String pm);

}
