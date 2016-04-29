package com.common.ui;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.common.R;
import com.common.widget.EndlessScrollListener;


/**
 * author miekoz on 2016/3/16.
 * email  meikoz@126.com
 */
public class ListViewFragment extends BaseFragment {

    private ListView listView;
    private View header, footer;
    private View progressView, loadingView;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected int getLayoutResource() {
        return  R.layout.fragment_list;
    }

    @Override
    protected void onInitData() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) view.findViewById(R.id.list_view);
        loadingView = view.findViewById(R.id.loading_view);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setEnabled(false);
            swipeRefreshLayout.setColorSchemeColors(R.color.black);
        }

        header = getHeaderView();
        footer = getFooterView();

        if (header != null) {
            listView.addHeaderView(header, null, false);
            setupHeader(header);
        }

        if (footer != null) {
            listView.addFooterView(footer, null, false);
            setupFooter(footer);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onItemClicked(view, position);
            }
        });
    }

    public void enableSwipeRefresh() {
        if (swipeRefreshLayout == null) return;
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onListRefreshing();
            }
        });
    }

    public void onListRefreshing() {

    }

    public void stopRefreshing() {
        if (swipeRefreshLayout == null) return;
        if (swipeRefreshLayout.isRefreshing()) swipeRefreshLayout.setRefreshing(false);
    }

    public void enableLoadMore() {
        listView.setOnScrollListener(scrollListener);
    }

    private EndlessScrollListener scrollListener = new EndlessScrollListener() {
        @Override
        public void onLoadMore() {
            loadingView.setVisibility(View.VISIBLE);
            shouldLoadMore();
        }
    };

    public void shouldLoadMore() {

    }

    public void onLoadMoreFinished() {
        loadingView.setVisibility(View.GONE);
        scrollListener.onLoadMoreFinished();
    }

    public void onLoadMoreEnded() {
        loadingView.setVisibility(View.GONE);
        listView.setOnScrollListener(null);
    }

    public void setAdapter(BaseAdapter adapter) {
        listView.setAdapter(adapter);
    }

    @Deprecated
    public BaseAdapter getListAdapter() {
        return null;
    }

    public void onItemClicked(View view, int position) {
    }

    public void dismissProgress() {
        if (progressView == null) return;
        if (progressView.getVisibility() != View.GONE) progressView.setVisibility(View.GONE);
    }

    public void showProgress() {
        if (progressView == null) return;
        if (progressView.getVisibility() != View.VISIBLE) progressView.setVisibility(View.VISIBLE);
    }

/*    public void setListEmptyText(String emptyText, String emptyButtonText) {
        if (emptyTV == null) return;
        if (progressBar == null) return;
        progressBar.setVisibility(View.GONE);
        emptyTV.setText(emptyText);
        if (!TextUtils.isEmpty(emptyButtonText) && emptyButton != null) {
            emptyButton.setVisibility(View.VISIBLE);
            emptyButton.setText(emptyButtonText);
        }
    }

    public void setListEmptyText(String emptyText) {
        setListEmptyText(emptyText, null);
    }*/

    public void removeDividers() {
        listView.setDivider(null);
    }

    public ListView getListView() {
        return listView;
    }


    public void setFooter(View footer) {
        listView.addFooterView(footer);
    }

    public void setHeader(View header) {
        listView.addHeaderView(header);
    }

    @Deprecated
    public View getHeaderView() {
        return null;
    }

    @Deprecated
    public View getFooterView() {
        return null;
    }

    public View getFooter() {
        return footer;
    }

    public void setupHeader(View header) {
    }

    public void setupFooter(View footer) {
    }

    public void setBackground(@ColorInt int color) {
        rootView.setBackgroundColor(color);
    }

}
