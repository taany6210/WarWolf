package com.ty.warwolf.listener;

/**
 * @ 文件名:   PtrRefreshListener
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午2:32
 * @ 描述:
 */

public interface PtrRefreshListener {
    /**
     * 下拉刷新回位
     */
    void onPullReset();

    /**
     * 下拉刷新有拉动距离
     */
    void onPullStart();
}
