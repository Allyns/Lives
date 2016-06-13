package com.allyn.lives.presenter;

import android.os.Bundle;

import com.allyn.lives.fragment.music.local.MusicLocalListFragment;
import com.allyn.lives.model.MusicModel;
import com.allyn.lives.model.bean.MusicBean;
import com.jude.beam.expansion.list.BeamListFragmentPresenter;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by apple on 16/6/8.
 */
public class MusicLocalPresenter extends BeamListFragmentPresenter<MusicLocalListFragment, MusicBean> {

    @Override
    protected void onCreate(MusicLocalListFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
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
                        getAdapter().addAll(musicBeen);
                    }
                });

    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        getAdapter().pauseMore();
    }
}
