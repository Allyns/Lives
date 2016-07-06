package com.allyn.lives.activity.music;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;

import com.allyn.lives.R;
import com.allyn.lives.activity.base.BaseActivity;
import com.allyn.lives.utils.blur.BlurTransformation;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play_activivy);
        ButterKnife.bind(this);
        com.bumptech.glide.Glide.with(this)
                .load("http://pic.qiantucdn.com/58pic/17/86/39/58u58PICyaM_1024.jpg")
                .crossFade()
                .placeholder(R.mipmap.ic_placeholder)
                .bitmapTransform(new BlurTransformation(this, 50, 3))
                .into(mBg);
    }

}
