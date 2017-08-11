package com.ty.warwolf.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * @ 文件名:   App
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 上午10:34
 * @ 描述:
 */

public class App extends Application {

    public static Context sContext;
    private static Handler mHandler;
    private static long mMainThreadId;
    private static Thread mMainThread;

    public static Handler getHandler() {
        return mHandler;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        mHandler = new Handler();
        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
    }
}
