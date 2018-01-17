package com.xhh.launcher.custom.app;

import android.app.Application;

import com.xhh.launcher.custom.handler.CrashHandler;

/**
 * Created by nameh on 2018/1/17 0017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
