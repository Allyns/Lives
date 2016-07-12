package com.allyn.lives.holder;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

import com.allyn.lives.R;
import com.allyn.lives.bean.MusicBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/7/11.
 */
public class MusicLocalHolder extends BaseViewHolder<MusicBean> {


    @Bind(R.id.local_music_name)
    TextView mName;
    @Bind(R.id.local_music_msg)
    TextView mMsg;
    @Bind(R.id.local_music_img)
    ImageView mImg;

    public MusicLocalHolder(ViewGroup viewGroup) {
        super(viewGroup, R.layout.item_local_music);
        mName = $(R.id.local_music_name);
        mMsg = $(R.id.local_music_msg);
        mImg = $(R.id.local_music_img);
    }

    @Override
    public void setData(MusicBean data) {
        mName.setText(data.getName());
        mMsg.setText(data.getArtist() + " - " + data.getAlbum());
    }
}
