package com.ty.warwolf.util;

import android.os.Handler;

import com.ty.warwolf.base.App;
import com.ty.warwolf.config.Const;


/**
 * @ 文件名:   UIUtil
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午2:25
 * @ 描述:
 */
public class UIUtil {

    /**
     * 保存token
     *
     * @param token
     */
    public static void setToken(String token) {
        SPUtil.setString(Const.TOKEN, token);
    }

    /**
     * 获取token
     *
     * @return
     */
    public static String getToken() {
        return SPUtil.getString(Const.TOKEN, null);
    }

    /**
     * 是否登陆
     *
     * @return
     */
    public static boolean isLogin() {
        return !(getToken() == null);
    }

    /**
     * 保存用户UID
     *
     * @param uid
     */
    public static void setUid(String uid) {
        SPUtil.setString(Const.USER_UID, uid);
    }

    /**
     * 获取用户UID
     *
     * @return
     */
    public static String getUid() {
        return SPUtil.getString(Const.USER_UID);
    }

    /**
     * 获取主线程
     *
     * @return
     */
    public static Thread getMainThread() {
        return App.getMainThread();
    }

    /**
     * 获取主线程id
     *
     * @return
     */
    public static long getMainThreadId() {
        return App.getMainThreadId();
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getHandler() {
        return App.getHandler();
    }

    /**
     * 延时在主线程执行runnable
     */
    public static boolean postDelayed(Runnable runnable, long delayMillis) {
        return getHandler().postDelayed(runnable, delayMillis);
    }

    /**
     * 在主线程执行runnable
     */
    public static boolean post(Runnable runnable) {
        return getHandler().post(runnable);
    }

    /**
     * 从主线程looper里面移除runnable
     */
    public static void removeCallbacks(Runnable runnable) {
        getHandler().removeCallbacks(runnable);
    }

    /**
     * 判断当前的线程是不是在主线程
     */
    public static boolean isRunInMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    public static void runInMainThread(Runnable runnable) {
        // 在主线程运行
        if (isRunInMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }
}
