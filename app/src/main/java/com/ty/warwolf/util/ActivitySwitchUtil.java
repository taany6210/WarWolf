package com.ty.warwolf.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ty.warwolf.base.BaseActivity;


/**
 * @ 文件名:   ActivitySwitchUtil
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午2:31
 * @ 描述:
 */

public class ActivitySwitchUtil {
    /**
     * activity切换（不携带数据）
     *
     * @param targetActivity 目标activity
     */
    public static void switchActivity(Class<? extends AppCompatActivity> targetActivity) {
        switchActivity(targetActivity, null);
    }

    /**
     * activity切换（携带数据）
     *
     * @param targetActivity 目标activity
     * @param bundle         携带的数据
     */
    public static void switchActivity(Class<? extends AppCompatActivity> targetActivity, Bundle bundle) {
        Intent intent = new Intent(PageManager.getCurrentActivity(), targetActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        PageManager.getCurrentActivity().startActivity(intent);
    }


    /**
     * activity切换并获取结果（不携带数据）
     *
     * @param targetActivity 目标activity
     * @param requestCode    resultCode
     */
    public static void switchActivityForResult(Class<? extends BaseActivity> targetActivity, int requestCode) {
        switchActivityForResult(targetActivity, null, requestCode);
    }

    /**
     * activity切换并获取结果（携带数据）
     *
     * @param targetActivity 目标activity
     * @param bundle         携带的数据
     * @param requestCode    resultCode
     */
    public static void switchActivityForResult(Class<? extends BaseActivity> targetActivity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(PageManager.getCurrentActivity(), targetActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        PageManager.getCurrentActivity().startActivityForResult(intent, requestCode);
    }
}
