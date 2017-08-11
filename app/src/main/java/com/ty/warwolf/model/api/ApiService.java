package com.ty.warwolf.model.api;

import com.ty.warwolf.model.bean.base.Reply;
import com.ty.warwolf.model.bean.base.Today;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @ 文件名:   ApiService
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午2:59
 * @ 描述:
 */

public interface ApiService {

    @GET("japi/toh")
    Observable<Reply<List<Today>>> getHistory(@Query("key")String key, @Query("month")String month, @Query("day")String day);

}
