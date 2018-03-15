package com.xhh.launcher.custom.app;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;
import com.xhh.launcher.custom.handler.CrashHandler;

import java.util.Map;

/**
 * <p>自定义Application.</p>
 * <p>创建时间: 2018/3/15 0015</p>
 * <br/><p>自定义Application.</p>
 * @author nameh
 */
public class NULApplication extends Application {
   
    /**
     * <p>自定义Application.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>初始化应用创建时的数据</p>
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

        CrashReport.initCrashReport(getApplicationContext(), "33d4a58eb9", true);
    }
}
