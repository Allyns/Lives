package com.allyn.lives.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;

import com.allyn.lives.bean.BooksBean;
import com.allyn.lives.fragment.books.RecommendBooksFragment;
import com.allyn.lives.model.BooksModel;
import com.allyn.lives.utils.Config;
import com.jude.beam.expansion.list.BeamListFragmentPresenter;

import java.util.Random;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/6/27.
 */
public class NewImagePreenter extends BeamListFragmentPresenter<RecommendBooksFragment, BooksBean.ListEntity> implements SwipeRefreshLayout.OnRefreshListener{

    @Override
    protected void onCreate(@NonNull RecommendBooksFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
    }

    @Override
    protected void onCreateView(@NonNull RecommendBooksFragment view) {
        super.onCreateView(view);
        getView().getListView().setLayoutManager(new GridLayoutManager(view.getActivity(), 2));
        getView().getListView().setRefreshListener(this);
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        getData();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        getData();
    }


    public void getData() {
        BooksModel.getBooksList(2, Config.size,1, new Subscriber<BooksBean>() {
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
}
