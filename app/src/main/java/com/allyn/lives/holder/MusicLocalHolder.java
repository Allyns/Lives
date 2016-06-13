package com.allyn.lives.holder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.allyn.lives.R;
import com.allyn.lives.model.bean.MusicBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by apple on 16/6/8.
 */
public class MusicLocalHolder extends BaseViewHolder<MusicBean> {

    @Bind(R.id.local_music_name)
    TextView mName;
    @Bind(R.id.local_music_msg)
    TextView mMsg;

    public MusicLocalHolder(ViewGroup viewParent) {
        super(viewParent, R.layout.item_local_music);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(MusicBean data) {
        super.setData(data);
        mName.setText(data.getName());
        mMsg.setText(data.getArtist() + " - " + data.getAlbum());
    }
}
