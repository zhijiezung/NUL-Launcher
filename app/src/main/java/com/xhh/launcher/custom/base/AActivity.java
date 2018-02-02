package com.xhh.launcher.custom.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.xhh.launcher.custom.R;
import com.xhh.launcher.custom.app.APPManager;

/**
 * <span class="text-title">Activity基类</span>
 * <br/><span class="text-date">创建时间: 2018/1/31 0031</span>
 * <br/><span class="text-desc">方便管理activity</span>
 *
 * @author nameh
 */

public abstract class AActivity extends AppCompatActivity {

    /**
     * 打印类型枚举
     **/
    public enum Print {
        /**
         * 弹出Toast提示
         **/
        TOAST,
        /**
         * 弹出Snackbar提示
         **/
        SNACKBAR,
        /**
         * 弹出Dialog提示框
         **/
        DIALOG
    }

    /**
     * <span class="text-title">重写onCreate方法</span>
     * <br/><span class="text-date">创建时间: 2018/1/31 0031</span>
     * <br/><span class="text-desc">当Activity创建时把此Activity添加到管理器</span>
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        APPManager.getInstance().addActivity(this);
    }

    /**
     * <span class="text-title">重写onDestory方法</span>
     * <br/><span class="text-date">创建时间: 2018/1/31 0031</span>
     * <br/><span class="text-desc">当Activity被删除时把此Activity从管理器中移除</span>
     */

    @Override
    protected void onDestroy() {
        APPManager.getInstance().removeActivity(this);
        super.onDestroy();
    }

    /**
     * <span class="text-title">打印字符串</span>
     * <br/><span class="text-date">创建时间: 2018/1/31 0031</span>
     * <br/><span class="text-desc">提供打印方法，方便直接调用</span>
     *
     * @param type    展示的类型<br/><span class="text-type">TOAST: 弹出Toast提示<br/>SNACKBAR: 弹出Snackbar提示<br/>DIALOOG: 弹出Dialog提示框</span>
     * @param time    展示时间,当展示类型为DIALOG时无效
     * @param message 标题以及正文<br/><span class="text-type">0:正文<br/>1:标题</span>
     */

    public void print(Print type, int time, String... message) {
        if (message[0] == null) return;
        //运行在UI线程，方便直接调用
        runOnUiThread(() -> {
            switch (type) {
                case TOAST:
                    toast(message[0], time);
                    break;
                case SNACKBAR:
                    print(message[0], time);
                    break;
                case DIALOG:
                    dialog(message[0], message.length == 2 ? message[1] : null);
                    break;
            }
        });

    }

    /**
     * <span class="text-title">Toast形式显示信息</span>
     * <br/><span class="text-date">创建时间: 2018/1/31 0031</span>
     *
     * @param message 要显示的信息
     * @param time    显示的时间
     */

    public void toast(String message, int time) {
        Toast.makeText(this, message, time).show();
    }

    /**
     * <span class="text-title">Snackbar形式显示信息</span>
     * <br/><span class="text-date">创建时间: 2018/1/31 0031</span>
     *
     * @param message 要显示的信息
     * @param time    显示的时间
     */

    public void print(String message, int time) {
        Snackbar.make(getWindow().getDecorView(), message, time).show();
    }

    /**
     * <span class="text-title">Dialog形式显示信息</span>
     * <br/><span class="text-date">创建时间: 2018/1/31 0031</span>
     *
     * @param message 要显示的信息
     * @param title   要显示的标题
     */

    public void dialog(String message, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (title != null) builder.setTitle(title);
        builder.setPositiveButton(getString(R.string.base_back), null);
        builder.setMessage(message);
        builder.show();
    }

}
