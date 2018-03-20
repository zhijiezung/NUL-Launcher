package com.xhh.launcher.custom.activity.launcher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.xhh.launcher.custom.R;
import com.xhh.launcher.custom.base.BaseActivity;
import com.xhh.launcher.custom.data.WallpaperPalette;
import com.xhh.launcher.custom.receiver.WallpaperReceiver;
import com.xhh.launcher.custom.service.WallpaperColorService;

/**
 * <p>启动器主界面.</p>
 * <p>创建时间: 18-1-25</p>
 *
 * @author xhh
 */
public class LauncherActivity extends BaseActivity {

    private WallpaperReceiver mWallpaperReceiver;
    private PaletteReceiver mPaletteReceiver;

    public static final String SERVICE_RECEIVER_WALLPAPER_PALETTE = "com.xhh.launcher.custom.activity.launcher.receiver.palette";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_WALLPAPER_CHANGED);
        mWallpaperReceiver = new WallpaperReceiver();
        registerReceiver(mWallpaperReceiver, intentFilter);

        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter.addAction(SERVICE_RECEIVER_WALLPAPER_PALETTE);
        mPaletteReceiver = new PaletteReceiver();
        registerReceiver(mPaletteReceiver, intentFilter1);

        Intent ser = new Intent(this, WallpaperColorService.class);
        startService(ser);
    }

    /**
     * <p>按键按下监听.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>主要是监听返回键</p>
     *
     * @param keyCode 按键code
     * @param event   按下动作
     * @return boolean
     */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent ser = new Intent(this, WallpaperColorService.class);
        startService(ser);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWallpaperReceiver != null) {
            unregisterReceiver(mWallpaperReceiver);
        }
        if (mPaletteReceiver != null) {
            unregisterReceiver(mPaletteReceiver);
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(LauncherActivity.this, R.string.activity_launcher_toast_keydown_back, Toast.LENGTH_SHORT).show();
    }

    class PaletteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            WallpaperPalette wallpaperPalette = (WallpaperPalette) intent.getSerializableExtra("palette");
            print(Print.TOAST, Toast.LENGTH_LONG, "颜色:" + wallpaperPalette.getPaletteStatus());
        }
    }

}
