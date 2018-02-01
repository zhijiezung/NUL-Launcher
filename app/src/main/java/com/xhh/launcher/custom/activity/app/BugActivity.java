package com.xhh.launcher.custom.activity.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.xhh.launcher.custom.R;
import com.xhh.launcher.custom.app.APPManager;
import com.xhh.launcher.custom.base.AActivity;
import com.xhh.launcher.custom.base.AAppBarStateChangeListener;
import com.xhh.launcher.custom.util.ExceptionUtil;
import com.xhh.launcher.custom.util.ExtrasUtil;

/**
 * 捕获应用崩溃详情
 * 创建时间: 18-1-25
 *
 * @author xhh
 */
public class BugActivity extends AActivity {

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private AppCompatTextView mTextDetial;
    private FloatingActionButton mFabSend;
    private FloatingActionButton mFabSendA;
    private Throwable mThrowable;
    private ExceptionUtil mExceptionInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        System.out.println("test01");
        System.out.println("test");
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
     * 初始化控件
     * 创建时间: 18-1-25
     * <br>初始化Activity的控件
     */
    private void initView() {
        mAppBarLayout = findViewById(R.id.app_bar);
        mCollapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        mTextDetial = findViewById(R.id.text_Detial);
        mFabSend = findViewById(R.id.fab);
        mFabSendA = findViewById(R.id.fab_send);
    }

    /**
     * 初始化数据
     * 创建时间: 18-1-25
     * <br>初始化Activity的数据
     */
    private void initData() {
        mExceptionInfo = new ExceptionUtil(BugActivity.this);

        mAppBarLayout.addOnOffsetChangedListener(new AAppBarStateChangeListener() {
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
                }
            }
        });

        showError();
    }

    /**
     * 显示错误信息
     * 创建时间: 18-1-25
     * <br>打印错误的堆栈信息
     */
    private void showError() {
        try {
            mToolbar.setSubtitle(mThrowable.getMessage());
            mTextDetial.setText("");
            mExceptionInfo.printPhoneInfo(mTextDetial);
            if (mThrowable != null) mExceptionInfo.printError(mTextDetial, mThrowable);
        } catch (Exception e) {
            print(Print.DIALOG, 0, e.getMessage(), getString(R.string.base_prompt));
        }
    }

    /**
     * OptionItem选中之后执行的操作
     * 创建时间: 2018/2/1 0000
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
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 拦截返回键
     * 创建时间: 2018/2/1 0001
     * <br>按下返回键直接关闭应用
     */

    @Override
    public void onBackPressed() {
        APPManager.getInstance().exitApp();
        super.onBackPressed();
    }

}
