package com.allyn.lives.fragment.image;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allyn.lives.R;
import com.allyn.lives.presenter.ImageClassifyFragmentPresenter;
import com.jude.beam.bijection.BeamFragment;
import com.jude.easyrecyclerview.EasyRecyclerView;

import butterknife.Bind;

/**
 * 2016-6-21
 */
public class ImageClassifyFragment extends BeamFragment<ImageClassifyFragmentPresenter> {


    public ImageClassifyFragment newInstance() {
        ImageClassifyFragment imageFragment = new ImageClassifyFragment();
        return imageFragment;
    }

    @Bind(R.id.recycler)
   public  EasyRecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_image, container, false);
        recyclerView.setErrorView(R.layout.error_layout);
        return v;
    }

}
