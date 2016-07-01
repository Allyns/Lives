package com.allyn.lives.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;

import com.allyn.lives.bean.MusicBean;
import com.allyn.lives.events.MusicBeamEvent;
import com.allyn.lives.utils.RxBus;

import java.net.URI;
import java.net.URL;
import java.util.Random;

import rx.functions.Action1;

public class MusicService extends Service {

    private final IBinder mBinder = new LocalBinder();

    MediaPlayer mediaPlayer;

    MusicBean mMusic;


    public class LocalBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }


    public void play() {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
        RxBus.getDefault().toObserverable(MusicBeamEvent.class)
                .subscribe(new Action1<MusicBeamEvent>() {
                    @Override
                    public void call(MusicBeamEvent musicBean) {
                        mMusic = musicBean.getBean();
                    }
                });
        try {
            mediaPlayer.setDataSource(mMusic.getFileData());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void stop() {
        mediaPlayer.stop();
    }

    public void next() {

    }

    public void Previous() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public String testMSG() {
        return "测试通信";
    }
}
