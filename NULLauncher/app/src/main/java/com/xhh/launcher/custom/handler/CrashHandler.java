package com.xhh.launcher.custom.handler;

import android.content.Context;

import com.xhh.launcher.custom.app.ActivityManager;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * Created by nameh on 2018/1/17 0017.
 */

public class CrashHandler implements UncaughtExceptionHandler {

    private static CrashHandler INSTANCE;
    private Context context;

    public CrashHandler() {

    }

    public void init(Context context) {
        this.context = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public static CrashHandler getInstance() {
        if (INSTANCE == null) INSTANCE = new CrashHandler();
        return INSTANCE;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        ActivityManager.getInstance().exitApp();
    }
}
