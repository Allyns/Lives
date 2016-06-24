package com.allyn.lives.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.allyn.lives.adapter.ImageClassAdapter;
import com.allyn.lives.fragment.image.ImageClassifyFragment;
import com.allyn.lives.model.ImageModel;
import com.allyn.lives.bean.ImageClassifyBean;
import com.allyn.lives.view.widgets.DividerItemDecoration;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/6/21.
 */
public class ImageClassifyFragmentPresenter extends com.jude.beam.expansion.BeamBasePresenter<ImageClassifyFragment> implements SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {

    ImageClassAdapter adapter;
    ImageClassifyBean imageclassify;

    @Override
    protected void onCreate(@NonNull ImageClassifyFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
        adapter = new ImageClassAdapter(getView().getContext());
        onRefresh();
    }

    @Override
    protected void onCreateView(@NonNull ImageClassifyFragment view) {
        super.onCreateView(view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getView().getContext());
        getView().recyclerView.setLayoutManager(manager);
        getView().recyclerView.setAdapterWithProgress(adapter);
        getView().recyclerView.setRefreshListener(this);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onRefresh() {
        ImageModel.getDataClassify(new Subscriber<ImageClassifyBean>() {
            @Override
            public void onNext(ImageClassifyBean imageClassifyBean) {
                imageclassify = imageClassifyBean;
                adapter.clear();

                adapter.addAll(imageClassifyBean.getTngou());
            }

            @Override
            public void onCompleted() {
                getView().recyclerView.showProgress();
            }

            @Override
            public void onError(Throwable e) {
                if (getView().recyclerView != null) {
                    getView().recyclerView.showError();
                }
            }


        });
    }

    @Override
    public void onItemClick(int position) {
        int typeId = imageclassify.getTngou().get(position).getId();
//        startActivity();
    }


//    @Override
//    public void onLoadMore() {
//
//    }

}

