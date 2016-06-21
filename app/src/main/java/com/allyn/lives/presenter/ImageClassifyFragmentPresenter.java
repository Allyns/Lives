package com.allyn.lives.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;

import com.allyn.lives.fragment.image.ImageClassifyFragment;

/**
 * Created by Administrator on 2016/6/21.
 */
public class ImageClassifyFragmentPresenter extends com.jude.beam.expansion.BeamBasePresenter<ImageClassifyFragment> implements SwipeRefreshLayout.OnRefreshListener {
    @Override
    protected void onCreate(@NonNull ImageClassifyFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
    }

    @Override
    protected void onCreateView(@NonNull ImageClassifyFragment view) {
        super.onCreateView(view);
        getView().recyclerView.setRefreshListener(this);
    }

    @Override
    public void onRefresh() {

    }
}

