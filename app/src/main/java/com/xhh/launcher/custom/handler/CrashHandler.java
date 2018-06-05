package com.xhh.launcher.custom.handler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.xhh.launcher.custom.R;
import com.xhh.launcher.custom.activity.app.BugActivity;
import com.xhh.launcher.custom.app.AppManager;
import com.xhh.launcher.custom.util.ExtrasUtil;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * <p>崩溃处理.</p>
 * <p>创建时间: 18-1-25</p>
 *
 * @author xhh
 */
public class CrashHandler implements UncaughtExceptionHandler {

    /**
     * 单例
     */
    @SuppressLint("StaticFieldLeak")
    private static CrashHandler INSTANCE;
    /**
     * ApplicationContext
     */
    private Context mContext;
    /**
     * Crash标签
     */
    private static final String TAG_CRASH = "NULCrash";

    /**
     * <p>确保不会被外部实例化.</p>
     * <p>创建时间: 18-1-25</p>
     *
     */
    private CrashHandler() {

    }

    /**
     * <p>初始化设置默认异常捕获程序.</p>
     * <p>创建时间: 18-1-25</p>
     *
     * @param context ApplicationContext
     */
    public void init(Context context) {
        this.mContext = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * <p>获取实例.</p>
     * <p>创建时间: 18-1-25</p>
     *
     */
    public static synchronized CrashHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CrashHandler();
        }
        return INSTANCE;
    }

    /**
     * <p>处理捕获的异常.</p>
     * <p>创建时间: 18-1-25</p>
     * 
     * @param thread 出错线程
     * @param throwable 抛出的异常
     */
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        Log.e(TAG_CRASH, mContext.getString(R.string.log_message_nulcrash) + throwable.getMessage());
        Intent intent = new Intent(mContext, BugActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(ExtrasUtil.EXTRA_BUG_THROWABLE, throwable);
        mContext.startActivity(intent);
        AppManager.getInstance().exitApp();
    }
}
