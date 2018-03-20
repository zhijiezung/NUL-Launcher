package com.xhh.launcher.custom.data;

import android.support.v7.graphics.Palette;

import java.io.Serializable;

/**
 * <p>壁纸palette数据类.</p>
 * <p>创建时间: 2018/3/20 0020</p>
 * <br/><p>壁纸palette数据类</p>
 * @author nameh
 */

public class WallpaperPalette implements Serializable{

   private boolean isStatusLight;
   private boolean isNavigationLight;

    public boolean isStatusLight() {
        return isStatusLight;
    }

    public void setStatusLight(boolean statusLight) {
        isStatusLight = statusLight;
    }

    public boolean isNavigationLight() {
        return isNavigationLight;
    }

    public void setNavigationLight(boolean navigationLight) {
        isNavigationLight = navigationLight;
    }
}
