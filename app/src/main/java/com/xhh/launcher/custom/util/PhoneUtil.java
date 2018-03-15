package com.xhh.launcher.custom.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * <p>获取手机信息.</p>
 * <p>创建时间: 18-1-25</p>
 *
 * @author xhh
 */
public class PhoneUtil {

    /**
     * <p>获取手机品牌.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>获取手机品牌.</p>
     *
     * @return String 返回手机品牌
     */

    public static String getBrand() {
        return Build.BRAND;
    }

    /**
     * <p>获取手机型号.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>获取手机型号.</p>
     *
     * @return String 返回手机型号
     */
    public static String getModel() {
        return Build.MODEL;
    }

    /**
     * <p>获取手机名称.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>获取手机名称.</p>
     *
     * @return String 返回手机名称
     */
    public static String getProduct() {
        return Build.PRODUCT;
    }

    /**
     * <p>获取安卓版本.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>获取安卓版本.</p>
     *
     * @return String 返回安卓版本
     */
    public static String getAndroidVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * <p>获取软件版本.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>获取软件版本.</p>
     *
     * @return String 返回软件版本
     */
    public static String getAppVersion(Context context) {
        String result = "null";
        PackageManager packageManager = context.getPackageManager();
        try {
            result = packageManager.getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}