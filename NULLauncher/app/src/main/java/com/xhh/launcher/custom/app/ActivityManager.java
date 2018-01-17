package com.xhh.launcher.custom.app;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nameh on 2018/1/17 0017.
 */

public class ActivityManager {

    private List<Activity> activities = new LinkedList<>();
    private static ActivityManager INSTANCE;

    public ActivityManager() {

    }

    public static ActivityManager getInstance() {
        if (INSTANCE == null) INSTANCE = new ActivityManager();
        return INSTANCE;
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    public void finishAllActivity() {
        if (activities != null) {
            for (Activity activity : activities) {
                activity.finish();
            }
            activities.clear();
        }
    }

    public void exitApp(){
        finishAllActivity();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
