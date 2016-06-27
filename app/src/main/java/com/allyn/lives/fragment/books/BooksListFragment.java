package com.allyn.lives.fragment.books;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allyn.lives.R;
import com.allyn.lives.bean.BooksBean;
import com.allyn.lives.holder.BooksHolder;
import com.allyn.lives.presenter.BooksListFragmentPresenter;
import com.allyn.lives.utils.Config;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * A simple {@link Fragment} subclass.
 */
@RequiresPresenter(BooksListFragmentPresenter.class)
public class BooksListFragment extends BeamListFragment<BooksListFragmentPresenter, BooksBean.ListEntity> {


    public static BooksListFragment newInstance(int typeId) {
        Bundle bundle = new Bundle();
        bundle.putInt(Config.Type, typeId);
        BooksListFragment fragment = new BooksListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new BooksHolder(parent);
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
