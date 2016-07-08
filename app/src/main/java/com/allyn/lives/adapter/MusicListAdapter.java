package com.allyn.lives.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allyn.lives.R;
import com.allyn.lives.activity.music.MusicPlayActivivy;
import com.allyn.lives.bean.MusicBean;
import com.allyn.lives.service.MusicService;
import com.allyn.lives.utils.Config;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.yokeyword.indexablelistview.IndexableAdapter;

/**
 * Created by Administrator on 2016/7/8.
 */
public class MusicListAdapter extends IndexableAdapter<MusicBean> {

    Context context;

    public MusicListAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected TextView onCreateTitleViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_music_list_title, parent, false);
        return (TextView) view.findViewById(R.id.tvTitle);
    }

    @Override
    protected ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_local_music, parent, false);
        return new MusicLocalHolder(view);
    }


    @Override
    protected void onBindViewHolder(ViewHolder holder, MusicBean cityEntity) {
        MusicLocalHolder musicLocalHolder = (MusicLocalHolder) holder;
        musicLocalHolder.mName.setText(cityEntity.getName());
        musicLocalHolder.mMsg.setText(cityEntity.getArtist() + " - " + cityEntity.getAlbum());
    }

    class MusicLocalHolder extends IndexableAdapter.ViewHolder {

        @Bind(R.id.local_music_name)
        TextView mName;
        @Bind(R.id.local_music_msg)
        TextView mMsg;
        @Bind(R.id.local_music_img)
        ImageView mImg;

        public MusicLocalHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
