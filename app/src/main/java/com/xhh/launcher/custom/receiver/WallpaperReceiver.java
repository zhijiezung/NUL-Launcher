package com.xhh.launcher.custom.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.xhh.launcher.custom.service.WallpaperColorService;

/**
 * <p>壁纸改变广播接收器.</p>
 * <p>创建时间: 2018/3/20 0020</p>
 * <br/><p>壁纸改变广播接收器</p>
 * @author nameh
 */

public class WallpaperReceiver extends BroadcastReceiver {

    /**
     * <p>壁纸改变触发.</p>
     * <p>创建时间: 2018/3/20 0020</p>
     * <br/><p>壁纸改变触发</p>
     */

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent ser=new Intent(context, WallpaperColorService.class);
        context.startService(ser);
    }
}
