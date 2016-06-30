package com.allyn.lives.presenter;

import android.content.Intent;
import android.os.Bundle;

import com.allyn.lives.activity.books.BooksDetailsActivity;
import com.allyn.lives.fragment.music.local.MusicLocalListFragment;
import com.allyn.lives.model.MusicModel;
import com.allyn.lives.bean.MusicBean;
import com.jude.beam.expansion.list.BeamListFragmentPresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by apple on 16/6/8.
 */
public class MusicLocalPresenter extends BeamListFragmentPresenter<MusicLocalListFragment, MusicBean> implements RecyclerArrayAdapter.OnItemClickListener {

    @Override
    protected void onCreate(MusicLocalListFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
        getAdapter().setOnItemClickListener(this);
    }

    @Override
    protected void onCreateView(MusicLocalListFragment view) {
        super.onCreateView(view);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        Observable.create(new Observable.OnSubscribe<List<MusicBean>>() {
            @Override
            public void call(Subscriber<? super List<MusicBean>> subscriber) {
                subscriber.onNext(MusicModel.getLocaleMusic());
                subscriber.onCompleted();
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<MusicBean>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<MusicBean> musicBeen) {
                        getAdapter().clear();
                        Collections.reverse(musicBeen);
                        getAdapter().addAll(musicBeen);
                    }
                });
    }

    @Override
    public void onItemClick(int position) {
        getView().getActivity().startActivity(new Intent(getView().getActivity(), BooksDetailsActivity.class));
    }
}
