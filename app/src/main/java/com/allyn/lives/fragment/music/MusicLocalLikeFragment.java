package com.allyn.lives.fragment.music;

import android.view.ViewGroup;

import com.allyn.lives.R;
import com.allyn.lives.bean.MusicBean;
import com.allyn.lives.holder.MusicLocalHolder;
import com.allyn.lives.presenter.MusicLikePreenter;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

@RequiresPresenter(MusicLikePreenter.class)
public class MusicLocalLikeFragment extends BeamListFragment<MusicLikePreenter, MusicBean> {

    public static MusicLocalLikeFragment newInstance() {
        MusicLocalLikeFragment musicLocalAlbumFragment = new MusicLocalLikeFragment();
        return musicLocalAlbumFragment;
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new MusicLocalHolder(parent);
    }

    @Override
    protected ListConfig getConfig() {
        return super.getConfig().setContainerProgressRes(R.layout.progress_layout).setErrorRes(R.layout.nul_layout).setErrorAble(true);
    }
}
