package com.xhh.launcher.custom.activity.launcher;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.xhh.launcher.custom.R;
import com.xhh.launcher.custom.base.BaseActivity;

/**
 * <p>启动器主界面.</p>
 * <p>创建时间: 18-1-25</p>
 *
 * @author xhh
 */
public class LauncherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        Object obj = null;
        obj.toString();
    }

    /**
     * <p>按键按下监听.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>主要是监听返回键</p>
     * @param keyCode 按键code
     * @param event 按下动作
     * @return boolean
     */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                Toast.makeText(LauncherActivity.this, R.string.activity_launcher_toast_keydown_back, Toast.LENGTH_SHORT).show();
                break;
                default:
                    break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
