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

public class BugActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private AppCompatTextView textDetial;
    private FloatingActionButton fabSend;
    private FloatingActionButton fabSendA;

    private Throwable throwable;
    private ExceptionUtil exceptionInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        APPManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_bug);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent != null) {
            try {
                throwable = (Throwable) intent.getSerializableExtra(ExtrasUtil.EXTRA_BUG_THROWABLE);
            } catch (Exception e) {
                print(e.getMessage());
            }
        }
        initView();
        initData();

    }

    private void initView() {
        appBarLayout = findViewById(R.id.app_bar);
        collapsingToolbarLayout=findViewById(R.id.toolbar_layout);
        //textDetial = findViewById(R.id.textDetial);
        fabSend = findViewById(R.id.fab);
        fabSendA=findViewById(R.id.fab_send);
    }

    private void initData() {
        exceptionInfo = new ExceptionUtil(this);

        appBarLayout.addOnOffsetChangedListener(new AAppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                switch (state) {
                    case IDLE:
                        collapsingToolbarLayout.setTitleEnabled(true);
                        break;
                    case EXPANDED:
                        collapsingToolbarLayout.setTitleEnabled(true);
                        fabSendA.hide();
                        break;
                    case COLLAPSED:
                        collapsingToolbarLayout.setTitleEnabled(false);
                        fabSendA.show();
                        break;
                }
            }
        });


        try {
            toolbar.setSubtitle(throwable.getMessage());
            //textDetial.setText("");
            //exceptionInfo.printPhoneInfo(textDetial);
            //if (throwable != null) exceptionInfo.printError(textDetial, throwable);
        } catch (Exception e) {
            print(e.getMessage());
        }

    }

    @Override
    public void onBackPressed() {
        APPManager.getInstance().exitApp();
        super.onBackPressed();
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

    private void print(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage(text);
        builder.setNegativeButton("返回", null);
        builder.show();
    }

}
