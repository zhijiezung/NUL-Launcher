package com.xhh.launcher.custom.util;

import android.content.Context;

/**
 * <p>屏幕工具类.</p>
 * <p>创建时间: 2018/3/20 0020</p>
 * <br/><p>屏幕工具类</p>
 * @author nameh
 */

public class ScreenUtil {

    /**
     * <p>获取状态栏高度.</p>
     * <p>创建时间: 2018/3/20 0020</p>
     * <br/><p>获取状态栏高度</p>
     * @param context 传入Context对象
     * @return int 返回状态栏高度
     */

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * <p>获取导航栏高度.</p>
     * <p>创建时间: 2018/3/20 0020</p>
     * <br/><p>获取导航栏高度</p>
     * @param context 传入Context对象
     * @return int 返回导航栏高度
     */

    public static int getNavigationHeight(Context context) {
        int result = 0;
        int resourceId=0;
        int rid = context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        if (rid!=0){
            resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");return context.getResources().getDimensionPixelSize(resourceId);
        }else {
            return 0;
        }
    }
}
