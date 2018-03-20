package com.xhh.launcher.custom.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.xhh.launcher.custom.R;
import com.xhh.launcher.custom.app.APPManager;
import com.xhh.launcher.custom.util.PermissionUtil;

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
     * <p>权限注册回调.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>权限注册回调.</p>
     *
     * @param requestCode  返回code
     * @param permissions  注册的权限
     * @param grantResults 授权值
     * @return
     */

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        onPermissions(requestCode, PermissionUtil.checkPermissions(grantResults));
    }

    /**
     * <p>权限注册回调.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>权限注册回调</p>
     *
     * @param requestCode 注册code
     * @param isGranted   是否授权成功
     */

    protected void onPermissions(int requestCode, boolean isGranted) {
        if (!isGranted) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getString(R.string.activity_base_dialog_permission_message));
            builder.setPositiveButton(getString(R.string.activity_base_dialog_permission_manual), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    intent.setData(Uri.parse("package:" + getPackageName()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton(getString(R.string.base_cancel), null);
            builder.show();
        } else {
            print(Print.TOAST, Toast.LENGTH_LONG, getString(R.string.activity_base_toast_repeat_operation));
        }
    }

    /**
     * <p>注册权限.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>注册权限</p>
     *
     * @param requestCode 注册code
     * @param permissions 需要注册的权限
     * @return boolean true为已经授权
     */

    public boolean requestPermissions(int requestCode, String[] permissions) {
        return PermissionUtil.request(this, permissions, requestCode);
    }

    /**
     * <p>设置状态栏导航栏图标亮色.</p>
     * <p>创建时间: 2018/3/20 0020</p>
     * <br/><p>设置状态栏导航栏图标亮色</p>
     * <br><p>参考Lawnchair的ch.deletescape.lawnchair.Launcher类的
     *        <a href="https://github.com/LawnchairLauncher/Lawnchair/blob/beta/app/src/main/java/ch/deletescape/lawnchair/Launcher.java#L499" target="blank">
     *           activateLightSystemBars
     *        </a>
     *        方法修改的效果
     *      </p>
     *
     * @param isLight true为亮色,false为暗色
     */

    public void setLightStatusNavigationBar(boolean isLight, int flag) {
        int oldFlags = getWindow().getDecorView().getSystemUiVisibility();
        int newFlags = oldFlags;
        if (!isLight) {
            newFlags |= flag;
        } else {
            newFlags &= ~(flag);
        }
        if (newFlags != oldFlags) {
            int finalNewFlags = newFlags;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    getWindow().getDecorView().setSystemUiVisibility(finalNewFlags);
                }
            });
        }
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
