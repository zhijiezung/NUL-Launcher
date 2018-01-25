package com.xhh.launcher.custom.base;

import android.support.design.widget.AppBarLayout;

/**
 * 重写AppBarLayout状态监听
 * <p>date: 18-1-25</p>
 *
 * @author xhh
 */
public abstract class AAppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {

    /**
     * 状态
     * <p>date: 18-1-25</p>
     * 展开 折叠 中间
     */
    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private State mCurrentState = State.IDLE;

    /**
     * 重写偏移改变
     * <p>date: 18-1-25</p>
     *
     * @param appBarLayout
     *        改变的AppBarLayout控件
     * @param verticalOffset
     *        改变的偏移量
     */
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0) {
            if (mCurrentState != State.EXPANDED) {
                onAppBarStateChanged(appBarLayout, State.EXPANDED);
            }
            mCurrentState = State.EXPANDED;
        } else if ((Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange())) {
            if (mCurrentState != State.COLLAPSED) {
                onAppBarStateChanged(appBarLayout, State.COLLAPSED);
            }
            mCurrentState = State.COLLAPSED;
        } else {
            if (mCurrentState != State.IDLE) {
                onAppBarStateChanged(appBarLayout, State.IDLE);
            }
            mCurrentState = State.IDLE;
        }
    }

    public abstract void onAppBarStateChanged(AppBarLayout appBarLayout, State state);
}
