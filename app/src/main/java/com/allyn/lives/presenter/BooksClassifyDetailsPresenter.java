package com.allyn.lives.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;

import com.allyn.lives.R;
import com.allyn.lives.activity.books.BooksClassifyDetailsActivity;
import com.allyn.lives.bean.BooksBean;
import com.allyn.lives.model.BooksModel;
import com.allyn.lives.utils.Config;
import com.jude.beam.expansion.list.BeamListActivityPresenter;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/6/27.
 */
public class BooksClassifyDetailsPresenter extends BeamListActivityPresenter<BooksClassifyDetailsActivity, BooksBean.ListEntity> implements SwipeRefreshLayout.OnRefreshListener {


    int index = 1;

    @Override
    protected void onCreate(@NonNull BooksClassifyDetailsActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
    }

    @Override
    protected void onCreateView(@NonNull BooksClassifyDetailsActivity view) {
        super.onCreateView(view);
        getView().getListView().setLayoutManager(new GridLayoutManager(view, 2));
        getView().getListView().setRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        getData(Config.def_size);
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        index++;
        getData(index);
    }

    public void getData(int page) {
        BooksModel.getBooksList(page, Config.size, getView().getIntent().getIntExtra(Config.BookId, 1), new Subscriber<BooksBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getView().getListView().showError();
            }

            @Override
            public void onNext(BooksBean booksBean) {
                getAdapter().addAll(booksBean.getList());
            }
        });
    }
}
