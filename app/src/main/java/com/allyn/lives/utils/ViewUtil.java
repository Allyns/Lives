package com.allyn.lives.utils;

import android.media.SoundPool;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by C on 3/3/2016.
 * Nukc
 */
public class ViewUtil {

    public static RecyclerView.OnScrollListener getOnLoadMoreListener(final SoundPool.OnLoadCompleteListener onLoadMoreListener){
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && onLoadMoreListener != null) {
                    boolean isBottom;
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    if (layoutManager instanceof LinearLayoutManager) {
                        isBottom = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition()
                                >= layoutManager.getItemCount() - 1;
                    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                        StaggeredGridLayoutManager sgLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                        int[] into = new int[sgLayoutManager.getSpanCount()];
                        sgLayoutManager.findLastVisibleItemPositions(into);

                        isBottom = last(into) >= layoutManager.getItemCount() - 1;
                    }else {
                        isBottom = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition()
                                >= layoutManager.getItemCount() - 1;
                    }

                    if (layoutManager.getItemCount() > 0 && isBottom) {
//                        onLoadMoreListener.onLoadMore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        };
    }

    //取到最后的一个节点
    private static int last(int[] lastPositions) {
        int last = lastPositions[0];
        for (int value : lastPositions) {
            if (value > last) {
                last = value;
            }
        }
        return last;
    }
}
