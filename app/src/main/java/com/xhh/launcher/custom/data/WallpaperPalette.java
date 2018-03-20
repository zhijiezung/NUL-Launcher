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

    private int paletteStatus;
    private int paletteNavigation;

    public int getPaletteStatus() {
        return paletteStatus;
    }

    public void setPaletteStatus(int paletteStatus) {
        this.paletteStatus = paletteStatus;
    }

    public int getPaletteNavigation() {
        return paletteNavigation;
    }

    public void setPaletteNavigation(int paletteNavigation) {
        this.paletteNavigation = paletteNavigation;
    }
}
