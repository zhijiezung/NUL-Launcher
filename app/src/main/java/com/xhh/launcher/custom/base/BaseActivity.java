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
 * <p>Activity基类.</p>
 * <p>创建时间: 2018/1/31 0031</p>
 * <br/><p>方便管理activity</p>
 *
 * @author nameh
 */

public abstract class BaseActivity extends AppCompatActivity {

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
     * <p>重写onCreate方法.</p>
     * <p>创建时间: 2018/1/31 0031</p>
     * <br/><p>当Activity创建时把此Activity添加到管理器</p>
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        APPManager.getInstance().addActivity(this);
    }

    /**
     * <p>重写onDestory方法.</p>
     * <p>创建时间: 2018/1/31 0031</p>
     * <br/><p>当Activity被删除时把此Activity从管理器中移除</p>
     */

    @Override
    protected void onDestroy() {
        APPManager.getInstance().removeActivity(this);
        super.onDestroy();
    }

    /**
     * <p>打印字符串.</p>
     * <p>创建时间: 2018/1/31 0031.</p>
     * <br/><p>提供打印方法，方便直接调用</p>
     *
     * @param type    展示的类型.
     *                <table border="1">
     *                <tr>
     *                <th>类型</th>
     *                <th>操作</th>
     *                </tr>
     *                <tr>
     *                <td>TOAST</td>
     *                <td>弹出Toast提示</td>
     *                </tr>
     *                <tr>
     *                <td>SNACKBAR</td>
     *                <td>弹出Snackbar提示</td>
     *                </tr>
     *                <tr>
     *                <td>DIALOOG</td>
     *                <td>弹出Dialog提示框</td>
     *                </tr>
     *                </table>
     * @param time    展示时间,当展示类型为DIALOG时无效.
     * @param message 标题以及正文.<ul><li>0:正文<li>1:标题</ul>
     */

    public void print(Print type, int time, String... message) {
        if (message[0] == null) {
            return;
        }
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
                    default:
                        break;
            }
        });

    }

    /**
     * <p>Toast形式显示信息.</p>
     * <p>创建时间: 2018/1/31 0031</p>
     *
     * @param message 要显示的信息
     * @param time    显示的时间
     */

    public void toast(String message, int time) {
        Toast.makeText(this, message, time).show();
    }

    /**
     * <p>Snackbar形式显示信息.</p>
     * <p>创建时间: 2018/1/31 0031</p>
     *
     * @param message 要显示的信息
     * @param time    显示的时间
     */

    public void print(String message, int time) {
        Snackbar.make(getWindow().getDecorView(), message, time).show();
    }

    /**
     * <p>Dialog形式显示信息.</p>
     * <p>创建时间: 2018/1/31 0031</p>
     *
     * @param message 要显示的信息
     * @param title   要显示的标题
     */

    public void dialog(String message, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (title != null) {
            builder.setTitle(title);
        }
        builder.setPositiveButton(getString(R.string.base_back), null);
        builder.setMessage(message);
        builder.show();
    }

}
