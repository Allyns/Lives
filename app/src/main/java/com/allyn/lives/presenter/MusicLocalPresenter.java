package com.allyn.lives.presenter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;

import com.allyn.lives.activity.music.MusicPlayActivivy;
import com.allyn.lives.events.MusicBeamEvent;
import com.allyn.lives.fragment.music.local.MusicLocalListFragment;
import com.allyn.lives.model.MusicModel;
import com.allyn.lives.bean.MusicBean;
import com.allyn.lives.service.MusicService;
import com.allyn.lives.utils.RxBus;
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

    List<MusicBean> been;
    MusicService mService;
    boolean mBound = false;

    @Override
    protected void onCreate(MusicLocalListFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
        getAdapter().setOnItemClickListener(this);

        Intent intent = new Intent(view.getActivity(), MusicService.class);
        view.getActivity().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

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
                        been = musicBeen;
                        getAdapter().clear();
                        Collections.reverse(musicBeen);
                        getAdapter().addAll(musicBeen);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBound) {
            getView().getActivity().unbindService(mConnection);
            mBound = false;
        }
    }


    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            MusicService.LocalBinder binder = (MusicService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    @Override
    public void onItemClick(int position) {

        RxBus.getDefault().post(new MusicBeamEvent(been.get(position)));

        if (mBound) {
            mService.remove();
            mService.play();
        }
//        getView().getActivity().startActivity(new Intent(getView().getActivity(), MusicPlayActivivy.class));
    }

}
