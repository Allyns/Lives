package com.allyn.lives.activity.music;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.allyn.lives.R;
import com.allyn.lives.activity.base.BaseActivity;
import com.allyn.lives.bean.MusicBean;
import com.allyn.lives.events.MusicBeamEvent;
import com.allyn.lives.manage.PlayMainage;
import com.allyn.lives.service.MusicService;
import com.allyn.lives.utils.Config;
import com.allyn.lives.utils.RxBus;
import com.allyn.lives.utils.blur.BlurTransformation;


import java.util.Random;

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
    @Bind(R.id.pro_len)
    SeekBar mSeekbar;
    @Bind(R.id.tvStart)
    TextView tvStart;
    @Bind(R.id.tvEnd)
    TextView tvEnd;

    @Bind(R.id.tvMusicNmae)
    TextView tvMusicNmae;
    @Bind(R.id.tvAuthorName)
    TextView tvAuthorName;

    MediaPlayer media;
    int mPosition;

    private boolean mUpdateTimeFlag = false;
    private boolean misPlaying = false;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play_activivy);
        ButterKnife.bind(this);

        intent = new Intent(MusicPlayActivivy.this, MusicService.class);

        com.bumptech.glide.Glide.with(this)
                .load("http://pic.qiantucdn.com/58pic/17/86/39/58u58PICyaM_1024.jpg")
                .crossFade()
                .placeholder(R.drawable.music_logo)
                .bitmapTransform(new BlurTransformation(this, 50, 3))
                .into(mBg);

        RxBus.getDefault().toObserverable(MusicBeamEvent.class).subscribe(new Action1<MusicBeamEvent>() {
            @Override
            public void call(MusicBeamEvent musicBeamEvent) {
                MusicBean musicBean = PlayMainage.getList().get(musicBeamEvent.getIndex());
                tvMusicNmae.setText(musicBean.getName());
                tvAuthorName.setText(musicBean.getArtist());
            }
        });

        listener();

    }


    Handler h = new Handler();
    Runnable run = new Runnable() {
        @Override
        public void run() {
            MediaPlayer mediaPlayer = PlayMainage.mediaPlayer;
            if (mediaPlayer != null) {
                mSeekbar.setProgress(mediaPlayer.getCurrentPosition() * mSeekbar.getMax() / mediaPlayer.getDuration());
            }
            h.postDelayed(run, 100);
        }

    };

    @Override
    protected void onResume() {
        super.onResume();
        media = PlayMainage.mediaPlayer;
        mPosition = MusicService.servicePosition;
        if (media != null) {
            new Thread(run).start();
            tvEnd.setText(PlayMainage.formatTime(media.getDuration()));

            //更新播放时间
            if (mUpdateTimeFlag == false) {
                mUpdateTimeFlag = true;
                new TimeThread().start();
            }

            UpdateButton(media.isPlaying());

        }

        if (PlayMainage.getList() == null) {
            tvMusicNmae.setText("没有歌曲");
            tvAuthorName.setText("");
            return;
        }
        if (mPosition == -1) {
            mPosition = 0;
        }
        MusicBean music = PlayMainage.getList().get(mPosition);
        tvMusicNmae.setText(music.getName());
        tvAuthorName.setText(music.getArtist());
    }


    class TimeThread extends Thread {
        @Override
        public void run() {
            while (mUpdateTimeFlag) {
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        int timeLen;
                        if (PlayMainage.mediaPlayer != null) {
                            timeLen = PlayMainage.mediaPlayer.getCurrentPosition();
                        } else {
                            timeLen = 0;
                        }
                        tvStart.setText(PlayMainage.formatTime(timeLen));
                    }
                });
                //过一秒更新
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void UpdateButton(boolean isPlaying) {
        if (isPlaying) {
            misPlaying = false;
            mPlay.setText("暂停");
        } else {
            misPlaying = true;
            mPlay.setText("播放");
        }
    }

    private void listener() {
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdatePlay(true);
            }
        });

        mPrevios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdatePlay(false);
            }
        });

        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt(Config.position, mPosition);
                intent.putExtra(Config.bunder, bundle);
                startService(intent);
                new Thread(run).start();
                UpdateButton(misPlaying);
            }
        });

        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            boolean state = false;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //推送时暂停
                if (PlayMainage.mediaPlayer != null) {
                    if (PlayMainage.mediaPlayer.isPlaying()) {
                        PlayMainage.pause();
                        state = true;
                    } else {
                        state = false;
                    }
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //结束拖动时播放拖动位置的音乐，
                int pro = seekBar.getProgress();
                if (PlayMainage.mediaPlayer != null) {
                    //公式：      拖动后的播放进度=当前进度条的进度 X 歌曲的总长度 / 进度条的总大小
                    PlayMainage.mediaPlayer.seekTo(pro * PlayMainage.mediaPlayer.getDuration() / seekBar.getMax());
                    if (state) {
                        PlayMainage.mediaPlayer.start();
                    }
                    new Thread(run).start();
                }

            }
        });
    }

    public void UpdatePlay(boolean isNext) {
        if (PlayMainage.Code == PlayMainage.ORDER) {
            if (isNext) {
                if (mPosition == PlayMainage.getMusicSize() - 1) {
                    mPosition = 0;
                }
                mPosition++;
            } else {
                if (mPosition == 0) {
                    mPosition = PlayMainage.getMusicSize() - 1;
                }
                mPosition--;
            }

        } else {
            mPosition = new Random().nextInt(PlayMainage.getMusicSize() - 1);
        }
        Bundle bundle = new Bundle();
        bundle.putInt(Config.position, mPosition);
        intent.putExtra(Config.bunder, bundle);
        startService(intent);
        tvEnd.setText(PlayMainage.formatTime(PlayMainage.mediaPlayer.getDuration()));

        mPlay.setText("暂停");
    }
}
