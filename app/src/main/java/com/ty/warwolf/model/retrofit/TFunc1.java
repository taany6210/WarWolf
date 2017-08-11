package com.ty.warwolf.model.retrofit;

import com.dgrlucky.log.LogX;
import com.ty.warwolf.base.LoadingPager;
import com.ty.warwolf.model.bean.base.Reply;

import rx.functions.Func1;

/**
 * @ 文件名:   TFunc1
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午3:01
 * @ 描述:
 */

public class TFunc1<T> implements Func1<Reply<T>, T> {

    private LoadingPager mPager;

    public TFunc1(LoadingPager pager) {
        this.mPager = pager;
    }

    @Override
    public T call(Reply<T> tReply) {
        int error_code = tReply.getError_code();
        if (error_code == 0) {
            LogX.e("请求成功: ");
        }else if (error_code==101){
            LogX.e("app_key错误");
        } else if (error_code == 0) {
            LogX.e("数据获取失败: " + tReply.getReason());
            mPager.onDataLoading(LoadingPager.LoadedResult.ERROR);
            mPager.getErrorBinding().tvError.setText(tReply.getReason());
        } else {
            LogX.e("其他错误: " + tReply.getReason());
        }

        return tReply.result;
    }
}
