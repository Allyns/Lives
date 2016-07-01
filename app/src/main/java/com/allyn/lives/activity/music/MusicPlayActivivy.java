package com.allyn.lives.activity.music;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.allyn.lives.R;
import com.allyn.lives.activity.base.BaseActivity;
import com.allyn.lives.bean.MusicBean;
import com.allyn.lives.events.MusicBeamEvent;
import com.allyn.lives.service.MusicService;
import com.allyn.lives.utils.RxBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class MusicPlayActivivy extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.btnPrevious)
    Button mPrevios;
    @Bind(R.id.btnPlay)
    Button mPlay;
    @Bind(R.id.btnNext)
    Button mNext;
    @Bind(R.id.btnLike)
    Button mLike;
    @Bind(R.id.ivBg)
    ImageView mBg;

    MusicService mService;
    boolean mBound = false;
    MusicBean mMusic;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play_activivy);
        ButterKnife.bind(this);

        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
//        RxBus.getDefault().toObserverable(MusicBeamEvent.class)
//                .subscribe(new Action1<MusicBeamEvent>() {
//                    @Override
//                    public void call(MusicBeamEvent musicBean) {
//                        mMusic = musicBean.getBean();
//
//                        Toast.makeText(MusicPlayActivivy.this, mMusic.getFileData(), Toast.LENGTH_SHORT).show();
//                        try {
//                            mediaPlayer.setDataSource(mMusic.getFileData());
//                            mediaPlayer.prepare();
//                            mediaPlayer.start();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    public void onButtonClick(View v) {
        if (mBound) {
            mService.play();

//            mService.stop();
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
}
