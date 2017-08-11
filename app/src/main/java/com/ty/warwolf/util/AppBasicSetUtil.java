package com.ty.warwolf.util;

import android.app.Activity;

import com.ty.warwolf.view.TRefreshGif;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * @ 文件名:   AppBasicSetUtil
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午2:31
 * @ 描述:
 */

public class AppBasicSetUtil {
    /**
     * 自定义Gif下拉刷新header
     *
     * @param ptrLayout
     * @param activity
     */
    public static TRefreshGif setGifHeader(PtrClassicFrameLayout ptrLayout, Activity activity) {
        /* 创建自定义刷新头部view */
        //MakeupHeaderView header = new MakeupHeaderView(activity);
        TRefreshGif header = new TRefreshGif(activity);
        /* 设置刷新头部view */
        ptrLayout.setHeaderView(header);
        /* 设置回调 */
        ptrLayout.addPtrUIHandler(header);
        ptrLayout.setLoadingMinTime(2000);

        return header;
    }

    /**
     * 正常模式的下拉刷新
     *
     * @param ptrLayout
     * @param activity
     * @return
     */
    public static TRefreshGif setRefreshHeader(PtrClassicFrameLayout ptrLayout, Activity activity) {
        /* 创建自定义刷新头部view */
        //MakeupHeaderView header = new MakeupHeaderView(activity);
        TRefreshGif header = new TRefreshGif(activity);
        /* 设置刷新头部view */
        ptrLayout.setHeaderView(header);
        /* 设置回调 */
        ptrLayout.addPtrUIHandler(header);
        ptrLayout.setLoadingMinTime(2000);

        return header;
    }
}
