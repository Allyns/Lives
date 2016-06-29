package com.allyn.lives.activity.books;

import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.allyn.lives.R;
import com.allyn.lives.presenter.BooksDetailPresenter;
import com.allyn.lives.utils.Config;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.pnikosis.materialishprogress.ProgressWheel;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(BooksDetailPresenter.class)
public class BooksDetailsActivity extends BeamBaseActivity<BooksDetailPresenter> {

    @Bind(R.id.toolbar)
    public Toolbar toolbar;
    @Bind(R.id.loading_progre)
    public ProgressWheel loading_progre;
    @Bind(R.id.fab)
    public FloatingActionButton fab;
    @Bind(R.id.tvAuthor)
    public TextView mAuthor;
    @Bind(R.id.tvClassifyName)
    public TextView mTvClassifyName;
    @Bind(R.id.recycler)
    public EasyRecyclerView recyclerView;
    @Bind(R.id.ivMsg)
    public ImageView mMsg;
    @Bind(R.id.ivBg)
    public ImageView mivBg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getIntent().getStringExtra(Config.Title));
        recyclerView.setErrorView(R.layout.error_layout);
    }


}
