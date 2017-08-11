package com.ty.warwolf.model.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ty.warwolf.BuildConfig;
import com.ty.warwolf.base.App;
import com.ty.warwolf.model.api.ApiService;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ 文件名:   RetrofitFactory
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午3:00
 * @ 描述:    生成网络请求
 */

public class RetrofitFactory {

    public static String sUrl;
    private static OkHttpClient sOkHttpClient;
    private static File cacheFile = new File(App.getContext().getCacheDir(), "cache");
    private static Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
    private static final String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final Gson sGson = new GsonBuilder()
            .setDateFormat(pattern)
            .create();

    static {
        sUrl = BuildConfig.DEBUG ? BuildConfig.host : BuildConfig.host;
        sOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(1000, TimeUnit.MILLISECONDS)
                .connectTimeout(1000, TimeUnit.MILLISECONDS)
                .addInterceptor(new LogInterceptor())
                //.addInterceptor(new JsonInterceptor())
                .addInterceptor(new HeaderInterceptor())
                //.addInterceptor(new SignInterceptor())
                .cache(cache)
                .build();
    }

    public static ApiService sApiService = new Retrofit.Builder()
            .baseUrl(sUrl)
            .addConverterFactory(GsonConverterFactory.create(sGson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(sOkHttpClient)
            .build()
            .create(ApiService.class);

}
