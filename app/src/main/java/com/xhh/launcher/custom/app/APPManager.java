package com.xhh.launcher.custom.app;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>Activity管理器.</p>
 * <p>创建时间: 18-1-25</p>
 * <br/><p>Activity管理作用</p>
 * @author xhh
 */
public class APPManager {

    private List<Activity> mActivities = new LinkedList<>();
    private static APPManager INSTANCE;

    /**
     * <p>确保不会被外部实例化.</p>
     * <p>创建时间: 18-1-25</p>
     *
     */
    private APPManager() {

    }

    /**
     * <p>获取实例.</p>
     * <p>创建时间: 18-1-25</p>
     * <br/><p>单例模式</p>
     */
    public static synchronized APPManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new APPManager();
        }
        return INSTANCE;
    }

    /**
     * <p>添加一个activity.</p>
     * <p>创建时间: 18-1-25</p>
     *
     */
    public void addActivity(Activity activity) {
        this.mActivities.add(activity);
    }

    /**
     * <p>移除一个activity.</p>
     * <p>创建时间: 18-1-25</p>
     *
     */
    public void removeActivity(Activity activity) {
        this.mActivities.remove(activity);
    }

    /**
     * <p>结束所有activity.</p>
     * <p>创建时间: 18-1-25</p>
     *
     */
    public void finishAllActivity() {
        if (mActivities != null) {
            for (int i = 0; i < mActivities.size(); i++) {
                if (mActivities.get(i) == null) {
                    continue;
                }
                mActivities.get(i).finish();
            }
            mActivities.clear();
        }
    }

    /**
     * <p>结束所有activity并且杀死当前进程.</p>
     * <p>创建时间: 18-1-25</p>
     *
     */
    public void exitApp() {
        finishAllActivity();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
