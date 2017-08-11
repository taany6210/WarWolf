package com.ty.warwolf.model.retrofit;

import com.dgrlucky.log.LogX;
import com.google.gson.JsonSyntaxException;
import com.ty.warwolf.base.LoadingPager;

import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * @ 文件名:   TSubscriber
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午3:01
 * @ 描述:
 */

public abstract class TSubscriber<T> extends Subscriber<T> {
    private LoadingPager mPager;

    public TSubscriber(LoadingPager pager) {
        this.mPager = pager;
    }

    @Override
    public void onCompleted() {
        //do nothing
    }

    @Override
    public void onNext(T t) {
        if (t != null) {
            onDataSuccess(t);
        } else {
            onDataError("code 不是1");
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e != null) {
            onDataError(e.getMessage());
        }
        if (e instanceof SocketTimeoutException) {
            LogX.e("请求超时");
        } else if (e instanceof JsonSyntaxException) {
            mPager.onDataLoading(LoadingPager.LoadedResult.ERROR);
            mPager.getErrorBinding().tvError.setText("json解析错误");
        } else if (e instanceof HttpException) {
            mPager.onDataLoading(LoadingPager.LoadedResult.ERROR);
            mPager.getErrorBinding().tvError.setText("网络错误");
        }
        e.printStackTrace();
    }

    public abstract void onDataSuccess(T t);

    public void onDataError(String message) {
        LogX.e(message);
    }
}
