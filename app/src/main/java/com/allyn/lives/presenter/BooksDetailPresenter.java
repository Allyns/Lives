package com.allyn.lives.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.allyn.lives.activity.books.BooksDetailsActivity;
import com.allyn.lives.adapter.BookTitleAdapter;
import com.allyn.lives.bean.BooksDetailBean;
import com.allyn.lives.model.BooksModel;
import com.allyn.lives.netwoarks.IPConfig;
import com.allyn.lives.utils.Config;
import com.allyn.lives.utils.Transition;
import com.jude.beam.expansion.BeamBasePresenter;

import java.util.Collections;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/6/28.
 */
public class BooksDetailPresenter extends BeamBasePresenter<BooksDetailsActivity> implements SwipeRefreshLayout.OnRefreshListener {


    BookTitleAdapter adapter;
    BooksDetailBean data;

    @Override
    protected void onCreate(@NonNull BooksDetailsActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        adapter = new BookTitleAdapter(getView().getApplicationContext());
        onRefresh();
    }
    @Override
    protected void onCreateView(@NonNull BooksDetailsActivity view) {
        super.onCreateView(view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getView().getApplicationContext());
        getView().recyclerView.setLayoutManager(layoutManager);
        getView().recyclerView.setRefreshListener(this);
        getView().recyclerView.setAdapterWithProgress(adapter);
        listener();
    }

    private void listener() {
        getView().fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getView().recyclerView, "已收藏", Snackbar.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onRefresh() {
        BooksModel.getBooksDetails(getView().getIntent().getIntExtra(Config.DetailId, -1), new Subscriber<BooksDetailBean>() {
            @Override
            public void onCompleted() {
//                getView().recyclerView.showProgress();
            }

            @Override
            public void onError(Throwable e) {
                getView().recyclerView.showError();
                getView().loading_progre.setVisibility(View.GONE);
            }

            @Override
            public void onNext(BooksDetailBean booksDetailBean) {
                data = booksDetailBean;

                getView().loading_progre.setVisibility(View.GONE);

                adapter.clear();
                Collections.reverse(booksDetailBean.getList());
                adapter.addAll(booksDetailBean.getList());

                getView().mAuthor.setText("作者: "+data.getAuthor());
                getView().mTvClassifyName.setText("分类: " + Transition.getClassifyName(data.getBookclass()));

                com.bumptech.glide.Glide.with(getView().getApplicationContext())
                        .load(IPConfig.ImageUrl + data.getImg())
                        .crossFade()
                        .into(getView().mMsg);
            }
        });
    }
}
