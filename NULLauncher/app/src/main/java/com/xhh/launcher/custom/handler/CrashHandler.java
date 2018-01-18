package com.xhh.launcher.custom.handler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.xhh.launcher.custom.activity.app.BugActivity;
import com.xhh.launcher.custom.app.APPManager;
import com.xhh.launcher.custom.util.ExtrasUtil;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * Created by nameh on 2018/1/17 0017.
 */

public class CrashHandler implements UncaughtExceptionHandler {

    private static CrashHandler INSTANCE;
    private Context context;

    private static final String TAG_CRASH = "NULCrash";

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
        Log.e(TAG_CRASH, "NUL启动器崩溃:" + throwable.getMessage());
        Intent intent = new Intent(context, BugActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(ExtrasUtil.EXTRA_BUG_THROWABLE, throwable);
        context.startActivity(intent);
        APPManager.getInstance().exitApp();
    }
}
