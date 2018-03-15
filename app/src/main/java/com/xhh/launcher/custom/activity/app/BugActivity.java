package com.xhh.launcher.custom.activity.app;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.xhh.launcher.custom.R;
import com.xhh.launcher.custom.app.APPManager;
import com.xhh.launcher.custom.base.BaseActivity;
import com.xhh.launcher.custom.base.BaseAppBarStateChangeListener;
import com.xhh.launcher.custom.util.ExceptionUtil;
import com.xhh.launcher.custom.util.ExtrasUtil;
import com.xhh.launcher.custom.util.PermissionUtil;

/**
 * <p>捕获应用崩溃详情界面.</p>
 * <p>创建时间: 18-1-25</p>
 *
 * @author xhh
 */
public class BugActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 折叠工具栏控件
     **/
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    /**
     * 用于显示错误信息的文本
     **/
    private AppCompatTextView mTextDetial;
    /**
     * 发送错误信息的按钮
     **/
    private FloatingActionButton mFabSend;
    /**
     * 发送错误信息的按钮
     **/
    private FloatingActionButton mFabSendA;
    /**
     * 抛出的错误的类
     **/
    private Throwable mThrowable;
    /**
     * 异常处理工具类
     **/
    private ExceptionUtil mExceptionInfo;
    /**
     * 储存权限注册code
     */
    private final int REQUEST_CODE_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        Intent intent = getIntent();
        if (intent != null) {
            try {
                mThrowable = (Throwable) intent.getSerializableExtra(ExtrasUtil.EXTRA_BUG_THROWABLE);
            } catch (Exception e) {
                print(Print.DIALOG, 0, e.getMessage(), getString(R.string.base_prompt));
            }
        }
        initView();
        initData();
    }

    /**
     * <p>初始化Activity控件.</p>
     * <p>创建时间: 18-1-25</p>
     * <br/><p>初始化Activity的控件</p>
     */
    private void initView() {
        mAppBarLayout = findViewById(R.id.app_bar);
        mCollapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        mTextDetial = findViewById(R.id.text_Detial);
        mFabSend = findViewById(R.id.fab);
        mFabSendA = findViewById(R.id.fab_send);
    }

    /**
     * <p>初始化数据.</p>
     * <p>创建时间: 18-1-25</p>
     * <br/><p>初始化Activity的数据</p>
     */
    private void initData() {
        mExceptionInfo = new ExceptionUtil(BugActivity.this);

        mFabSend.setOnClickListener(this);
        mFabSendA.setOnClickListener(this);

        showError();

        mAppBarLayout.addOnOffsetChangedListener(new BaseAppBarStateChangeListener() {
            @Override
            public void onAppBarStateChanged(AppBarLayout appBarLayout, State state) {
                switch (state) {
                    case IDLE:
                        mCollapsingToolbarLayout.setTitleEnabled(true);
                        break;
                    case EXPANDED:
                        mCollapsingToolbarLayout.setTitleEnabled(true);
                        mFabSendA.hide();
                        break;
                    case COLLAPSED:
                        mCollapsingToolbarLayout.setTitleEnabled(false);
                        mFabSendA.show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * <p>显示错误信息.</p>
     * <p>创建时间: 18-1-25</p>
     * <br/><p>打印错误的堆栈信息</p>
     */
    private void showError() {
        try {
            mToolbar.setSubtitle(mThrowable.getMessage());
            mTextDetial.setText("");
            mExceptionInfo.printPhoneInfo(mTextDetial);
            if (mThrowable != null) {
                mExceptionInfo.printError(mTextDetial, mThrowable);
            }
        } catch (Exception e) {
            print(Print.DIALOG, 0, e.getMessage(), getString(R.string.base_prompt));
        }
    }

    /**
     * <p>发送错误信息.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>发送错误信息并保存</p>
     */

    private void sendError(){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,mTextDetial.getText().toString());
        intent.setType("text/plain");
        startActivity(intent);
    }

    /**
     * <p>按钮监听.</p>
     * <p>创建时间: 2018/3/15 0015</p>
     * <br/><p>按钮监听</p>
     * @param v 按下的View
     */

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab:
            case R.id.fab_send:
                if(requestPermissions(REQUEST_CODE_STORAGE, PermissionUtil.PERMISSION_STORAGE)){
                    sendError();
                }else{
                    print(Print.TOAST,Toast.LENGTH_SHORT,"没有授予权限");
                }
                break;
            default:
                break;
        }
    }

    /**
     * <p>OptionItem选中之后执行的操作.</p>
     * <br/><p>创建时间: 2018/2/1 0000</p>
     *
     * @param item 选中的菜单Item
     * @return boolean
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                APPManager.getInstance().exitApp();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * <p>拦截返回键.</p>
     * <p>创建时间: 2018/2/1 0001</p>
     * <br/><p>按下返回键直接关闭应用</p>
     */

    @Override
    public void onBackPressed() {
        APPManager.getInstance().exitApp();
        super.onBackPressed();
    }

}
