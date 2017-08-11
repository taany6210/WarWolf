package com.ty.warwolf.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.ty.warwolf.base.App;


/**
 * @ 文件名:   SPUtil
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午2:40
 * @ 描述:
 */

public class SPUtil {
    private final static String SP_NAME = "Const";
    private static SharedPreferences mSp = App.sContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);

    private static SharedPreferences getSp() {
        return mSp;
    }

    /**
     * 获取boolean类型的值
     * @param key      对应的键
     * @param defValue 如果没有对应的值，
     * @return
     */
    public static boolean getBoolean(String key, boolean defValue) {
        SharedPreferences sp = getSp();
        return sp.getBoolean(key, defValue);
    }

    /**
     * 获取boolean类型的值,如果没有对应的值，默认值返回false
     * @param key     对应的键
     * @return
     */
    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * 设置boolean类型的值
     * @param key
     * @param value
     */
    public static void setBoolean(String key, boolean value) {
        SharedPreferences sp = getSp();
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 获取String类型的值
     * @param key      对应的键
     * @param defValue 如果没有对应的值，
     * @return
     */
    public static String getString(String key, String defValue) {
        SharedPreferences sp = getSp();
        return sp.getString(key, defValue);
    }

    /**
     * 获取String类型的值,如果没有对应的值，默认值返回null
     * @param key     对应的键
     * @return
     */
    public static String getString(String key) {
        return getString(key, null);
    }

    /**
     * 设置String类型的值
     * @param key
     * @param value
     */
    public static void setString(String key, String value) {
        SharedPreferences sp = getSp();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 获取long类型的值
     * @param key      对应的键
     * @param defValue 如果没有对应的值，
     * @return
     */
    public static long getLong(String key, long defValue) {
        SharedPreferences sp = getSp();
        return sp.getLong(key, defValue);
    }

    /**
     * 获取long类型的值,如果没有对应的值，默认值返回0
     * @param key     对应的键
     * @return
     */
    public static long getLong( String key) {
        return getLong(key, 0);
    }

    /**
     * 设置Long类型的值
     * @param key
     * @param value
     */
    public static void setLong(String key, long value) {
        SharedPreferences sp = getSp();
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.commit();
    }


    /**
     * 获取float类型的值
     * @param key      对应的键
     * @param defValue 如果没有对应的值，
     * @return
     */
    public static float getFloat(String key, float defValue) {
        SharedPreferences sp = getSp();
        return sp.getFloat(key, defValue);
    }

    /**
     * 获取float类型的值,如果没有对应的值，默认值返回0
     * @param key     对应的键
     * @return
     */
    public static float getFloat( String key) {
        return getFloat(key, 0);
    }

    /**
     * 设置float类型的值
     * @param key
     * @param value
     */
    public static void setFloat(String key, float value) {
        SharedPreferences sp = getSp();
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, value);
        editor.commit();
    }


    /**
     * 获取int类型的值
     * @param key      对应的键
     * @param defValue 如果没有对应的值，
     * @return
     */
    public static int getInt(String key, int defValue) {
        SharedPreferences sp = getSp();
        return sp.getInt(key, defValue);
    }

    /**
     * 获取int类型的值,如果没有对应的值，默认值返回false
     * @param key     对应的键
     * @return
     */
    public static int getInt(String key) {
        return getInt(key, 0);
    }

    /**
     * 设置int类型的值
     * @param key
     * @param value
     */
    public static void setInt(String key, int value) {
        SharedPreferences sp = getSp();
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }
}
