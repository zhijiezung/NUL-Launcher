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
import com.xhh.launcher.custom.base.AAppBarStateChangeListener;
import com.xhh.launcher.custom.util.ExceptionUtil;
import com.xhh.launcher.custom.util.ExtrasUtil;

/**
 * 捕获应用崩溃详情
 * <p>date: 18-1-25</p>
 *
 * @author xhh
 */
public class BugActivity extends AppCompatActivity {

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
        APPManager.getInstance().addActivity(BugActivity.this);
        setContentView(R.layout.activity_bug);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        Intent intent = getIntent();
        if (intent != null) {
            try {
                mThrowable = (Throwable) intent.getSerializableExtra(ExtrasUtil.EXTRA_BUG_THROWABLE);
            } catch (Exception e) {
                print(e.getMessage());
            }
        }
        initView();
        initData();

    }

    /**
     * 初始化控件
     * <p>date: 18-1-25</p>
     *
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
     * <p>date: 18-1-25</p>
     *
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
     * <p>date: 18-1-25</p>
     *
     */
    private void showError() {
        try {
            mToolbar.setSubtitle(mThrowable.getMessage());
            mTextDetial.setText("");
            mExceptionInfo.printPhoneInfo(mTextDetial);
            if (mThrowable != null) mExceptionInfo.printError(mTextDetial, mThrowable);
        } catch (Exception e) {
            print(e.getMessage());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                APPManager.getInstance().exitApp();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        APPManager.getInstance().exitApp();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        APPManager.getInstance().removeActivity(BugActivity.this);
        super.onDestroy();
    }

    /**
     * 打印额外的错误信息
     * <p>date: 18-1-25</p>
     *
     */
    private void print(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BugActivity.this);
        builder.setTitle(R.string.base_prompt);
        builder.setMessage(text);
        builder.setNegativeButton(R.string.base_back, null);
        builder.show();
    }

}
