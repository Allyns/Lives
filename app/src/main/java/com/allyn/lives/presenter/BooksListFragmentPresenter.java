package com.allyn.lives.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;

import com.allyn.lives.bean.BooksBean;
import com.allyn.lives.fragment.books.BooksListFragment;
import com.allyn.lives.model.BooksModel;
import com.allyn.lives.utils.Config;
import com.jude.beam.expansion.list.BeamListFragmentPresenter;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/6/24.
 */
public class BooksListFragmentPresenter extends BeamListFragmentPresenter<BooksListFragment, BooksBean.ListEntity> implements SwipeRefreshLayout.OnRefreshListener {

    int index = 1;

    @Override
    protected void onCreate(@NonNull BooksListFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
    }

    @Override
    protected void onCreateView(@NonNull BooksListFragment view) {
        super.onCreateView(view);
        getView().getListView().setRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        getData(Config.def_size);
    }

    public void getData(int page) {
        BooksModel.getBooksList(page, Config.size, getView().getArguments().getInt(Config.Type, 1), new Subscriber<BooksBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(BooksBean imageBean) {
                getAdapter().addAll(imageBean.getList());
            }
        });
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        index++;
        getData(index);
    }


}
