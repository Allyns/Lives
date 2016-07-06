package com.allyn.lives.fragment.music;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allyn.lives.R;
import com.allyn.lives.holder.MusicLocalHolder;
import com.allyn.lives.bean.MusicBean;
import com.allyn.lives.presenter.MusicLocalPresenter;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by apple on 16/6/8.
 */
@RequiresPresenter(MusicLocalPresenter.class)
public class MusicLocalListFragment extends BeamListFragment<MusicLocalPresenter, MusicBean> {

    public static MusicLocalListFragment newInstance() {
        MusicLocalListFragment fragment = new MusicLocalListFragment();
        return fragment;
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new MusicLocalHolder(parent);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    protected ListConfig getConfig() {
        return super.getConfig()
                .setRefreshAble(true)
                .setErrorAble(true)
                .setContainerErrorAble(true)
                .setContainerProgressRes(R.layout.progress_layout)
                .setNoMoreAble(true);
    }
}
