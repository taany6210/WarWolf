package com.ty.warwolf.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.ty.warwolf.base.App;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @ 文件名:   AppSystemUtil
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午2:34
 * @ 描述:
 */

public class AppSystemUtil {
    private static final String OSVERSION;//系统版本
    private static final String DEVICE_NAME;//设备名称
    private static final String OS = "android";//渠道固定为安卓
    private static final String DEVICE_ID;//设备ID
    private static String versionName;//版本名称
    private static String packageName;//包名
    private static int versionCode;//版本号
    private static String imei;
    private static String mac;


    public AppSystemUtil() {
    }

    public static void initAppSystemData(Context context) {
        PackageManager packageManager = context.getPackageManager();
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        PackageInfo packInfo = null;

        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            WifiInfo info = wifi.getConnectionInfo();
            mac = info.getMacAddress();
            versionName = packInfo.versionName;
            versionCode = packInfo.versionCode;
            packageName = packInfo.packageName;
            imei = manager.getDeviceId();
        } catch (PackageManager.NameNotFoundException var5) {
            var5.printStackTrace();
            versionName = "-1.0.0";
            versionCode = -1;
        }

    }

    /**
     * 获取手机的IMEI号码,设备唯一标识之一
     *
     * @return
     */
    public static String getImei() {
        return imei == null ? "" : imei;
    }

    /**
     * 获取手机的mac地址,设备唯一标识之一
     *
     * @return
     */
    public static String getMac() {
        return mac == null ? "" : mac;
    }

    /**
     * 获取手机的deviceID  唯一标识之一
     *
     * @return
     */
    public static String getDeviceId() {
        return DEVICE_ID == null ? "" : DEVICE_ID;
    }

    /**
     * 获取安卓系统版本号
     *
     * @return
     */
    public static String getOSVERSION() {
        return OSVERSION == null ? "" : OSVERSION;
    }

    /**
     * 获取手机设备名称
     *
     * @return
     */
    public static String getDeviceName() {
        return DEVICE_NAME == null ? "" : DEVICE_NAME;
    }

    /**
     * 固定值 Android
     *
     * @return
     */
    public static String getOS() {
        return OS;
    }

    /**
     * 获取APP的包名
     *
     * @return
     */
    public static String getPackageName() {
        return packageName;
    }

    /**
     * 获取APP版本名称
     *
     * @return
     */
    public static String getVersionName() {
        return versionName;
    }

    /**
     * 获取APP版本号
     *
     * @return
     */
    public static int getVersionCode() {
        return versionCode;
    }


    public static String getUmengChannel() {
        return getMetaData("UMENG_CHANNEL");
    }

    public static String getMetaData(String metaDataKey) {
        try {
            return App.sContext.getPackageManager().getApplicationInfo(App.sContext.getPackageName(),
                    PackageManager.GET_META_DATA).metaData.getString(metaDataKey);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getFromAssets(String fileName) {
        String Result = "";
        try {
            InputStreamReader inputReader = new InputStreamReader(App.sContext.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line;
            while ((line = bufReader.readLine()) != null) {
                Result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result;
    }

    static {
        OSVERSION = Build.VERSION.RELEASE;
        DEVICE_NAME = Build.BRAND + Build.MODEL;
        DEVICE_ID = Build.SERIAL;
    }
}
