package com.allyn.lives.fragment.music;

import android.view.ViewGroup;

import com.allyn.lives.holder.MusicLocalHolder;
import com.allyn.lives.model.bean.MusicBean;
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

    @Override
    protected ListConfig getConfig() {
        return super.getConfig()
                .setLoadmoreAble(true)
                .setErrorAble(true)
                .setContainerErrorAble(true)
                .setNoMoreAble(true);
    }
}