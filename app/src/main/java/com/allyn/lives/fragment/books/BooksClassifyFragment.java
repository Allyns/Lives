package com.allyn.lives.fragment.books;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allyn.lives.R;
import com.allyn.lives.presenter.BooksClassifyFragmentPresenter;
import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.easyrecyclerview.EasyRecyclerView;

/**
 * 2016-6-21
 */
@RequiresPresenter(BooksClassifyFragmentPresenter.class)
public class BooksClassifyFragment extends BeamFragment<BooksClassifyFragmentPresenter> {


    public static BooksClassifyFragment newInstance() {
        BooksClassifyFragment imageFragment = new BooksClassifyFragment();
        return imageFragment;
    }

    public EasyRecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_books, container, false);
        recyclerView = (EasyRecyclerView) v.findViewById(R.id.recycler);
        recyclerView.setErrorView(R.layout.error_layout);
        recyclerView.setProgressView(R.layout.progress_layout);
        return v;
    }

}
