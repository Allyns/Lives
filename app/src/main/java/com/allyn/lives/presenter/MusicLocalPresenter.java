package com.allyn.lives.presenter;

import android.os.Bundle;

import com.allyn.lives.fragment.music.MusicLocalListFragment;
import com.allyn.lives.model.MusicModel;
import com.allyn.lives.model.bean.MusicBean;
import com.jude.beam.expansion.list.BeamListFragmentPresenter;

import java.util.List;

/**
 * Created by apple on 16/6/8.
 */
public class MusicLocalPresenter extends BeamListFragmentPresenter<MusicLocalListFragment, MusicBean> {

    @Override
    protected void onCreate(MusicLocalListFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        List<MusicBean> musicBeanList = MusicModel.getLocaleMusic();
        getAdapter().clear();
        getAdapter().addAll(musicBeanList);
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        getAdapter().pauseMore();
    }
}
