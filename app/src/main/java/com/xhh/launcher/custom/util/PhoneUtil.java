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
     * 传输的Context
     */
    private Context mContext;

    public PhoneUtil(Context context) {
        this.mContext = context;
    }

    /**
     * <p>获取手机品牌.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>获取手机品牌.</p>
     *
     * @return String 返回手机品牌
     */

    public String getBrand() {
        return Build.BRAND;
    }

    /**
     * <p>获取手机型号.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>获取手机型号.</p>
     *
     * @return String 返回手机型号
     */
    public String getModel() {
        return Build.MODEL;
    }

    /**
     * <p>获取手机名称.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>获取手机名称.</p>
     *
     * @return String 返回手机名称
     */
    public String getProduct() {
        return Build.PRODUCT;
    }

    /**
     * <p>获取安卓版本.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>获取安卓版本.</p>
     *
     * @return String 返回安卓版本
     */
    public String getAndroidVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * <p>获取软件版本.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>获取软件版本.</p>
     *
     * @return String 返回软件版本
     */
    public String getAppVersion() {
        String result = "null";
        PackageManager packageManager = mContext.getPackageManager();
        try {
            result = packageManager.getPackageInfo(mContext.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}