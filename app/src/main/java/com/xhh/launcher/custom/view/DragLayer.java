package com.xhh.launcher.custom.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * <p>拖动层.</p>
 * <p>创建时间: 2018/6/9 0009</p>
 * <br/><p>拖动层</p>
 * @author nameh
 */
public class DragLayer extends FrameLayout {

    public DragLayer(@NonNull Context context) {
        super(context);
        init();
    }

    public DragLayer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * <p>初始化拖动层.</p>
     * <p>创建时间: 2018/6/9 0009</p>
     * <br/><p></p>
     */

    private void init(){

    }
}
