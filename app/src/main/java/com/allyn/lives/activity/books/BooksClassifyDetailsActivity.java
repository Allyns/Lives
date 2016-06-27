package com.allyn.lives.activity.books;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.allyn.lives.R;
import com.allyn.lives.bean.BooksBean;
import com.allyn.lives.holder.BooksHolder;
import com.allyn.lives.presenter.BooksClassifyDetailsPresenter;
import com.allyn.lives.utils.Config;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(BooksClassifyDetailsPresenter.class)
public class BooksClassifyDetailsActivity extends BeamListActivity<BooksClassifyDetailsPresenter, BooksBean.ListEntity> {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycler)
    EasyRecyclerView recyclerView;

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new BooksHolder(parent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(BooksClassifyDetailsActivity.this);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getIntent().getStringExtra(Config.BookClassifyName));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_books_classify_details;
    }

    @Override
    protected ListConfig getConfig() {
        return super.getConfig()
                .setLoadMoreRes(R.layout.more_layout)
                .setLoadmoreAble(true)
                .setRefreshAble(true)
                .setContainerProgressAble(true)
                .setContainerProgressRes(R.layout.progress_layout);
    }
}
