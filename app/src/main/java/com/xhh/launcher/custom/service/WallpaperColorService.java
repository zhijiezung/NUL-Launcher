package com.xhh.launcher.custom.service;

import android.app.IntentService;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.util.Log;

import com.xhh.launcher.custom.activity.launcher.LauncherActivity;
import com.xhh.launcher.custom.data.WallpaperPalette;
import com.xhh.launcher.custom.util.ColorUtil;
import com.xhh.launcher.custom.util.ImageUtil;
import com.xhh.launcher.custom.util.ScreenUtil;

/**
 * <p>壁纸颜色获取服务.</p>
 * <p>创建时间: 2018/3/20 0020</p>
 * <br/><p>壁纸颜色获取服务</p>
 * @author nameh
 */

public class WallpaperColorService extends IntentService {

    public WallpaperColorService() {
        super("WallpaperColorService");
    }

    /**
     * <p>执行操作.</p>
     * <p>创建时间: 2018/3/20 0020</p>
     * <br/><p>获取壁纸并截取状态栏的导航栏部分，再获取状态栏导航栏亮色或者暗色并通过广播传回{@link com.xhh.launcher.custom.activity.launcher.LauncherActivity.PaletteReceiver#onReceive(Context, Intent)}</p>
     */

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        PackageManager packageManager = getApplicationContext().getPackageManager();
        Bitmap bitmap = null;
        if (wallpaperManager.getWallpaperInfo() != null) {
            try {
                bitmap = ImageUtil.drawable2Bitmap(wallpaperManager.getWallpaperInfo().loadThumbnail(packageManager));
            }catch (Exception e){
                bitmap=ImageUtil.drawable2Bitmap(wallpaperManager.getDrawable());
            }
        } else {
            bitmap=ImageUtil.drawable2Bitmap(wallpaperManager.getDrawable());
        }
        if (bitmap != null) {
            Palette paletteStatus = ImageUtil.getImagePalette(bitmap, 0, 0, bitmap.getWidth(), ScreenUtil.getStatusBarHeight(getApplicationContext()));
            int height=ScreenUtil.getNavigationHeight(getApplicationContext());
            Palette paletteNavigation = ImageUtil.getImagePalette(bitmap,0,bitmap.getHeight()-height,bitmap.getWidth(),bitmap.getHeight());

            WallpaperPalette wallpaperPalette=new WallpaperPalette();

            if(paletteStatus!=null){
                wallpaperPalette.setStatusLight(ColorUtil.isShouldLight(paletteStatus));
            }

            if(paletteNavigation!=null){
                wallpaperPalette.setNavigationLight(ColorUtil.isShouldLight(paletteNavigation));
            }

            Intent intentPalette=new Intent(LauncherActivity.SERVICE_RECEIVER_WALLPAPER_PALETTE);
            intentPalette.putExtra("palette",wallpaperPalette);
            sendBroadcast(intentPalette);
        }
    }

}
