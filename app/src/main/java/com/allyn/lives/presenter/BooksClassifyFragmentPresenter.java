package com.allyn.lives.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.allyn.lives.activity.books.BooksDetailsActivity;
import com.allyn.lives.adapter.BooksClassAdapter;
import com.allyn.lives.fragment.books.BooksClassifyFragment;
import com.allyn.lives.model.BooksModel;
import com.allyn.lives.bean.BooksClassifyBean;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/6/21.
 */
public class BooksClassifyFragmentPresenter extends com.jude.beam.expansion.BeamBasePresenter<BooksClassifyFragment> implements SwipeRefreshLayout.OnRefreshListener {

    BooksClassAdapter adapter;
    BooksClassifyBean imageclassify;

    @Override
    protected void onCreate(@NonNull BooksClassifyFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
        adapter = new BooksClassAdapter(getView().getContext());
        onRefresh();
    }

    @Override
    protected void onCreateView(@NonNull BooksClassifyFragment view) {
        super.onCreateView(view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getView().getContext());
        getView().recyclerView.setLayoutManager(manager);
        getView().recyclerView.setAdapterWithProgress(adapter);
        getView().recyclerView.setRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        BooksModel.getDataClassify(new Subscriber<BooksClassifyBean>() {
            @Override
            public void onNext(BooksClassifyBean imageClassifyBean) {
                imageclassify = imageClassifyBean;
                adapter.clear();
                adapter.addAll(imageClassifyBean.getTngou());
            }

            @Override
            public void onCompleted() {
                getView().recyclerView.showProgress();
            }

            @Override
            public void onError(Throwable e) {
                if (getView().recyclerView != null) {
                    getView().recyclerView.showError();
                }
            }
        });
    }
}

