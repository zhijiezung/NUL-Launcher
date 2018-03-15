package com.xhh.launcher.custom.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * <p>权限工具类.</p>
 * <p>创建时间: 2018/3/15 0015</p>
 * <br/><p>动态申请权限</p>
 * @author nameh
 */

public class PermissionUtil {


    /**
     * 储存读写权限
     */
    public static final String[] PERMISSION_STORAGE=new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    /**
     * <p>注册权限.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>先判断有没有授权</p>
     * @param activity 注册的Activity
     * @param permissions 需要注册的权限
     * @param code 注册时的code
     * @return boolean true为已经注册
     */

    public static boolean request(Activity activity,String[] permissions,int code){
        if(!checkPermissions(activity,permissions)){
            requestPermission(activity,permissions,code);
            return false;
        }else {
            return true;
        }
    }

    /**
     * <p>权限申请.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>权限申请</p>
     * @param activity 申请权限的activity
     * @param permissions 需要申请的权限
     * @param code 返回的code
     */

    public static void requestPermission(Activity activity, String[] permissions,int code){
        ActivityCompat.requestPermissions(activity,permissions,code);
    }

    /**
     * <p>检查多个权限.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>检查权限 </p>
     * @param permissions 多个权限
     * @return boolean true为全部授权
     */

    public static boolean checkPermissions(Activity activity,String[] permissions){
        for(String permission:permissions){
            if(!checkPermission(activity,permission)){
                return false;
            }
        }
        return true;
    }

    /**
     * <p>检查是否注册成功.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>检查是否注册成功</p>
     * @param grantResults 授权值
     * @return boolean true为成功
     */

    public static boolean checkPermissions(int[] grantResults){
        for(int gr:grantResults){
            if(gr==PackageManager.PERMISSION_DENIED){
                return false;
            }
        }
        return true;
    }

    /**
     * <p>检查单个权限.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>检查单个权限.</p>
     * @param permission 单个权限
     * @return boolean true为授权
     */

    public static boolean checkPermission(Activity activity,String permission){
        return ContextCompat.checkSelfPermission(activity,permission)== PackageManager.PERMISSION_GRANTED;
    }


}
