package com.xhh.launcher.custom.model;

import android.graphics.drawable.Drawable;

/**
 *
 * @author nameh
 * @date 2018/3/21 0021
 */

public class AppWidgetInfo {
    private String name;
    private Drawable icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
