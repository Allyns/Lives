package com.allyn.lives.activity.music;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.allyn.lives.R;
import com.allyn.lives.activity.base.BaseActivity;
import com.allyn.lives.service.MusicService;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    MusicService service;

    private boolean isStart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play_activivy);
        ButterKnife.bind(this);
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MusicPlayActivivy.this, service.test(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, connection, Context.BIND_ABOVE_CLIENT);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isStart) {
            unbindService(connection);
            isStart = false;
        }
    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicService.LiteBinder binder = (MusicService.LiteBinder) iBinder;
            service = binder.getService();
            isStart = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isStart = false;

        }
    };
}
