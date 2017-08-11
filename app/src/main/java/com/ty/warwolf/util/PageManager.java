package com.ty.warwolf.util;

import android.app.Activity;
import android.content.Context;

import com.ty.warwolf.base.BaseActivity;

import java.util.Stack;

/**
 * @ 文件名:   PageManager
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午2:30
 * @ 描述:
 */

public class PageManager {
    private static Stack<BaseActivity> pageStack = new Stack<>();

    /**
     * 获取最后一个入栈的activity
     *
     * @return
     */
    public static BaseActivity getCurrentActivity() {
        return pageStack.lastElement();
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = pageStack.lastElement();
        removePage(activity);
    }

    /**
     * 移除页面对象
     *
     * @param activity 页面对象
     */
    public static void removePage(Activity activity) {
        if (activity != null) {
            pageStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 添加新页面
     *
     * @param activity 页面对象
     */
    public static void addPage(BaseActivity activity) {
        if (!pageStack.contains(activity)) {
            pageStack.add(activity);
        }
    }

    /**
     * 页面清理
     */
    public static void clearPage() {
        int size = pageStack.size();
        for (int i = 0; i < size; i++) {
            pageStack.get(i).finish();
        }
        pageStack.clear();
    }

    /**
     * 退出应用程序
     */
    public static void AppExit(Context context) {
        try {
            clearPage();
            //退出程序
            ((Activity) context).onBackPressed();
        } catch (Exception e) {
        }
    }
}
