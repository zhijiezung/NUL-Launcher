package com.xhh.launcher.custom.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.tencent.bugly.crashreport.CrashReport;
import com.xhh.launcher.custom.handler.CrashHandler;

/**
 * <p>自定义Application.</p>
 * <p>创建时间: 2018/3/15 0015</p>
 * <br/><p>自定义Application.</p>
 * @author nameh
 */
public class NulApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    /**
     * <p>自定义Application.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>初始化应用创建时的数据</p>
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

        CrashReport.initCrashReport(getApplicationContext(), "33d4a58eb9", true);
    }

    public static Context getContext(){
        return context;
    }

}
