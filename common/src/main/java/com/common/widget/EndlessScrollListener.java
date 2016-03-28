package com.common.widget;

import android.widget.AbsListView;

/**
 * author miekoz on 2016/3/16.
 * email  meikoz@126.com
 */
public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {

    private int currentFirstVisibleItem, currentVisibleItemCount, currentScrollState;
    private boolean isLoading = false;

    public abstract void onLoadMore();

    public void onLoadMoreFinished() {
        isLoading = false;
    }

    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.currentFirstVisibleItem = firstVisibleItem;
        this.currentVisibleItemCount = visibleItemCount;
    }

    public void onScrollStateChanged(AbsListView view, int scrollState) {
        this.currentScrollState = scrollState;
        this.isScrollCompleted();
    }

    private void isScrollCompleted() {
        if (this.currentVisibleItemCount > 0 && this.currentScrollState == SCROLL_STATE_IDLE) {
            if (!isLoading) {
                isLoading = true;
                onLoadMore();
            }
        }
    }

}

