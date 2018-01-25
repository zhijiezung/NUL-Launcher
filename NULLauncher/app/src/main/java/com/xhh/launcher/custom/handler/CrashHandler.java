package com.xhh.launcher.custom.handler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.xhh.launcher.custom.R;
import com.xhh.launcher.custom.activity.app.BugActivity;
import com.xhh.launcher.custom.app.APPManager;
import com.xhh.launcher.custom.util.ExtrasUtil;

import java.lang.Thread.UncaughtExceptionHandler;

public class CrashHandler implements UncaughtExceptionHandler {

    private static CrashHandler INSTANCE;
    private Context mContext;

    private static final String TAG_CRASH = "NULCrash";

    private CrashHandler() {

    }

    public void init(Context context) {
        this.mContext = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public static synchronized CrashHandler getInstance() {
        if (INSTANCE == null) INSTANCE = new CrashHandler();
        return INSTANCE;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        Log.e(TAG_CRASH, mContext.getString(R.string.log_message_nulcrash) + throwable.getMessage());
        Intent intent = new Intent(mContext, BugActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(ExtrasUtil.EXTRA_BUG_THROWABLE, throwable);
        mContext.startActivity(intent);
        APPManager.getInstance().exitApp();
    }
}
