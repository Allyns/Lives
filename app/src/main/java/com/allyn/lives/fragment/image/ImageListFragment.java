package com.allyn.lives.fragment.image;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.allyn.lives.R;
import com.allyn.lives.bean.ImageBean;
import com.allyn.lives.holder.ImageHolder;
import com.allyn.lives.presenter.ImageListFragmentPresenter;
import com.allyn.lives.utils.Config;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * A simple {@link Fragment} subclass.
 */
@RequiresPresenter(ImageListFragmentPresenter.class)
public class ImageListFragment extends BeamListFragment<ImageListFragmentPresenter, ImageBean.ListEntity> {


    public static ImageListFragment newInstance(int typeId) {
        ImageListFragment fragment = new ImageListFragment();
        Bundle bundle = new Bundle();
        bundle.getInt(Config.typdId, typeId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new ImageHolder(parent);
    }

    @Override
    protected ListConfig getConfig() {
        return super.getConfig()
                .setRefreshAble(true)
                .setErrorAble(true)
                .setLoadMoreRes(R.layout.more_layout)
                .setLoadmoreAble(true)
                .setContainerErrorAble(true)
                .setContainerProgressRes(R.layout.progress_layout)
                .setNoMoreAble(true);
    }
}
