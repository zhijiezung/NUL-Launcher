package com.xhh.launcher.custom.handler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.xhh.launcher.custom.R;
import com.xhh.launcher.custom.activity.app.BugActivity;
import com.xhh.launcher.custom.app.APPManager;
import com.xhh.launcher.custom.util.ExtrasUtil;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 崩溃处理
 * <p>date: 18-1-25</p>
 *
 * @author xhh
 */
public class CrashHandler implements UncaughtExceptionHandler {

    private static CrashHandler INSTANCE;
    private Context mContext;

    private static final String TAG_CRASH = "NULCrash";

    /**
     * 确保不会被外部实例化
     * <p>date: 18-1-25</p>
     *
     */
    private CrashHandler() {

    }

    /**
     * 初始化设置默认异常捕获程序
     * <p>date: 18-1-25</p>
     *
     * @param context
     *        ApplicationContext
     * @return
     */
    public void init(Context context) {
        this.mContext = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 获取实例
     * <p>date: 18-1-25</p>
     *
     */
    public static synchronized CrashHandler getInstance() {
        if (INSTANCE == null) INSTANCE = new CrashHandler();
        return INSTANCE;
    }

    /**
     * 处理捕获的异常
     * <p>date: 18-1-25</p>
     * 
     * @param thread
     *        出错线程
     * @param throwable
     *        抛出的异常
     */
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
