package com.ty.warwolf.model.retrofit;


import com.ty.warwolf.config.AppConst;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ 文件名:   HeaderInterceptor
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午2:59
 * @ 描述:
 */

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                //.addHeader("Content-Type", "application/json")
                .addHeader("key", AppConst.APP_KEY)
                .build();


        Response response = chain.proceed(request);
        return response;
    }
}
