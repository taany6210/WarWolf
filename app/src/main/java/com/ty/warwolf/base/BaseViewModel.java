package com.ty.warwolf.base;

import android.databinding.BaseObservable;
import android.databinding.ViewDataBinding;

import com.dgrlucky.log.LogX;
import com.ty.warwolf.util.NetworkUtil;

import rx.Subscription;

/**
 * @ 文件名:   BaseViewModel
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午2:57
 * @ 描述:
 */

public class BaseViewModel<B extends ViewDataBinding> extends BaseObservable {
    protected LoadingPager mPager;
    protected BaseActivity mActivity;
    protected B mBinding;

    public BaseViewModel(LoadingPager pager, BaseActivity activity, B binding) {
        this.mPager = pager;
        this.mActivity = activity;
        this.mBinding = binding;
        checkNetwork();
    }

    public void checkNetwork() {
        if (!NetworkUtil.isAvailable(App.sContext)) {
            LogX.e("没有网络.....");
            mPager.onDataLoading(LoadingPager.LoadedResult.SUCCESS);
            return;
        }
    }

    /**
     * 添加订阅
     *
     * @param s 需要取消的Subscriptions
     */
    protected void addSubscribe(Subscription s) {
        mActivity.addSubscribe(s);
    }

    /**
     * 获取颜色
     *
     * @param colorId id
     * @return
     */
    protected int getColorRes(int colorId) {
        return mActivity.getColorRes(colorId);
    }



}
