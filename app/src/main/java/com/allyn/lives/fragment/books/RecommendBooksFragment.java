package com.allyn.lives.fragment.books;


import android.support.v4.app.Fragment;
import android.view.ViewGroup;

import com.allyn.lives.R;
import com.allyn.lives.bean.BooksBean;
import com.allyn.lives.holder.BooksHolder;
import com.allyn.lives.presenter.NewImagePreenter;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * A simple {@link Fragment} subclass.
 */
@RequiresPresenter(NewImagePreenter.class)
public class RecommendBooksFragment extends BeamListFragment<NewImagePreenter, BooksBean.ListEntity> {


    public static RecommendBooksFragment newInstance() {
        RecommendBooksFragment newImageFragment = new RecommendBooksFragment();
        return newImageFragment;
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new BooksHolder(parent);
    }

    @Override
    protected ListConfig getConfig() {
        return super.getConfig()
                .setContainerProgressRes(R.layout.progress_layout)
                .setLoadmoreAble(true)
                .setLoadMoreRes(R.layout.more_layout);
    }
}
