package com.ty.warwolf.model.api;

import com.ty.warwolf.model.bean.base.Reply;
import com.ty.warwolf.model.bean.base.Today;
import com.ty.warwolf.model.bean.base.User;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @ 文件名:   ApiService
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午2:59
 * @ 描述: API类
 */

public interface ApiService {

    /**
     * 聚合数据获取历史上的今天
     *
     * @param key   申请的key
     * @param month 月份
     * @param day   日期
     * @return
     */
    @GET("japi/toh")
    Observable<Reply<List<Today>>> getHistory(@Query("key") String key, @Query("month") String month, @Query("day") String day);

    /**
     * 手机登录
     *
     * @param phone   手机号码
     * @param pssWord 登录密码
     * @return
     */
    @FormUrlEncoded
    @POST("User/login")
    Observable<Reply<User>> login(@Field("phone") String phone, @Field("password") String pssWord);

}
