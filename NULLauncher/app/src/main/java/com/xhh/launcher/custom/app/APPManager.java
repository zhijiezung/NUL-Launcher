package com.xhh.launcher.custom.app;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Activity管理器
 * <p>date: 18-1-25</p>
 *
 * @author xhh
 */
public class APPManager {

    private static List<Activity> mActivities = new LinkedList<>();
    private static APPManager INSTANCE;

    /**
     * 确保不会被外部实例化
     * <p>date: 18-1-25</p>
     *
     */
    private APPManager() {

    }

    /**
     * 获取实例
     * <p>date: 18-1-25</p>
     *
     */
    public static synchronized APPManager getInstance() {
        if (INSTANCE == null) INSTANCE = new APPManager();
        return INSTANCE;
    }

    /**
     * 添加一个activity
     * <p>date: 18-1-25</p>
     *
     */
    public void addActivity(Activity activity) {
        this.mActivities.add(activity);
    }

    /**
     * 移除一个activity
     * <p>date: 18-1-25</p>
     *
     */
    public void removeActivity(Activity activity) {
        this.mActivities.remove(activity);
    }

    /**
     * 结束所有activity
     * <p>date: 18-1-25</p>
     *
     */
    public void finishAllActivity() {
        if (mActivities != null) {
            for (int i = 0; i < mActivities.size(); i++) {
                if (mActivities.get(i) == null) continue;
                mActivities.get(i).finish();
            }
            mActivities.clear();
        }
    }

    /**
     * 结束所有activity并且杀死当前进程
     * <p>date: 18-1-25</p>
     *
     */
    public void exitApp() {
        finishAllActivity();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
